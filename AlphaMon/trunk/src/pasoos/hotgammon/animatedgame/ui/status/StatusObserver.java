package pasoos.hotgammon.animatedgame.ui.status;

import pasoos.hotgammon.GameObserver;

public interface StatusObserver extends GameObserver {

    void updateStatus(String s);
}
