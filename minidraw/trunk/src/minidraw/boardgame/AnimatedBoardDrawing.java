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

    public void moveAnimated(final LOCATION from, final LOCATION to, final AnimationCallbacks cb) {
        // Setup animation
        final BoardPiece piece = getPiece(from);
        Point destination = positioningStrategy.calculateFigureCoordinatesIndexedForLocation(to, getCount(to));
        Animation a = createAnimation(piece, destination);
        // Make move
        cb.beforeStart(from, to);
        startMove(piece, from);
        cb.afterStart(from, to);
        a.addAnimationChangeListener(new AnimationChangeListener() {

            @Override
            public void onAnimationCompleted(AnimationChangeEvent ace) {
                cb.beforeEnd(from, to);
                endMove(piece, to);
                cb.afterEnd(from, to);
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


    /* ToDo
    . Make Animation setup
    - Find To Index


     */
}
