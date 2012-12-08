package pasoos.hotgammon.ui;

import minidraw.framework.BezierMovement;
import minidraw.framework.EasingFunctionStrategy;
import minidraw.framework.LinearMove;

import java.awt.*;

public class HotgammonEasingFunctionStrategyFactoryMethod {

    private EasingFunctionStrategy preparedEasingFunction;

    public HotgammonEasingFunctionStrategyFactoryMethod() {
        preparedEasingFunction = new LinearMove();
    }

    public void create(Point from, Point to) {
        preparedEasingFunction = new BezierMovement(getWayPoint1(from, to), getWayPoint2(from, to));
    }

    public EasingFunctionStrategy getEasingFunction(Point from, Point to) {
        return new BezierMovement(getWayPoint1(from, to), getWayPoint2(from, to));
    }

    public EasingFunctionStrategy getEasingFunction() {
        return preparedEasingFunction;
    }

    private Point getWayPoint2(Point from, Point to) {
        return new Point(to.x, getY(from, to));
    }

    private Point getWayPoint1(Point from, Point to) {
        return new Point(from.x, getY(from, to));
    }

    private int getY(Point from, Point to) {
        return 220;
        //int deltaY = Math.abs(from.y - to.y);
        //int minY = Math.min(from.y, to.y);
        //int yMove = getMinimumYMove(deltaY, minY);
        //return (minY + Math.max(deltaY / 2, yMove));
    }

    private int getMinimumYMove(int deltaY, int minY) {
        int MIN_Y_MOVE = 100;
        if (deltaY / 2 < MIN_Y_MOVE) {
            if (minY < 220)
                return MIN_Y_MOVE;
            else
                return -MIN_Y_MOVE;
        } else
            return deltaY / 2;
    }
}

