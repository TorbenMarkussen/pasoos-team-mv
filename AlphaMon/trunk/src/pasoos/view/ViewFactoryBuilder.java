package pasoos.view;

import minidraw.boardgame.IBoardDrawing;
import minidraw.framework.Factory;
import pasoos.hotgammon.Location;

public interface ViewFactoryBuilder {
    void setBoardDrawing(IBoardDrawing<Location> boardDrawing);

    Factory build();
}
