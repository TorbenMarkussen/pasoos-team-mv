package pasoos.hotgammon.ui;

import minidraw.boardgame.*;
import minidraw.framework.*;
import pasoos.hotgammon.Location;

import java.awt.*;

public class HotgammonAnimatedBoardDrawingFactory implements AnimatedBoardDrawingFactory<Location> {

    private FigureFactory<Location> figureFactory;
    private final PropAppearanceStrategy propAppearanceStrategy;
    private final AnimationEngine animationEngine;

    public HotgammonAnimatedBoardDrawingFactory(FigureFactory<Location> figureFactory, PropAppearanceStrategy propAppearanceStrategy, AnimationEngine animationEngine) {
        this.figureFactory = figureFactory;
        this.propAppearanceStrategy = propAppearanceStrategy;
        this.animationEngine = animationEngine;
    }

    @Override
    public void configureAnimation(Point from, Point to, AnimationConfiguration cfg) {
        cfg.setTimeline(getTimeInterval(from, to));
        cfg.setEasingFuntion(createEasingFunction(from, to));
    }

    private EasingFunctionStrategy createEasingFunction(Point from, Point to) {
        return new BezierMovement(getWayPoint(from), getWayPoint(to));
    }

    private Point getWayPoint(Point point) {
        return new Point(point.x, 220);
    }

    private TimeInterval getTimeInterval(Point from, Point to) {
        return TimeInterval.fromNow().duration(calculateIntervalDuration(from, to));
    }

    private int calculateIntervalDuration(Point from, Point to) {
        int deltaX = Math.abs(from.x - to.x);
        int deltaY = Math.max(Math.abs(from.y - to.y), 220);
        int hypotenuse = (int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        return (hypotenuse * 7);
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

}
