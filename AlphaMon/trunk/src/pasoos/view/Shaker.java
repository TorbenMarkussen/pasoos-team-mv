package pasoos.view;

import minidraw.framework.EasingFunctionStrategy;

import java.awt.*;
import java.util.Random;

public class Shaker implements EasingFunctionStrategy {
    private Random random;

    public Shaker() {
        random = new Random();
    }

    @Override
    public Point calculate(double t, Point begin, Point end) {
        if (t >= 1)
            return end;
        int dx = -5 + random.nextInt(10);
        int dy = -5 + random.nextInt(10);
        return new Point(begin.x + dx, begin.y + dy);
    }
}
