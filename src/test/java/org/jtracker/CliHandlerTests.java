package org.jtracker;

import org.jretracker.CliHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CliHandlerTests {

    private CliHandler cliHandler = null;

    @Before
    public void SetUp(){
        cliHandler = new CliHandler();
    }

    @Test
    public void ShouldBeCorrectParsePortOption() throws Exception {
        cliHandler.parse(new String[]{"--port=80"});
        assertEquals(80, cliHandler.getPort());
    }

    @Test
    public void ShouldBeReturnDefaultPortIfPortNotSet() throws Exception {
        cliHandler.parse(new String[]{});
        assertEquals(8080, cliHandler.getPort());
    }

    @Test
    public void IsHelpReturnTrueIfHelpArgExists() throws Exception {
        cliHandler.parse(new String[]{"--help"});
        assertTrue(cliHandler.isHelp());
    }

    @Test
    public void IsHelpReturnFalseIfHelpArgNotExists() throws Exception {
        cliHandler.parse(new String[]{});
        assertFalse(cliHandler.isHelp());
    }
}
