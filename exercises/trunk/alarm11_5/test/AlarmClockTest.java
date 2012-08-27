import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 24-08-12
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */


public class AlarmClockTest {

    AlarmClock clock;

    @Before
    public void Setup() {
        clock = new AlarmClockImpl();
    }

    @Test
    public void should_handle_all() {
        assertEquals("11:32", clock.readDisplay());

        clock.increase();
        assertEquals("11:32", clock.readDisplay());
        clock.decrease();
        assertEquals("11:32", clock.readDisplay());

        clock.mode();
        assertEquals("06:15", clock.readDisplay());
        clock.increase();
        clock.increase();
        clock.increase();
        assertEquals("09:15", clock.readDisplay());

        clock.mode();
        clock.increase();
        clock.increase();
        assertEquals("09:17", clock.readDisplay());

        clock.mode();
        assertEquals("11:32", clock.readDisplay());

        clock.mode();
        assertEquals("09:17", clock.readDisplay());
        for (int i = 1;i<=10;i++)
            clock.decrease();
        assertEquals("23:17", clock.readDisplay());
        clock.increase();
        assertEquals("00:17", clock.readDisplay());

        clock.mode();
        assertEquals("00:17", clock.readDisplay());

        for (int i = 1;i<=18;i++)
            clock.decrease();
        assertEquals("00:59", clock.readDisplay());
        clock.increase();
        assertEquals("00:00", clock.readDisplay());

    }
}
