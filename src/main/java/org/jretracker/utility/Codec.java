package org.jretracker.utility;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail Filippov
 * Date: 5/28/12
 * Time: 1:21 AM
 */
public class Codec {
    public static String getHexStringFromUrl(String str) {
        StringBuilder buffer = new StringBuilder();
        for(int i = 0; i < str.length();) {
            if (str.charAt(i) == '%') {
                byte b = (byte)((byte)(Character.digit(str.charAt(i + 1), 16) << 4) + (byte)Character.digit(str.charAt(i + 2), 16));
                buffer.append(String.format("%02X", b).toLowerCase());
                i += 3;
            } else {
                buffer.append(String.format("%02X", (byte) str.charAt(i)).toLowerCase());
                i++;
            }
        }
        return buffer.toString();
    }
}
