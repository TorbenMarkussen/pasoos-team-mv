package pasoos.hotgammon.ui;

import minidraw.framework.TimeInterval;

import java.awt.*;

public interface TimeIntervalStrategy {
    TimeInterval getTimeInterval();

    void updateTimeInterval(Point from, Point to);
}
