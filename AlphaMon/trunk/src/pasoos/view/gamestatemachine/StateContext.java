package pasoos.view.gamestatemachine;

import minidraw.boardgame.AnimatedBoardDrawing;
import minidraw.framework.SoundResource;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;

public interface StateContext {
    Game getGame();

    void setState(StateId stateId);

    void updateStatus(GammonState state, String message);

    void rollDice();

    void notifyPieceMovedEvent(Location from, Location to);

    void notifyDiceRolled(int[] values);

    void setGame(Game game);

    String getWinnerName();

    SoundResource getSoundMachine();

    AnimatedBoardDrawing getBoard();
}
