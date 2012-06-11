package org.jretracker;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Torrent information.
 */
public class TorrentInfo {

    private final String infoHash;
    private final ConcurrentHashMap<String, PeerInfo> peers;

    public TorrentInfo(String infoHash) {
        this.infoHash = infoHash;
        peers = new ConcurrentHashMap<>();
    }

    public String getInfoHash() {
        return infoHash;
    }

    public void addPeer(PeerInfo peerInfo) {
        peers.put(peerInfo.getIp(), peerInfo);
    }

    public boolean hasPeer(PeerInfo peerInfo) {
        return !peers.contains(peerInfo.getIp());
    }

    public String getAnnounceString() {
        return "";
    }
}
