package org.jretracker;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail Filippov
 * Date: 5/26/12
 * Time: 1:48 AM
 */
public class StatCommand implements ICommand {

    private Tracker tracker;

    public StatCommand() {
        this.tracker = App.getTracker();
    }

    public StatCommand(Tracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public String execute(Map<String,String> params) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Torrents: ");
        buffer.append(tracker.getTorrentsCount());
        buffer.append("<br />Hash list:<br /><ul>");
        for (TorrentInfo torrentInfo : tracker.getTorrents()) {
            buffer.append("<li>");
            buffer.append(torrentInfo.getInfoHash());
            buffer.append(" - peers count: ");
            //buffer.append(torrentInfo.)
            buffer.append("</li>");
        }
        buffer.append("</ul>");
        return buffer.toString();
    }
}
