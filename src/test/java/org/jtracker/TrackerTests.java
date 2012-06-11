package org.jtracker;

import org.jretracker.Action;
import org.jretracker.PeerInfo;
import org.jretracker.Tracker;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: mikhail
 * Date: 11.06.12
 * Time: 13:59
 */
public class TrackerTests {

    @Test
    public void ShouldBeCorrectProcessStartAnnounce() {
        PeerInfo peer = new PeerInfo("178.159.50.6", 57911);
        Tracker.processAnnounce("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", Action.START, peer);
        assertEquals(1, Tracker.getTorrentsCount());
    }

}
