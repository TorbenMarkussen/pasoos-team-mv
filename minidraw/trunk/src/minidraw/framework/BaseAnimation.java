package minidraw.framework;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAnimation implements Animation {
    protected Figure figure;
    protected final TimeInterval timeInterval;
    private List<AnimationChangeListener> listeners = new ArrayList<AnimationChangeListener>();

    protected BaseAnimation(Figure f, TimeInterval tl) {
        figure = f;
        timeInterval = tl;
    }

    @Override
    public boolean isStartable() {
        return timeInterval.isAfterStart(timeInterval.now());
    }

    @Override
    public boolean isCompleted() {
        return timeInterval.isAfterEnd(timeInterval.now());
    }

    public void addAnimationChangeListener(AnimationChangeListener changeListener) {
        listeners.add(changeListener);
    }

    public void removeAnimationChangeListener(AnimationChangeListener changeListener) {
        listeners.remove(changeListener);
    }

    @Override
    public Figure getFigure() {
        return figure;
    }

    @Override
    public void abort() {
    }

    @Override
    public void end() {
        if (listeners.size() > 0) {
            for (AnimationChangeListener l : listeners) {
                l.onAnimationCompleted(new AnimationChangeEvent(this));
            }
        }
    }

}
