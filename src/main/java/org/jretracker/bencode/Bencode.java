package org.jretracker.bencode;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail
 * Date: 11.06.12
 * Time: 2:08
 */
public class Bencode {
    private final StringBuilder buffer;

    public Bencode() {
        buffer = new StringBuilder();
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    public Bencode append(int value) {
        buffer.append(String.format(Locale.US, "i%de", value));
        return this;
    }

    public Bencode append(String value) {
        buffer.append(String.format(Locale.US, "%d:%s", value.length(), value));
        return this;
    }

    public Bencode append(byte[] bytes) {
        try {
            buffer.append(String.format(Locale.US, "%d:%s", bytes.length, new String(bytes, "UTF-8")));
        } catch (UnsupportedEncodingException ignored) {}
        return this;
    }

    public Bencode startList() {
        buffer.append("l");
        return this;
    }

    public Bencode endList() {
        buffer.append("e");
        return this;
    }

    public Bencode startMap() {
        buffer.append("d");
        return this;
    }

    public Bencode endMap() {
        buffer.append("e");
        return this;
    }

}
