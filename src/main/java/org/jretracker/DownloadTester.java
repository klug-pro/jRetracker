package org.jretracker;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail
 * Date: 13.06.12
 * Time: 2:54
 */
public class DownloadTester {
    public static void main(String[] args) {
        DownloadManager.startDownload(new TorrentInfo("e9de6f415a109a7c8e7bc95dd68651e780fa7ef2"));
    }
}
