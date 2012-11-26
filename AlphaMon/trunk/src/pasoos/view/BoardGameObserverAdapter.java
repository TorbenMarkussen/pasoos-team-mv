package pasoos.view;

import minidraw.boardgame.BoardGameObserver;
import pasoos.hotgammon.GameObserver;
import pasoos.hotgammon.Location;

public class BoardGameObserverAdapter implements GameObserver {
    private BoardGameObserver<Location> boardGameObserver;

    public BoardGameObserverAdapter(BoardGameObserver<Location> boardGameObserver) {
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

    @Override
    public void turnEnded() {
    }
}
