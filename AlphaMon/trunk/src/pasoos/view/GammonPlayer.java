package pasoos.view;

import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.Command;

public interface GammonPlayer extends Command {
    void setActive();

    void setInactive();

    void addStatusObserver(StatusObserver statusObserver);

    void addPiece(BoardPiece piece);
}
