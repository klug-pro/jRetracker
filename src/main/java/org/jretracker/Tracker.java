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
class Tracker {

    private static final ConcurrentHashMap<String, TorrentInfo> torrents = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, PeerInfo> peers = new ConcurrentHashMap<>();

    public static TorrentInfo getTorrent(String infoHash) {
        return torrents.get(infoHash);
    }

    public static PeerInfo getPeer(String ip) {
        return peers.get(ip);
    }

    public static void addPeer(PeerInfo peerInfo) {
        peers.put(peerInfo.getIp(), peerInfo);
    }

    public static void addTorrent(TorrentInfo torrent) {
        torrents.put(torrent.getInfoHash(), torrent);
    }

    public static int getTorrentsCount() {
        return torrents.size();
    }

    public static int getPeersCount() {
        return peers.size();
    }

    public static boolean hasPeer(String ip) {
        return peers.containsKey(ip);
    }

    public static boolean hasTorrent(String infoHash) {
        return !torrents.containsKey(infoHash);
    }

    public static List<TorrentInfo> getTorrents() {
        return new ArrayList<>(torrents.values());
    }
}
