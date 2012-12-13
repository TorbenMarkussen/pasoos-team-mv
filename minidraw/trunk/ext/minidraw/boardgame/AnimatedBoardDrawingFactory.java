package minidraw.boardgame;

import minidraw.animation.engine.AnimationEngine;

public interface AnimatedBoardDrawingFactory<LOCATION> {

    FigureFactory<LOCATION> getFigureFactory();

    PositioningStrategy<LOCATION> getPositioningStrategy();

    PropAppearanceStrategy getAppearanceStrategy();

    AnimationEngine getAnimationEngine();

    MoveAnimationConfiguratorStrategy createAnimationConfigurator();

}
