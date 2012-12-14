package pasoos.hotgammon.simplegame;

import minidraw.boardgame.BoardGameObserver;
import pasoos.hotgammon.GameObserver;
import pasoos.hotgammon.Location;

public class BoardObserverAdapter implements GameObserver {
    private BoardGameObserver<Location> boardGameObserver;

    public BoardObserverAdapter(BoardGameObserver<Location> boardGameObserver) {
        this.boardGameObserver = boardGameObserver;
    }

    @Override
    public void checkerMove(Location from, Location to) {
        boardGameObserver.pieceMovedEvent(from, to);
    }

    @Override
    public void diceRolled(int[] values) {
        boardGameObserver.propChangeEvent("die1");
        boardGameObserver.propChangeEvent("die2");
    }
}
