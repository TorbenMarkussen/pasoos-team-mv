package minidraw.framework;

import java.awt.*;

public interface Animation {

    Figure getFigure();

    boolean isStartable();

    boolean isCompleted();

    void begin();

    void step();

    void end();

    void abort();

    void addAnimationCallback(AnimationChangeListener changeListener);

    void removeAnimationCallback(AnimationChangeListener changeListener);

}
