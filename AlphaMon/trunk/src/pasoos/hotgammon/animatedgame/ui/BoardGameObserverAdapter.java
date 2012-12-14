package pasoos.hotgammon.animatedgame.ui;

import minidraw.boardgame.BoardGameObserver;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.animatedgame.ui.status.StatusObserver;

public class BoardGameObserverAdapter implements StatusObserver {
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
    public void updateStatus(String s) {
    }

}
