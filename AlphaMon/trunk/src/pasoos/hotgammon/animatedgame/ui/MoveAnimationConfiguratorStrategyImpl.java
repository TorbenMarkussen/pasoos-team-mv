package pasoos.hotgammon.animatedgame.ui;

import minidraw.animatedboard.AnimationConfiguration;
import minidraw.animatedboard.MoveAnimationConfiguratorStrategy;
import minidraw.animation.easings.BezierMovement;
import minidraw.animation.easings.EasingFunctionStrategy;
import minidraw.animation.TimeInterval;
import pasoos.hotgammon.boardphysics.BoardPhysics;

import java.awt.*;

public class MoveAnimationConfiguratorStrategyImpl implements MoveAnimationConfiguratorStrategy {

    Rectangle board = BoardPhysics.board();

    @Override
    public void configureAnimation(Point from, Point to, AnimationConfiguration cfg) {
        cfg.setTimeline(createTimeInterval(from, to));
        cfg.setEasingFuntion(createEasingFunction(from, to));
    }

    private EasingFunctionStrategy createEasingFunction(Point from, Point to) {
        return new BezierMovement(createWayPoint(from), createWayPoint(to));
    }

    private Point createWayPoint(Point point) {
        return new Point(point.x, board.height / 2);
    }

    private TimeInterval createTimeInterval(Point from, Point to) {
        return TimeInterval.fromNow().duration(calculateIntervalDuration(from, to));
    }

    private int calculateIntervalDuration(Point from, Point to) {
        int deltaX = Math.abs(from.x - to.x);
        int deltaY = Math.max(Math.abs(from.y - to.y), board.height / 2);
        int hypotenuse = (int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        return (hypotenuse * 3);
    }
}
