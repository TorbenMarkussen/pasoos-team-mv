package minidraw.boardgame;

import minidraw.framework.AnimationEngine;

import java.awt.*;

public interface AnimatedBoardDrawingFactory<LOCATION> {

    FigureFactory<LOCATION> getFigureFactory();

    PositioningStrategy<LOCATION> getPositioningStrategy();

    PropAppearanceStrategy getAppearanceStrategy();

    AnimationEngine getAnimationEngine();

    void configureAnimation(Point from, Point to, AnimationConfiguration cfg);
}
