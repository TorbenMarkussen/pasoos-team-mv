package minidraw.framework;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveAnimation implements Animation {
    protected Point beginPoint;
    protected Point endPoint;
    protected Figure figure;
    private Point current;
    protected final TimeInterval timeInterval;
    private List<AnimationChangeListener> listeners = new ArrayList<AnimationChangeListener>();
    private EasingFunctionStrategy easingFunction;

    public MoveAnimation(Figure f, Point endPoint, TimeInterval tl, EasingFunctionStrategy easingFunction) {
        this.timeInterval = tl;
        this.figure = f;
        this.easingFunction = easingFunction;
        beginPoint = f.displayBox().getLocation();
        this.endPoint = endPoint;
    }

    @Override
    public void begin() {
        current = beginPoint;
    }

    @Override
    public void step() {
        double t = timeInterval.elapsed();
        Point p = easingFunction.calculate(t, beginPoint, endPoint);
        figure.moveBy(p.x - current.x, p.y - current.y);
        current = p;
    }

    @Override
    public Figure getFigure() {
        return figure;
    }

    @Override
    public boolean isStartable() {
        return timeInterval.isAfterStart(timeInterval.now());
    }

    @Override
    public boolean isCompleted() {
        return timeInterval.isAfterEnd(timeInterval.now());
    }

    @Override
    public void end() {
        if (listeners.size() > 0) {
            for (AnimationChangeListener l : listeners) {
                l.onAnimationCompleted(new AnimationChangeEvent(this));
            }
        }
    }

    public void addAnimationCallback(AnimationChangeListener changeListener) {
        listeners.add(changeListener);
    }

    public void removeAnimationCallback(AnimationChangeListener changeListener) {
        listeners.remove(changeListener);
    }

    public void setBeginPoint(Point begin) {
        this.beginPoint = begin;
    }

    public void setEndPoint(Point end) {
        this.endPoint = end;
    }

    @Override
    public void abort() {
    }

}
