package minidraw.framework;

import java.awt.*;

public class BezierAnimation implements Animation {
    private Point beginPoint;
    private Point endPoint;
    private Point waypoint1;
    private Point waypoint2;
    private Figure figure;
    private long startTime;
    private double t;
    private boolean completed;
    private Point current;
    private AnimationCallback callback = null;

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public void begin() {
        startTime = System.currentTimeMillis();
        t = 0;
        completed = false;
        current = beginPoint;
    }

    @Override
    public void run() {
        t = (System.currentTimeMillis() - startTime) / 1000.0;
        if (t >= 1) {
            completed = true;
            t = 1;
        }
        Point p = bezierAnimate(t);
        figure.moveBy(p.x - current.x, p.y - current.y);
        current = p;
    }

    @Override
    public void end() {
        if (callback != null)
            callback.onAnimationCompleted(this);
    }

    public void setBeginPoint(Point begin) {
        this.beginPoint = begin;
    }

    public void setEndPoint(Point end) {
        this.endPoint = end;
    }

    public void setWaypoints(Point waypoint1, Point waypoint2) {
        this.waypoint1 = waypoint1;
        this.waypoint2 = waypoint2;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    @Override
    public Figure getFigure() {
        return figure;
    }

    @Override
    public void abort() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private Point bezierAnimate(double t) {
        double x, y;
        //uses Berstein polynomials
        x = (beginPoint.x + t * (-beginPoint.x * 3 + t * (3 * beginPoint.x -
                beginPoint.x * t))) + t * (3 * waypoint1.x + t * (-6 * waypoint1.x +
                waypoint1.x * 3 * t)) + t * t * (waypoint2.x * 3 - waypoint2.x * 3 * t) +
                endPoint.x * t * t * t;
        y = (beginPoint.y + t * (-beginPoint.y * 3 + t * (3 * beginPoint.y -
                beginPoint.y * t))) + t * (3 * waypoint1.y + t * (-6 * waypoint1.y +
                waypoint1.y * 3 * t)) + t * t * (waypoint2.y * 3 - waypoint2.y * 3 * t) +
                endPoint.y * t * t * t;
        return new Point((int) x, (int) y);
    }

    public void setAnimationCallback(AnimationCallback callback) {
        this.callback = callback;
    }
}
