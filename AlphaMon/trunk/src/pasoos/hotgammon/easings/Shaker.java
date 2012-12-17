package pasoos.hotgammon.easings;

import minidraw.animation.easings.EasingFunctionStrategy;

import java.awt.*;
import java.util.Random;

public class Shaker implements EasingFunctionStrategy {
    private Random random;

    public Shaker() {
        random = new Random();
    }

    @Override
    public Point calculate(double elapsedTime, Point begin, Point end) {
        if (elapsedTime >= 1)
            return end;
        int dx = -5 + random.nextInt(10);
        int dy = -5 + random.nextInt(10);
        return new Point(begin.x + dx, begin.y + dy);
    }
}
