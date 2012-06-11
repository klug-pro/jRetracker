package org.jtracker;

import org.jretracker.PeerInfo;
import org.jretracker.TorrentInfo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail Filippov
 * Date: 5/26/12
 * Time: 1:14 AM
 */
public class TorrentInfoTests {

    @Test
    public void ShouldBeCreateWithCorrectInfoHash() {
        assertEquals("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", new TorrentInfo("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3").getInfoHash());
    }

    @Test
    public void ShouldBeReturnCorrectAnnounceString() {
        TorrentInfo torrent = new TorrentInfo("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3");
        torrent.addPeer(new PeerInfo("178.159.49.6", 57911));
        torrent.addPeer(new PeerInfo("178.159.50.2", 57911));

        byte[] bytes = {(byte) 0xB2, (byte) 0x9F, 0x31, 0x06, (byte) 0xE2, 0x37, (byte) 0xB2, (byte) 0x9F, 0x32, 0x02, (byte) 0xE2, 0x37};
        assertEquals("d8:completei0e10:incompletei2e8:intervali1800e12:min intervali1800e5:peers12:" + new String(bytes) + "e", torrent.getAnnounceString());
    }

}
