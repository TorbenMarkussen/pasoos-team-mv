package pasoos.view.gamestatemachine;

import minidraw.boardgame.BoardDrawing;
import minidraw.framework.Animation;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;

public interface StateContext {
    Game getGame();

    void setState(StateId stateId);

    State getState();

    void updateStatus(State state, String message);

    BoardDrawing<Location> getBoardDrawing();

    void startAnimation(Animation a, Location from, Location to);

    void rollDice();

    void notifyPieceMovedEvent(Location from, Location to);

    void notifyDiceRolled(int[] values);

    void setGame(Game game);
}
