package pasoos.hotgammon.boardphysics;

import pasoos.hotgammon.Location;

import java.awt.*;

public class Convert {
    private static final Dimension checkerDimension = CheckerPhysics.getDimension();

    /**
     * Given coordinate (x,y) on the graphical backgammon board
     * return the location it is witin, or null if not on any
     * location.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public static Location xy2Location(int x, int y) {
        return BoardPhysics.map2Location(x, y);
    }

    /**
     * Given a  location and a count of checkers on this location,
     * compute the (x,y) position of a properly positioned checker
     * (i.e. properly aligned).
     *
     * @param location the location on the board
     * @param count    the number of checkers already on this location
     */
    public static Point locationAndCount2xy(Location location, int count) {
        Rectangle box = BoardPhysics.getRectangle(location);

        int newx = box.x + (box.width - checkerDimension.width) / 2;

        int newy;

        int checkerHeight = checkerDimension.height;

        if (isOnBlackSide(location)) {
            newy = checkerHeight * count + box.y;
        } else if (location == Location.R_BEAR_OFF) {
            newy = (box.y + box.height) - (checkerHeight * (count + 2));
        } else {
            newy = (box.y + box.height) - (checkerHeight * (count + 1));
        }
        return new Point(newx, newy);
    }

    private static boolean isBearOff(Location location) {
        return location == Location.B_BEAR_OFF || location == Location.R_BEAR_OFF;
    }

    private static boolean isOnBlackSide(Location location) {
        return location == Location.B1 ||
                location == Location.B2 ||
                location == Location.B3 ||
                location == Location.B4 ||
                location == Location.B5 ||
                location == Location.B6 ||
                location == Location.B7 ||
                location == Location.B8 ||
                location == Location.B9 ||
                location == Location.B10 ||
                location == Location.B11 ||
                location == Location.B12 ||
                location == Location.B12 ||
                location == Location.B_BEAR_OFF ||
                location == Location.R_BAR;
    }


}