package minidraw.animation.easings;

import java.awt.*;

/*
Algorithm is inspired by http://13thparallel.com/archive/bezier-curves/
 */

public class BezierMovement implements EasingFunctionStrategy {
    private Point waypoint1;
    private Point waypoint2;

    public BezierMovement(Point waypoint1, Point waypoint2) {
        this.waypoint1 = waypoint1;
        this.waypoint2 = waypoint2;
    }

    @Override
    public Point calculate(double elapsedPercentage, Point beginPoint, Point endPoint) {
        return getBezier(elapsedPercentage, beginPoint, waypoint1, waypoint2, endPoint);
    }

    private double B1(double t) {
        return t * t * t;
    }

    private double B2(double t) {
        return 3 * t * t * (1 - t);
    }

    private double B3(double t) {
        return 3 * t * (1 - t) * (1 - t);
    }

    private double B4(double t) {
        return (1 - t) * (1 - t) * (1 - t);
    }

    private Point getBezier(double percent, Point begin, Point wp1, Point wp2, Point end) {
        double b1 = B1(percent);
        double b2 = B2(percent);
        double b3 = B3(percent);
        double b4 = B4(percent);
        double x = end.x * b1 + wp2.x * b2 + wp1.x * b3 + begin.x * b4;
        double y = end.y * b1 + wp2.y * b2 + wp1.y * b3 + begin.y * b4;
        return new Point((int) x, (int) y);
    }
}
