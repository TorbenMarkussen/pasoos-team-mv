package minidraw.boardgame;

import minidraw.framework.AnimationEngine;
import minidraw.framework.EasingFunctionStrategy;
import minidraw.framework.TimeInterval;

import java.awt.*;

public interface AnimatedBoardDrawingFactory<LOCATION> {

    FigureFactory<LOCATION> getFigureFactory();

    PositioningStrategy<LOCATION> getPositioningStrategy();

    PropAppearanceStrategy getAppearanceStrategy();

    AnimationEngine getAnimationEngine();

    TimeInterval getTimeInterval();

    EasingFunctionStrategy getEasingFunction();

    void updateEasingFunction(Point from, Point to);

    void updateTimeInterval(Point from, Point to);
}
