package minidraw.animation;

import minidraw.animation.Animation;

import java.util.EventObject;

public class AnimationChangeEvent extends EventObject {
    public AnimationChangeEvent(Animation source) {
        super(source);
    }

    @Override
    public Animation getSource() {
        return (Animation) super.getSource();
    }
}
