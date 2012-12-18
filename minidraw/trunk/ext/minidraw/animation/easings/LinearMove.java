package minidraw.animation.easings;

import java.awt.*;

public class LinearMove implements EasingFunctionStrategy {

    @Override
    public Point calculate(double elapsedPercentage, Point begin, Point end) {
        double x = begin.x + (end.x - begin.x) * elapsedPercentage;
        double y = begin.y + (end.y - begin.y) * elapsedPercentage;
        return new Point((int) x, (int) y);
    }
}
