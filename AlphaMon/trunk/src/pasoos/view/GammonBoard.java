package pasoos.view;

import minidraw.boardgame.*;
import minidraw.framework.*;
import pasoos.hotgammon.Location;
import pasoos.physics.Convert;

import java.awt.*;

public class GammonBoard extends BoardDrawing<Location> {
    private AnimationEngine aengine;

    public GammonBoard(FigureFactory<Location> figureFactory,
                       PositioningStrategy<Location> positioningStrategy,
                       PropAppearanceStrategy appearanceStrategy,
                       AnimationEngine aengine) {
        super(figureFactory, positioningStrategy, appearanceStrategy);
        this.aengine = aengine;
    }

    public void moveAnimated(final Location from, final Location to, final AnimationChangeListener changeListener, final AnimationChangeListener cl2) {
        final BoardPiece piece = getPiece(from);
        Point destination = Convert.locationAndCount2xy(to, 0);
        TimeInterval timeInterval = TimeInterval.fromNow().duration(1000);
        EasingFunctionStrategy ef = new LinearMove();
        Animation a = new MoveAnimation(piece, destination, timeInterval, ef);
        startMove(piece, from);
        a.addAnimationChangeListener(new AnimationChangeListener() {
            @Override
            public void onAnimationCompleted(AnimationChangeEvent ace) {
                if (changeListener != null)
                    changeListener.onAnimationCompleted(ace);
                endMove(piece, to);
                if (cl2 != null)
                    cl2.onAnimationCompleted(ace);
            }
        });
        aengine.startAnimation(a);
    }

}
