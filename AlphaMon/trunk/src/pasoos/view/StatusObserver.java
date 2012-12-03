package pasoos.view;

import pasoos.hotgammon.GameObserver;
import pasoos.hotgammon.Location;

public interface StatusObserver extends GameObserver {

    void updateStatus(String s);

    void checkerLogicalMove(Location from, Location to);
}
