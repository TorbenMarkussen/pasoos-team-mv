package pasoos.view;

import minidraw.boardgame.PositioningStrategy;
import pasoos.hotgammon.Location;
import pasoos.physics.Convert;

import java.awt.*;

public class HotgammonPositioningStrategy implements PositioningStrategy<Location> {
    private static final int FIRST_LAYER_LIMIT = 5;   // The last index in layer 1
    private static final int SECOND_LAYER_LIMIT = 10;  // The last index in layer 2

    @Override
    public Point calculateFigureCoordinatesIndexedForLocation(Location location, int index) {
        Point result;
        if (index > SECOND_LAYER_LIMIT) {                                                                   // Layer 3
            result = Convert.locationAndCount2xy(location, index - SECOND_LAYER_LIMIT);
        } else if (index > FIRST_LAYER_LIMIT) {                                                             // Layer 2
            // Find the positions the result should be between
            Point positionBelow1 = Convert.locationAndCount2xy(location, index - FIRST_LAYER_LIMIT);        // Find one position
            Point positionBelow2 = Convert.locationAndCount2xy(location, index - FIRST_LAYER_LIMIT - 1);    // Find the other position
            int pointDistance = positionBelow1.y - positionBelow2.y;                                        // Find Delta y
            result = new Point(positionBelow1.x, positionBelow1.y - pointDistance / 2);                     // Create the result position
        } else {
            result = Convert.locationAndCount2xy(location, index);                                          // Layer 1
        }
        return result;
    }

    @Override
    public Point calculateFigureCoordinatesForProps(String keyOfProp) {
        if (keyOfProp.equals("die1")) {
            return new Point(216 + 0 * 90, 202);
        } else if (keyOfProp.equals("die2")) {
            return new Point(216 + 1 * 90, 202);
        }
        return null;
    }
}
