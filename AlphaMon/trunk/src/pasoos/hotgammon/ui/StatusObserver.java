package pasoos.hotgammon.ui;

import pasoos.hotgammon.GameObserver;

public interface StatusObserver extends GameObserver {

    void updateStatus(String s);

}
