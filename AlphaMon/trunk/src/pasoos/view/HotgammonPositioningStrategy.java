package pasoos.view;

import minidraw.boardgame.PositioningStrategy;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.minidraw_view.Convert;

import java.awt.*;

public class HotgammonPositioningStrategy implements PositioningStrategy<Location> {
    @Override
    public Point calculateFigureCoordinatesIndexedForLocation(Location location, int index) {
        return Convert.locationAndCount2xy(location, index);
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
