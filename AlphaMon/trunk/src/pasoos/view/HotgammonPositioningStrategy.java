package pasoos.view;

import minidraw.boardgame.PositioningStrategy;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.minidraw_view.Convert;

import java.awt.*;

public class HotgammonPositioningStrategy implements PositioningStrategy<Location> {
    private static final int FIRST_LAYER_LIMIT = 5;

    @Override
    public Point calculateFigureCoordinatesIndexedForLocation(Location location, int index) {
        Point p = Convert.locationAndCount2xy(location, index);
        if (index > FIRST_LAYER_LIMIT) {
            Point hp = Convert.locationAndCount2xy(location, index - 1);
            int pointDistance = p.y - hp.y;
            p = Convert.locationAndCount2xy(location, index - FIRST_LAYER_LIMIT);
            p = new Point(p.x, p.y - pointDistance / 2);
        }
        return p;
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
