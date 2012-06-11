package org.jtracker;

import org.jretracker.Action;
import org.jretracker.ConfigurationManager;
import org.jretracker.PeerInfo;
import org.jretracker.Tracker;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail
 * Date: 12.06.12
 * Time: 1:57
 */
public class TrackerTests {

    private Tracker tracker;

    @Before
    public void SetUp() {
        tracker = new Tracker(ConfigurationManager.getInterval(), ConfigurationManager.getMinInterval(), ConfigurationManager.getCountCompleteForDownload());
    }

    @Test
    public void ShouldBeCorrectAnnounceForNewTorrent() {
        PeerInfo peer = new PeerInfo("178.159.50.6", 57911);
        tracker.processAnnounce("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", Action.START, peer);
        assertEquals(1, tracker.getTorrentsCount());
    }

    @Test
    public void ShouldBeCorrectAddPeerForNewTorrent() {
        PeerInfo peer = new PeerInfo("178.159.50.6", 57911);
        tracker.processAnnounce("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", Action.START, peer);
        assertEquals(1, tracker.getTorrent("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3").getPeersCount());
    }

    @Test
    public void ShouldBeNotChangePeerCountIfTorrentReAnnounce() {
        PeerInfo peer = new PeerInfo("178.159.50.6", 57911);
        tracker.processAnnounce("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", Action.START, peer);
        tracker.processAnnounce("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", Action.START, peer);
        assertEquals(1, tracker.getTorrent("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3").getPeersCount());
    }

    @Test
    public void ShouldBeDeletePeerAfterStop() {
        PeerInfo peer = new PeerInfo("178.159.50.6", 57911);
        tracker.processAnnounce("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", Action.START, peer);
        assertEquals(1, tracker.getTorrent("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3").getPeersCount());
        tracker.processAnnounce("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", Action.STOP, peer);
        assertEquals(0, tracker.getTorrent("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3").getPeersCount());
    }

    @Test
    public void ShouldBeCorrectCountIncompletePeers() {
        PeerInfo peer = new PeerInfo("178.159.50.6", 57911);
        tracker.processAnnounce("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", Action.START, peer);
        assertEquals(1, tracker.getTorrent("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3").getIncompletePeersCount());
    }

    @Test
    public void ShouldBeCorrectCountCompletePeers() {
        PeerInfo peer = new PeerInfo("178.159.50.6", 57911);
        tracker.processAnnounce("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", Action.START, peer);
        tracker.processAnnounce("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", Action.COMPLETE, peer);
        assertEquals(1, tracker.getTorrent("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3").getCompletePeersCount());
    }

    @Test
    public void ShouldBeCorrectIncompletePeerCountAfterDeletePeer() {
        PeerInfo peer = new PeerInfo("178.159.50.6", 57911);
        tracker.processAnnounce("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", Action.START, peer);
        tracker.processAnnounce("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", Action.STOP, peer);
        assertEquals(0, tracker.getTorrent("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3").getIncompletePeersCount());
    }

}
