package pasoos.hotgammon.ui;

import minidraw.animatedboard.AnimationConfiguration;
import minidraw.animatedboard.MoveAnimationConfiguratorStrategy;
import minidraw.animation.easings.BezierMovement;
import minidraw.animation.easings.EasingFunctionStrategy;
import minidraw.animation.TimeInterval;

import java.awt.*;

public class MoveAnimationConfiguratorStrategyImpl implements MoveAnimationConfiguratorStrategy {

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
        return (hypotenuse * 3);
    }
}
