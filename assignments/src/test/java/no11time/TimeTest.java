package no11time;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.junit.Assert.assertEquals;

public class TimeTest {
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void testInvalidInput () {
        Time t = new Time(-1, 299, 8080);
        assertEquals("00:00:00", t.toString());
    }

    @Test
    public void testGetters () {
        Time t = new Time(2, 30, 34);
        assertEquals(2, t.getHour());
        assertEquals(30, t.getMinute());
        assertEquals(34, t.getSecond());
    }

    @Test
    public void testSetters () {
        Time t = new Time(7, 0, 39);
        t.setHour(8);
        t.setMinute(1);
        t.setSecond(59);

        assertEquals("08:01:59", t.toString());

        t.setTime(24, 99, -6);

        assertEquals("00:00:00", t.toString());
    }

    @Test
    public void testNextSecond() {
        Time t = new Time(9, 59, 59);

        Time tt = t.nextSecond();

        assertEquals("10:00:00", t.toString());
        assertEquals("10:00:00", tt.toString());
    }

    @Test
    public void testPreviousSecond() {
        Time t = new Time(22, 0, 0);

        Time tt = t.previousSecond();

        assertEquals("21:59:59", t.toString());
        assertEquals("21:59:59", tt.toString());
    }
}