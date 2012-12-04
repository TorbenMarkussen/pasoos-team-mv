package minidraw.boardgame;

import minidraw.framework.Drawing;

public interface IBoardDrawing<LOCATION> extends Drawing {
    BoardPiece getPiece(LOCATION location);

    BoardPiece findPiece(int x, int y);

}
