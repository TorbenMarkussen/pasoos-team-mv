package minidraw.framework;

import java.awt.*;

public interface EasingFunctionStrategy {
    Point calculate(double t, Point begin, Point end);
}
