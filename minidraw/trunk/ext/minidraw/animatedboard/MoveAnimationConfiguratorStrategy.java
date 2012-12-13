package minidraw.animatedboard;

import java.awt.*;

public interface MoveAnimationConfiguratorStrategy {
    void configureAnimation(Point from, Point to, AnimationConfiguration cfg);
}
