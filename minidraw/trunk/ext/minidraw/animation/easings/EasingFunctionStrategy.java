package minidraw.animation.easings;

import java.awt.*;

public interface EasingFunctionStrategy {
    Point calculate(double elapsedPercentage, Point begin, Point end);
}
