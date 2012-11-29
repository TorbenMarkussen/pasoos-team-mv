package pasoos.view;

import minidraw.boardgame.BoardDrawing;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;

public interface StateContext {
    Game getGame();

    void setState(StateId stateId);

    State getState();

    void updateStatus(State state, String message);

    BoardDrawing<Location> getBoardDrawing();
}
