package org.jtracker.utility;

import org.jretracker.utility.Codec;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail Filippov
 * Date: 5/28/12
 * Time: 1:22 AM
 */
public class CodecTests {
    @Test
    public void ShouldBeGetCorrectHexStringFromUrl() {
        assertEquals("ce22aa5501dd79b1a9bfc321255327aa868eb103", Codec.getHexStringFromUrl("%ce%22%aaU%01%ddy%b1%a9%bf%c3%21%25S%27%aa%86%8e%b1%03"));
    }
}
