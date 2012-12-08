package pasoos.hotgammon.ui;

import minidraw.framework.TimeInterval;

import java.awt.*;

public class HotgammonTimeIntervalStrategy implements TimeIntervalStrategy {
    private long intervalDuration = 1000;

    @Override
    public TimeInterval getTimeInterval() {
        return TimeInterval.fromNow().duration(intervalDuration);
    }

    @Override
    public void updateTimeInterval(Point from, Point to) {
        int deltaX = Math.abs(from.x - to.x);
        int deltaY = Math.max(Math.abs(from.y - to.y), 220);
        int hypotenuse = (int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        setIntervalDuration(hypotenuse * 7);
    }

    public void setIntervalDuration(long duration) {
        intervalDuration = duration;
    }
}
