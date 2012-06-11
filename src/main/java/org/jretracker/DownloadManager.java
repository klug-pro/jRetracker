package org.jretracker;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail
 * Date: 12.06.12
 * Time: 2:45
 */
public class DownloadManager {
    public static void startDownload(TorrentInfo torrentInfo) {
        System.out.println("Download started on server:" + ConfigurationManager.getDownloadServer());
    }
}
