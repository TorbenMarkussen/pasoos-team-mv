package minidraw.animatedboard;

import minidraw.animation.easings.EasingFunctionStrategy;
import minidraw.animation.TimeInterval;

public interface AnimationConfiguration {
    void setTimeline(TimeInterval ti);

    TimeInterval getTimeInterval();

    EasingFunctionStrategy getEasingFunction();

    void setEasingFuntion(EasingFunctionStrategy easingFunction);
}
