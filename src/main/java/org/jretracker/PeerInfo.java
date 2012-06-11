package org.jretracker;

/**
 * For peer information.
 */
public class PeerInfo {

    private final String ip;
    private int port;

    public PeerInfo(String ip, int i) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public byte[] getIpAsByteArray() {
        byte[] buffer = new byte[4];
        String[] parts = ip.split("\\.");
        if (parts.length == 4) {
            for (int i = 0; i < 4; i++) {
                buffer[i] = Byte.valueOf(parts[i]);
            }
        }
        return buffer;
    }

    public byte[] getPortAsByteArray() {
        byte[] buffer = new byte[2];
        buffer[0] = (byte) (port & 0xFF);
        buffer[1] = (byte) ((port >> 8) & 0xFF);
        return buffer;
    }
}
