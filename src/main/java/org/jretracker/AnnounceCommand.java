package org.jretracker;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail Filippov
 * Date: 5/26/12
 * Time: 1:41 AM
 */
public class AnnounceCommand implements ICommand {
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

            PeerInfo peerInfo = new PeerInfo(ip);

            peerInfo.setPort(Integer.parseInt(params.get("port")));
            String infoHash = params.get("info_hash");
            if (Tracker.hasTorrent(infoHash)) {
                TorrentInfo torrentInfo = new TorrentInfo(infoHash);
                torrentInfo.addPeer(peerInfo);
                Tracker.addTorrent(torrentInfo);
            } else {
                TorrentInfo torrentInfo = Tracker.getTorrent(infoHash);
                if (torrentInfo.hasPeer(peerInfo)) {
                    torrentInfo.addPeer(peerInfo);
                }
            }
        } else {
            buffer.append(validator.getError());
        }
        return buffer.toString();
    }
}
