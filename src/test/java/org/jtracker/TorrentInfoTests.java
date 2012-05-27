package org.jtracker;

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

}
