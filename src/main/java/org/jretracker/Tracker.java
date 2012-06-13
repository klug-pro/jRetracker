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

    private final ConcurrentHashMap<String, TorrentInfo> torrents;
    private int interval;
    private int minInterval;
    private int countCompleteForDownload;

    public Tracker(int interval, int minInterval, int countCompleteForDownload) {
        torrents = new ConcurrentHashMap<>();
        this.interval = interval;
        this.minInterval = minInterval;
        this.countCompleteForDownload = countCompleteForDownload;
    }
    public TorrentInfo getTorrent(String infoHash) {
        return torrents.get(infoHash);
    }

    public int getTorrentsCount() {
        return torrents.size();
    }

    public List<TorrentInfo> getTorrents() {
        return new ArrayList<>(torrents.values());
    }

    public TorrentInfo processAnnounce(String infoHash, Action action, PeerInfo peer) {
        if (torrents.containsKey(infoHash)) {
            TorrentInfo torrent = torrents.get(infoHash);
            if (action == Action.STOP) {
                if (torrent.containsPeer(peer)) {
                    torrent.removePeer(peer);
                }
            }
            if (action == Action.COMPLETE) {
                if (torrent.containsPeer(peer)) {
                    torrent.getPeer(peer.getIp()).setState(State.DOWNLOADED);
                }
                if (countCompleteForDownload <= getCountCompleteForDownload() && !torrent.isDownloaded()) {
                    DownloadManager.startDownload(torrent);
                    torrent.downloaded();
                }
            }
            if (action == Action.START) {
                if (!torrent.containsPeer(peer)) {
                    torrent.addPeer(peer);
                } else {
                    torrent.getPeer(peer.getIp()).setState(State.DOWNLOADING);
                }
            }
            return torrent;
        } else {
            if (action == Action.START) {
                TorrentInfo torrent = new TorrentInfo(infoHash);
                peer.setState(State.DOWNLOADING);
                torrent.addPeer(peer);
                torrents.put(infoHash, torrent);
                return torrent;
            }
        }
        return null;
    }

    public int getMinInterval() {
        return minInterval;
    }

    public int getInterval() {
        return interval;
    }

    public int getCountCompleteForDownload() {
        return countCompleteForDownload;
    }

}
