package org.jretracker;

import org.jretracker.bencode.Bencode;

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
        Bencode bencode = new Bencode();
        byte[] buffer = new byte[peers.size() * 6];
        int cnt = 0;
        for(PeerInfo peer : peers.values()) {
            byte[] peerIpBytes = peer.getIpAsByteArray();
            byte[] peerPortBytes = peer.getPortAsByteArray();
            for (byte b : peerIpBytes) {
                buffer[cnt] = b;
                cnt++;
            }
            for(byte b : peerPortBytes) {
                buffer[cnt] = b;
                cnt++;
            }
        }

        return bencode
                .startMap()
                .append("complete").append(0)
                .append("incomplete").append(2)
                .append("interval").append(1800)
                .append("min interval").append(1800)
                .append("peers").append(buffer)
                .endMap()
                .toString();
    }
}
