package org.jtracker.bencode;

import org.jretracker.bencode.Bencode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail
 * Date: 11.06.12
 * Time: 3:14
 */
public class EncoderTest {

    private Bencode bencode;

    @Before
    public void SetUp() {
        bencode = new Bencode();
    }

    @Test
    public void ShouldBeCorrectEncodeInt() {
        assertEquals("i1e", bencode.append(1).toString());
    }

    @Test
    public void ShouldBeCorrectEncodeString() {
        assertEquals("5:value", bencode.append("value").toString());
    }

    @Test
    public void ShouldBeConvertEncodeByteArray() {
        assertEquals("5:value", bencode.append("value".getBytes()).toString());
    }

    @Test
    public void ShouldBeCorrectEncodeList() {
        assertEquals("l5:value3:vali42ee", bencode.startList().append("value").append("val").append(42).endList().toString());
    }

    @Test
    public void ShouldBeCorrectEncodeMap() {
        assertEquals("d3:key5:valuee", bencode.startMap().append("key").append("value").endMap().toString());
    }

}
