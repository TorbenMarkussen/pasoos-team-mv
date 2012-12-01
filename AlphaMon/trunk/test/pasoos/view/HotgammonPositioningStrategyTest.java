package pasoos.view;

import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.Location;

import java.awt.*;

import static org.junit.Assert.*;

public class HotgammonPositioningStrategyTest {
    private static final int CHECKER_WIDTH = 27;
    private static final double CHECKER_HEIGHT = 27;
    private static final Dimension checkerDimension = new Dimension(27, 27);
    private HotgammonPositioningStrategy ps;

    @Before
    public void setUp() throws Exception {
        ps = new HotgammonPositioningStrategy();
    }

    @Test
    public void should_position_6_checkers_on_a_straight_row() {
        Rectangle r = new Rectangle(500, 217, 40 - CHECKER_WIDTH, 200);

        // Check Checker position 1
        Point checker1Position = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 0); // Calculate Checker 1 position
        assertTrue(r.contains(checker1Position));

        // Check Checker position 2
        Point checker2Position = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 1); // Calculate Checker 2 position
        assertEquals(checker1Position.getX(), checker2Position.getX(), 0.01);
        assertTrue((checker1Position.getY() - checker2Position.getY()) >= CHECKER_HEIGHT);

        // Check Checker position 6
        Point checker6Position = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 5); // Calculate Checker 6 position
        assertEquals(checker1Position.getX(), checker6Position.getX(), 0.01);
        assertTrue((checker1Position.getY() - checker6Position.getY()) >= (CHECKER_HEIGHT * 5));
    }

    @Test
    public void should_position_7th_checker_between_checker_1_and_2() {
        Point checker1Position = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 0); // Calculate Checker 1 position
        Point checker2Position = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 1); // Calculate Checker 2 position
        assertTrue((checker1Position.getY() - checker2Position.getY()) >= CHECKER_HEIGHT);

        // Check Checker position 7
        Point checker7Position = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 6); // Calculate Checker 7 position
        assertTrue(checker7Position.getY() > checker2Position.getY());
        assertTrue(checker7Position.getY() < checker1Position.getY());
    }

    @Test
    public void should_position_11th_checker_between_checker_5_and_6() {
        Point checker5Position = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 4); // Calculate Checker 5 position
        Point checker6Position = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 5); // Calculate Checker 6 position
        assertTrue((checker5Position.getY() - checker6Position.getY()) >= CHECKER_HEIGHT);

        // Check Checker position 11
        Point checker11Position = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 10); // Calculate Checker 1 position
        assertTrue(checker11Position.getY() > checker6Position.getY());
        assertTrue(checker11Position.getY() < checker5Position.getY());
    }

    @Test
    public void should_position_12th_checker_as_checker_2_() {
        Point checker2Position = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 1); // Calculate Checker 2 position
        Point checker12Position = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, 11); // Calculate Checker 12 position

        // Check Checker position 2
        assertEquals(checker2Position.getX(), checker12Position.getX(), 0.01);
        assertEquals(checker2Position.getY(), checker12Position.getY(), 0.01);
    }

    @Test
    public void should_position_checkers_centered() {
        Rectangle r = new Rectangle(500, 217, 40, 200);
        for (int i = 0; i < 12; i++) {
            Point p0 = ps.calculateFigureCoordinatesIndexedForLocation(Location.R1, i);
            Rectangle checker = new Rectangle(p0, checkerDimension);
            assertTrue(r.contains(checker));
            assertEquals(r.getCenterX(), checker.getCenterX(), 1);
        }
    }

}
