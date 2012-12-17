package minidraw.animation.easings;

import java.awt.*;

public interface EasingFunctionStrategy {
    Point calculate(double elapsedTime, Point begin, Point end);
}
