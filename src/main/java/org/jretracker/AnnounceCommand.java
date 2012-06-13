package org.jretracker;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail Filippov
 * Date: 5/26/12
 * Time: 1:41 AM
 */
public class AnnounceCommand implements ICommand {

    private Tracker tracker;

    public AnnounceCommand() {
        this.tracker = App.getTracker();
    }

    public AnnounceCommand(Tracker tracker) {
        this.tracker = tracker;
    }

    public String execute(Map<String, String> params) {
        StringBuilder buffer = new StringBuilder();
        AnnounceValidator validator = new AnnounceValidator(params);
        if (validator.isValid()) {
            String ip;
            if(params.containsKey("ip")) {
                ip = params.get("ip");
            } else {
                ip = params.get("ip_from_request");
            }

            PeerInfo peerInfo = new PeerInfo(ip, Integer.parseInt(params.get("port")));
            String infoHash = params.get("info_hash");
            Action action = null;
            switch (params.get("event")) {
                case "started":
                    action = Action.START;
                    break;
                case "stopped ":
                    action = Action.STOP;
                    break;
                case "completed":
                    action = Action.COMPLETE;
                    break;
            }
            buffer.append(tracker.processAnnounce(infoHash, action, peerInfo).getAnnounceString());
        } else {
            buffer.append(validator.getError());
        }
        return buffer.toString();
    }
}
