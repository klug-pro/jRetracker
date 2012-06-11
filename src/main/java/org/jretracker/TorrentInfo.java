package org.jretracker;

import org.jretracker.bencode.Bencode;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Torrent information.
 */
public class TorrentInfo {

    private final String infoHash;
    private final ConcurrentHashMap<String, PeerInfo> peers;
    private boolean downloaded = false;

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
                .append("complete").append(getCompletePeersCount())
                .append("incomplete").append(getIncompletePeersCount())
                .append("interval").append(App.getTracker().getInterval())
                .append("min interval").append(App.getTracker().getMinInterval())
                .append("peers").append(buffer)
                .endMap()
                .toString();
    }

    public int getPeersCount() {
        return peers.size();
    }

    public boolean containsPeer(PeerInfo peer) {
        return peers.containsKey(peer.getIp());
    }

    public void removePeer(PeerInfo peer) {
        peers.remove(peer.getIp());
    }

    public int getIncompletePeersCount() {
        int result = 0;
        for (PeerInfo peer : peers.values()) {
            if (peer.getState() == State.DOWNLOADING) {
                result++;
            }
        }
        return result;
    }

    public int getCompletePeersCount() {
        int result = 0;
        for (PeerInfo peer : peers.values()) {
            if (peer.getState() == State.DOWNLOADED) {
                result++;
            }
        }
        return result;
    }

    public PeerInfo getPeer(String ip) {
        return peers.get(ip);
    }

    public boolean isDownloaded() {
        return downloaded;
    }

    public void downloaded() {
        downloaded = true;
    }

}
