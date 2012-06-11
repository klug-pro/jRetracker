package org.jretracker;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail
 * Date: 12.06.12
 * Time: 2:45
 */
public class DownloadManager {

    public static void startDownload(TorrentInfo torrentInfo) {
        try {
            StringBuilder link = new StringBuilder();
            link.append(ConfigurationManager.getDownloadServer());

            URL url = new URL(link.toString());
            url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    try {
    // Construct data
    String data = URLEncoder.encode("key1", "UTF-8") + "=" + URLEncoder.encode("value1", "UTF-8");
    data += "&" + URLEncoder.encode("key2", "UTF-8") + "=" + URLEncoder.encode("value2", "UTF-8");

    // Send data
    URL url = new URL("http://hostname:80/cgi");
    URLConnection conn = url.openConnection();
    conn.setDoOutput(true);
    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
    wr.write(data);
    wr.flush();

    // Get the response
    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String line;
    while ((line = rd.readLine()) != null) {
        // Process line...
    }
    wr.close();
    rd.close();
} catch (Exception e) {
}
     */
}
