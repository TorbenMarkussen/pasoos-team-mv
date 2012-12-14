package pasoos.hotgammon.simplegame;

import minidraw.boardgame.PositioningStrategy;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.animatedgame.ui.HotgammonPositioningStrategy;

import java.awt.*;

public class CheckerPositioningStrategy implements PositioningStrategy<Location> {

    PositioningStrategy<Location> ps = new HotgammonPositioningStrategy();

    @Override
    public Point calculateFigureCoordinatesIndexedForLocation(Location location, int index) {
        return ps.calculateFigureCoordinatesIndexedForLocation(location, index);
    }

    @Override
    public Point calculateFigureCoordinatesForProps(String keyOfProp) {
        return ps.calculateFigureCoordinatesForProps(keyOfProp);
    }
}
