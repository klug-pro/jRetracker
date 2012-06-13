package org.jretracker;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.*;
import java.net.*;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail
 * Date: 12.06.12
 * Time: 2:45
 */
public class DownloadManager {

    public static void startDownload(TorrentInfo torrentInfo) {
        String cookie= getCookie();
        String token = getAuthToken(cookie);
        addTorrentByUrl(cookie, token, torrentInfo.getInfoHash());
    }

    public static void addTorrentByUrl(String cookie, String token, String infoHash) {
        try {
            String link = String.format(Locale.US, "http://%s/gui/?token=%s&action=add-url&s=%s%s",
                    ConfigurationManager.getDownloadServer(),
                    URLEncoder.encode(token, "ISO-8859-1"),
                    URLEncoder.encode("magnet:?xt=urn:btih:", "ISO-8859-1"),
                    URLEncoder.encode(infoHash, "ISO-8859-1"));
            System.out.println(link);
            try {
                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setRequestProperty("Authorization", getBasicAuthString());
                conn.setRequestProperty("Cookie", String.format(Locale.US, "GUID=%s", cookie));
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String getCookie() {
        String link = String.format(Locale.US, "http://%s/gui/token.html", ConfigurationManager.getDownloadServer());
        try {
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Authorization", getBasicAuthString());

            String cookie = conn.getHeaderField("Set-Cookie");
            Pattern p = Pattern.compile("GUID=(.+); path.+");
            Matcher m = p.matcher(cookie);
            m.find();
            return m.group(1);

        }  catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getAuthToken(String cookie) {
        String link = String.format(Locale.US, "http://%s/gui/token.html", ConfigurationManager.getDownloadServer());
        try {
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Authorization", getBasicAuthString());
            conn.setRequestProperty("Cookie", String.format(Locale.US, "GUID=%s", cookie));
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                if (line.contains("token")) {
                    Pattern p = Pattern.compile(".+>(.+)</div.+");
                    Matcher m = p.matcher(line);
                    m.find();
                    return m.group(1);
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getBasicAuthString() {
        String authString = String.format(Locale.US, "%s:%s",
                ConfigurationManager.getUsername(),
                ConfigurationManager.getPassword());
        String line = Base64.encode(authString.getBytes());
        return String.format(Locale.US, "Basic %s", line);
    }
}
