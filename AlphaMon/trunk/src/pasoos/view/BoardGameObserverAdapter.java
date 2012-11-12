package pasoos.view;

import minidraw.boardgame.BoardDrawing;
import pasoos.hotgammon.GameObserver;
import pasoos.hotgammon.Location;

public class BoardGameObserverAdapter implements GameObserver {
    private BoardDrawing<Location> drawing;

    public BoardGameObserverAdapter(BoardDrawing<Location> drawing) {
        this.drawing = drawing;
    }

    @Override
    public void checkerMove(Location from, Location to) {
        drawing.pieceMovedEvent(from, to);
    }

    @Override
    public void diceRolled(int[] values) {
        drawing.propChangeEvent("die1");
        drawing.propChangeEvent("die2");
    }
}
