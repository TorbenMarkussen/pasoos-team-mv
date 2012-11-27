package pasoos.view;

import pasoos.hotgammon.Game;

public interface StateContext {
    Game getGame();

    void setState(GammonState state);

    State getState();
}
