package org.jtracker;

import org.jretracker.PeerInfo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail Filippov
 * Date: 5/25/12
 * Time: 9:48 PM
 */
public class PeerInfoTests {
    @Test
    public void ShouldBeSetIpAfterCreation() {
        assertEquals("127.0.0.1", new PeerInfo("127.0.0.1", 57911).getIp());
    }
}
