package pasoos.hotgammon.builder;

import minidraw.boardgame.BoardGameDrawing;
import minidraw.framework.Factory;
import pasoos.hotgammon.Location;

public interface ViewFactoryBuilder {
    void setBoardDrawing(BoardGameDrawing<Location> boardDrawing);

    Factory build();
}
