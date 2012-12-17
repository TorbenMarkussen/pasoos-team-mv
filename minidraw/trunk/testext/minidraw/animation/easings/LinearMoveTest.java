package minidraw.animation.easings;


import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class LinearMoveTest {

    EasingFunctionStrategy linearMove;
    Point begin;
    Point end;

    @Before
    public void Setup() {
        linearMove = new LinearMove();
        begin = new Point(100, 200);
        end = new Point(300, 400);
    }

    @Test
    public void should_return_startpoint_at_0_time_elapsed() {
        Point newPoint = linearMove.calculate(0.0, begin, end);

        assertEquals(100, newPoint.x);
        assertEquals(200, newPoint.y);

    }

    @Test
    public void should_return_endpoint_at_1_time_elapsed() {
        Point newPoint = linearMove.calculate(1.0, begin, end);

        assertEquals(300, newPoint.x);
        assertEquals(400, newPoint.y);

    }

    @Test
    public void should_return_midpoint_at_half_time_elapsed() {
        Point newPoint = linearMove.calculate(0.5, begin, end);

        assertEquals(200, newPoint.x);
        assertEquals(300, newPoint.y);

    }
}
