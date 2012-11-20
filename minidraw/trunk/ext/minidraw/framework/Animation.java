package minidraw.framework;

public interface Animation {

    Figure getFigure();

    boolean isStartable();

    boolean isCompleted();

    void begin();

    void step();

    void end();

    void abort();

    void addAnimationChangeListener(AnimationChangeListener changeListener);

    void removeAnimationChangeListener(AnimationChangeListener changeListener);

}
