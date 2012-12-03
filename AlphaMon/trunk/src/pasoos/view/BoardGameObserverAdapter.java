package pasoos.view;

import minidraw.boardgame.BoardGameObserver;
import pasoos.hotgammon.Location;

public class BoardGameObserverAdapter implements StatusObserver {
    private BoardGameObserver<Location> boardGameObserver;

    public BoardGameObserverAdapter(BoardGameObserver<Location> boardGameObserver) {
        this.boardGameObserver = boardGameObserver;
    }

    @Override
    public void checkerMove(Location from, Location to) {
        boardGameObserver.pieceMovedEvent(from, to);
        boardGameObserver.propChangeEvent("die1");
        boardGameObserver.propChangeEvent("die2");
    }

    @Override
    public void diceRolled(int[] values) {
        boardGameObserver.propChangeEvent("die1");
        boardGameObserver.propChangeEvent("die2");
    }

    @Override
    public void updateStatus(String s) {
    }
}
