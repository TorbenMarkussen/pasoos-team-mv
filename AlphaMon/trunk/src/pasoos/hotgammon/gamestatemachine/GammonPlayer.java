package pasoos.hotgammon.gamestatemachine;

import minidraw.boardgame.BoardPiece;

public interface GammonPlayer extends GammonState {

    void addPiece(BoardPiece piece);

}
