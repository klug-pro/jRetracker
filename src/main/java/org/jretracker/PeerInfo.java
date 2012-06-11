package org.jretracker;

/**
 * For peer information.
 */
public class PeerInfo {

    private final String ip;
    private int port;
    private State state;

    public PeerInfo(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public byte[] getIpAsByteArray() {
        byte[] buffer = new byte[4];
        String[] parts = ip.split("\\.");
        if (parts.length == 4) {
            for (int i = 0; i < 4; i++) {
                int value = Integer.valueOf(parts[i]);
                buffer[i] = (byte)value;
            }
        }
        return buffer;
    }

    public byte[] getPortAsByteArray() {
        byte[] buffer = new byte[2];
        buffer[0] = (byte) ((port >> 8) & 0xFF);
        buffer[1] = (byte) (port & 0xFF);
        return buffer;
    }
}
