package pasoos.view;

import minidraw.boardgame.BoardDrawing;
import minidraw.boardgame.FigureFactory;
import minidraw.boardgame.PositioningStrategy;
import minidraw.boardgame.PropAppearanceStrategy;
import minidraw.framework.Factory;
import pasoos.hotgammon.Location;

public interface ViewFactoryBuilder {
    void setBoardDrawing(BoardDrawing<Location> boardDrawing);

    Factory build();
}
