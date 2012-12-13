package minidraw.animation.easings;

import java.awt.*;

public interface EasingFunctionStrategy {
    Point calculate(double t, Point begin, Point end);
}
