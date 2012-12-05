package minidraw.boardgame;

import minidraw.framework.*;

import java.awt.*;
import java.util.List;

public class AnimatedBoardDrawing<LOCATION> extends BoardDrawing<LOCATION> {
    private PositioningStrategy<LOCATION> positioningStrategy;
    private AnimationEngine aengine;

    public AnimatedBoardDrawing(FigureFactory<LOCATION> figureFactory,
                                PositioningStrategy<LOCATION> positioningStrategy,
                                PropAppearanceStrategy appearanceStrategy,
                                AnimationEngine aengine) {
        super(figureFactory, positioningStrategy, appearanceStrategy);
        this.positioningStrategy = positioningStrategy;
        this.aengine = aengine;
    }

    public void moveAnimated(final LOCATION from, final LOCATION to) {
        moveAnimated(from, to, new NullAnimationCallback<LOCATION>());
    }

    public void moveAnimated(final LOCATION from, final LOCATION to, final AnimationCallbacks<LOCATION> cb) {
        // Setup animation
        final BoardPiece piece = getPiece(from);
        piece.setZorder(10);
        Point destination = positioningStrategy.calculateFigureCoordinatesIndexedForLocation(to, getCount(to));
        Animation a = createAnimation(piece, destination);
        final AnimationCallbacks<LOCATION> cbLocal = (cb == null) ? new NullAnimationCallback<LOCATION>() : cb;

        // Make move
        cbLocal.beforeStart(from, to);
        startMove(piece, from);
        cbLocal.afterStart(from, to);
        a.addAnimationChangeListener(new AnimationChangeListener() {

            @Override
            public void onAnimationCompleted(AnimationChangeEvent ace) {
                cbLocal.beforeEnd(from, to);
                endMove(piece, to);
                cbLocal.afterEnd(from, to);
            }
        });
        aengine.startAnimation(a);
    }

    private Animation createAnimation(BoardPiece piece, Point destination) {

        BezierMovement bm = new BezierMovement(new Point(piece.displayBox().getLocation().x, 220), new Point(destination.x, 220));
        Animation animation = new MoveAnimation(piece, destination, TimeInterval.fromNow().duration(1000), bm);
        return animation;
    }

    public void startMove(BoardPiece piece, LOCATION location) {
        movingPieces.add(piece);
        List<BoardPiece> l = figureMap.get(location);
        if (l.contains(piece)) {
            l.remove(piece);
            adjustPieces(location);
        }
    }

    public void endMove(BoardPiece piece, LOCATION location) {
        movingPieces.remove(piece);
        figureMap.get(location).add(piece);
        adjustPieces(location);
    }
}
