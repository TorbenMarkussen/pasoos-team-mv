package pasoos.view;

import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.Location;

import java.awt.*;

import static org.junit.Assert.*;

public class HotgammonPositioningStrategyTest {
    private static final int CHECKER_WIDTH = 27;
    private static final double CHECKER_HEIGHT = 27;
    private HotgammonPositioningStrategy ps;

    @Before
    public void setUp() throws Exception {
        ps = new HotgammonPositioningStrategy();
    }

    @Test
    public void should_position_6_checkers_on_a_straight_row() {
        Point p = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 0);
        Rectangle r = new Rectangle(500, 217, 40 - CHECKER_WIDTH, 200);
        assertTrue(r.contains(p));
        Point p1 = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 1);
        assertEquals(p.getX(), p1.getX(), 0.01);
        assertTrue((p.getY() - p1.getY()) >= CHECKER_HEIGHT);
        Point p5 = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 5);
        assertEquals(p.getX(), p5.getX(), 0.01);
        assertTrue((p.getY() - p5.getY()) >= (CHECKER_HEIGHT * 5));
    }

    @Test
    public void should_position_7th_checker_between_checker_1_and_2() {
        Point p = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 0);
        Point p1 = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 1);
        assertTrue((p.getY() - p1.getY()) >= CHECKER_HEIGHT);
        Point p7 = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 6);
        assertTrue(p7.getY() > p1.getY());
        assertTrue(p7.getY() < p.getY());
    }
}
