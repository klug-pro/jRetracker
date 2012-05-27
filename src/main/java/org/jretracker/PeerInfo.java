package org.jretracker;

/**
 * For peer information.
 */
public class PeerInfo {

    private final String ip;
    private int port;

    public PeerInfo(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
