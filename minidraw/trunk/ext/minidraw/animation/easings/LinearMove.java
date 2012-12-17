package minidraw.animation.easings;

import java.awt.*;

public class LinearMove implements EasingFunctionStrategy {

    @Override
    public Point calculate(double elapsedTime, Point begin, Point end) {
        double x = begin.x + (end.x - begin.x) * elapsedTime;
        double y = begin.y + (end.y - begin.y) * elapsedTime;
        return new Point((int) x, (int) y);
    }
}
