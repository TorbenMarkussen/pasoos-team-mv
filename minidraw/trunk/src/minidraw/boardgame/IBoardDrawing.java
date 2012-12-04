package minidraw.boardgame;

import minidraw.framework.Drawing;

public interface IBoardDrawing<LOCATION> extends Drawing {
    void pieceMovedEvent(LOCATION from, LOCATION to);

    void propChangeEvent(String key);

    BoardPiece getPiece(LOCATION location);

    BoardPiece findPiece(int x, int y);

    void pieceLogicalMove(LOCATION from, LOCATION to);

}
