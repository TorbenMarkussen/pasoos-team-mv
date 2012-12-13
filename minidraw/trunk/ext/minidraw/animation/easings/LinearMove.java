package minidraw.animation.easings;

import minidraw.animation.easings.EasingFunctionStrategy;

import java.awt.*;

public class LinearMove implements EasingFunctionStrategy {

    @Override
    public Point calculate(double t, Point begin, Point end) {
        double x = begin.x + (end.x - begin.x) * t;
        double y = begin.y + (end.y - begin.y) * t;
        return new Point((int) x, (int) y);
    }
}
