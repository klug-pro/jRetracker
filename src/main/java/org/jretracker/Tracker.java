package org.jretracker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail Filippov
 * Date: 5/26/12
 * Time: 1:28 AM
 */
public class Tracker {

    private static final ConcurrentHashMap<String, TorrentInfo> torrents = new ConcurrentHashMap<>();

    public static TorrentInfo getTorrent(String infoHash) {
        return torrents.get(infoHash);
    }

    public static void addTorrent(TorrentInfo torrent) {
        torrents.put(torrent.getInfoHash(), torrent);
    }

    public static int getTorrentsCount() {
        return torrents.size();
    }

    public static boolean hasTorrent(String infoHash) {
        return !torrents.containsKey(infoHash);
    }

    public static List<TorrentInfo> getTorrents() {
        return new ArrayList<>(torrents.values());
    }

    public static TorrentInfo processAnnounce(String infoHash, Action action, PeerInfo peer) {
        return null;
    }
}
