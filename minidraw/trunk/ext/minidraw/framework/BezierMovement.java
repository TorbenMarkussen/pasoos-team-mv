package minidraw.framework;

import java.awt.*;

public class BezierMovement implements EasingFunctionStrategy {
    private Point waypoint1;
    private Point waypoint2;

    public BezierMovement(Point waypoint1, Point waypoint2) {
        this.waypoint1 = waypoint1;
        this.waypoint2 = waypoint2;
    }

    @Override
    public Point calculate(double t, Point beginPoint, Point endPoint) {
        return getBezier(t, beginPoint, waypoint1, waypoint2, endPoint);
    }

    private Point calculate(double t, Point begin, Point wp1, Point wp2, Point end) {
        double x, y;
        //uses Berstein polynomials
        x = (begin.x + t * (-begin.x * 3 + t * (3 * begin.x - begin.x * t))) + t * (3 * wp1.x + t * (-6 * wp1.x +
                wp1.x * 3 * t)) + t * t * (wp2.x * 3 - wp2.x * 3 * t) +
                end.x * t * t * t;
        y = (begin.y + t * (-begin.y * 3 + t * (3 * begin.y -
                begin.y * t))) + t * (3 * wp1.y + t * (-6 * wp1.y +
                wp1.y * 3 * t)) + t * t * (wp2.y * 3 - wp2.y * 3 * t) +
                end.y * t * t * t;
        return new Point((int) x, (int) y);
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
