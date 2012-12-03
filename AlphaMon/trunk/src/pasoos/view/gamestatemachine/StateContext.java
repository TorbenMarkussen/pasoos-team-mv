package pasoos.view.gamestatemachine;

import minidraw.boardgame.AnimatedBoardDrawing;
import minidraw.boardgame.BoardPiece;
import minidraw.framework.Animation;
import minidraw.framework.SoundResource;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.minidraw_controller.GammonMove;

public interface StateContext {
    Game getGame();

    void setState(StateId stateId);

    void updateStatus(GammonState state, String message);

    void startAnimation(Animation a, BoardPiece piece, GammonMove move);

    void rollDice();

    void notifyPieceMovedEvent(Location from, Location to);

    void notifyDiceRolled(int[] values);

    void setGame(Game game);

    String getWinnerName();

    SoundResource getSoundMachine();

    BoardPiece getPieceFromBoard(Location location);

    void notifyLogicalMove(Location from, Location to);

    void startMove(BoardPiece piece, Location location);

    void endMove(BoardPiece piece, Location location);

    AnimatedBoardDrawing getBoard();
}
