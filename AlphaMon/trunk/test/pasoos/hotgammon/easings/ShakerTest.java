package pasoos.hotgammon.easings;


import minidraw.animation.easings.EasingFunctionStrategy;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

public class ShakerTest {

    @Test
    public void should_move_max_5_pixels() {
        EasingFunctionStrategy shaker = new Shaker();

        Point begin = new Point(100, 100);
        Point end = new Point(200, 200);

        Point newPoint = shaker.calculate(0.5, begin, end);

        assertTrue(newPoint.x <= begin.x + 5);
        assertTrue(newPoint.x >= begin.x - 5);
        assertTrue(newPoint.y <= begin.y + 5);
        assertTrue(newPoint.y >= begin.y - 5);
    }
}
