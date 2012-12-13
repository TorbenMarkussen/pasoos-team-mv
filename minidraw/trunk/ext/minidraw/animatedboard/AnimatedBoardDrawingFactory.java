package minidraw.animatedboard;

import minidraw.animation.engine.AnimationEngine;
import minidraw.boardgame.FigureFactory;
import minidraw.boardgame.PositioningStrategy;
import minidraw.boardgame.PropAppearanceStrategy;

public interface AnimatedBoardDrawingFactory<LOCATION> {

    FigureFactory<LOCATION> getFigureFactory();

    PositioningStrategy<LOCATION> getPositioningStrategy();

    PropAppearanceStrategy getAppearanceStrategy();

    AnimationEngine getAnimationEngine();

    MoveAnimationConfiguratorStrategy createAnimationConfigurator();

}
