package pasoos.hotgammon.gameengine;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;

public interface Board extends BoardState {
    void initialize();

    void decrementLocation(Location location, Color color);

    void incrementLocation(Location location, Color color);

    boolean removeCheckersWithColor(Location location, Color color);

    void incrementBar(Color barColor);

}
