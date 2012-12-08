package pasoos.hotgammon.ui;

import minidraw.boardgame.AnimatedBoardDrawingFactory;
import minidraw.boardgame.FigureFactory;
import minidraw.boardgame.PositioningStrategy;
import minidraw.boardgame.PropAppearanceStrategy;
import minidraw.framework.*;
import pasoos.hotgammon.Location;

import java.awt.*;

public class HotgammonAnimatedBoardDrawingFactory implements AnimatedBoardDrawingFactory<Location> {

    private FigureFactory<Location> figureFactory;
    private final PropAppearanceStrategy propAppearanceStrategy;
    private final AnimationEngine animationEngine;
    private HotgammonEasingFunctionStrategyFactoryMethod easingFunction;
    private TimeIntervalStrategy timeIntervalStrategy;

    public HotgammonAnimatedBoardDrawingFactory(FigureFactory<Location> figureFactory, PropAppearanceStrategy propAppearanceStrategy, AnimationEngine animationEngine) {
        this.figureFactory = figureFactory;
        this.propAppearanceStrategy = propAppearanceStrategy;
        this.animationEngine = animationEngine;
        timeIntervalStrategy = new HotgammonTimeIntervalStrategy();
        easingFunction = new HotgammonEasingFunctionStrategyFactoryMethod();
    }

    @Override
    public void updateEasingFunction(Point from, Point to) {
        easingFunction.create(from, to);
    }

    @Override
    public void updateTimeInterval(Point from, Point to) {
        timeIntervalStrategy.updateTimeInterval(from, to);
    }

    @Override
    public EasingFunctionStrategy getEasingFunction() {
        return easingFunction.getEasingFunction();
    }

    public FigureFactory<Location> getFigureFactory() {
        return figureFactory;
    }

    public PositioningStrategy<Location> getPositioningStrategy() {
        return new HotgammonPositioningStrategy();
    }

    public PropAppearanceStrategy getAppearanceStrategy() {
        return propAppearanceStrategy;
    }

    public AnimationEngine getAnimationEngine() {
        return animationEngine;
    }

    @Override
    public TimeInterval getTimeInterval() {
        return timeIntervalStrategy.getTimeInterval();
    }

}
