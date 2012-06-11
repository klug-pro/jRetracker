package org.jretracker;

import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail
 * Date: 12.06.12
 * Time: 2:38
 */
public class ConfigurationManager {

    public static int getPort() {
        try {
            return new Wini(new File("settings.ini")).get("main", "port", int.class);
        } catch (IOException ignored) {
            return -1;
        }
    }

    public static int getInterval() {
        try {
            return new Wini(new File("settings.ini")).get("main", "interval", int.class);
        } catch (IOException ignored) {
            return -1;
        }
    }

    public static int getMinInterval() {
        try {
            return new Wini(new File("settings.ini")).get("main", "min_interval", int.class);
        } catch (IOException ignored) {
            return -1;
        }
    }

    public static int getCountCompleteForDownload() {
        try {
            return new Wini(new File("settings.ini")).get("main", "count_complete_for_download", int.class);
        } catch (IOException ignored) {
            return -1;
        }
    }

    public static String getDownloadServer() {
        try {
            return new Wini(new File("settings.ini")).get("main", "download_server", String.class);
        } catch (IOException ignored) {
            return "127.0.0.1";
        }
    }

}
