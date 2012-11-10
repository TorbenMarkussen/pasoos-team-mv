package snakesladders.view;

import minidraw.boardgame.PositioningStrategy;
import snakesladders.domain.Square;

import java.awt.*;

public class SnakesAndLaddersPositioningStrategy implements PositioningStrategy<Square> {

    public Point calculateFigureCoordinatesIndexedForLocation(Square location, int index) {
        int sqindex = location.index();
        // Note the tricky board layout - what is square 18 is visually
        // square 19!!!
        if (sqindex > 17) {
            sqindex++;
        }
        // calculate the row and column
        int row = (sqindex - 1) / 7;
        int column = (sqindex - 1) % 7;
        return new Point(20 + column * 92 + index * 20, 400 - row * 92);
    }

    public Point calculateFigureCoordinatesForProps(String keyOfProp) {
        if (keyOfProp.equals(Constant.diePropName)) {
            return new Point(690, 40);
        }
        return null;
    }
}
