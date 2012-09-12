package pasoos.hotgammon.gameengine;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;

public interface BoardState {
    Color getColor(Location location);

    int getCount(Location location);

    boolean hasAllInInnerTable(Color color);

    boolean hasAllCheckersOnBearOff(Color color);
}
