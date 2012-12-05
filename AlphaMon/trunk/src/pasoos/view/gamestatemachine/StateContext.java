package pasoos.view.gamestatemachine;

import minidraw.boardgame.AnimatedBoardDrawing;
import minidraw.framework.SoundResource;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.view.StatusObserver;

public interface StateContext {
    Game getGame();

    void setState(StateId stateId);

    void updateStatus(GammonState state, String message);

    void rollDice();

    void notifyPieceMovedEvent(Location from, Location to);

    void notifyDiceRolled(int[] values);

    String getWinnerName();

    SoundResource getSoundMachine();

    AnimatedBoardDrawing<Location> getBoard();

    void addStatusObserver(StatusObserver statusObserver);

    GammonState getCurrentState();
}
