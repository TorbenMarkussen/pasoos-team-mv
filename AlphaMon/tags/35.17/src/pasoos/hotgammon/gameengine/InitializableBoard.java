package pasoos.hotgammon.gameengine;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;

public interface InitializableBoard {
    void clear();

    void set(Location location, Color color, int count);
}
