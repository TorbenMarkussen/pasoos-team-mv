package minidraw.boardgame;

import minidraw.framework.EasingFunctionStrategy;
import minidraw.framework.TimeInterval;

public interface AnimationConfiguration {
    void setTimeline(TimeInterval ti);

    TimeInterval getTimeInterval();

    EasingFunctionStrategy getEasingFunction();

    void setEasingFuntion(EasingFunctionStrategy easingFunction);
}
