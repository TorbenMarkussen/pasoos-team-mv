package minidraw.boardgame;

import minidraw.framework.*;

import java.awt.*;
import java.util.List;

public class AnimatedBoardDrawing<LOCATION> extends BoardDrawing<LOCATION> {

    private AnimatedBoardDrawingFactory<LOCATION> animationFactory;

    public AnimatedBoardDrawing(AnimatedBoardDrawingFactory animatedBoardDrawingFactory) {
        super(animatedBoardDrawingFactory.getFigureFactory(), animatedBoardDrawingFactory.getPositioningStrategy(), animatedBoardDrawingFactory.getAppearanceStrategy());
        animationFactory = animatedBoardDrawingFactory;
    }

    public void moveAnimated(final LOCATION from, final LOCATION to) {
        moveAnimated(from, to, new NullAnimationCallback<LOCATION>());
    }

    public void moveAnimated(final LOCATION from, final LOCATION to, final AnimationCallbacks<LOCATION> cb) {
        // Setup animation
        final BoardPiece piece = getPiece(from);
        if (piece != null) {
            piece.setZorder(10);
            Point destination = animationFactory.getPositioningStrategy().calculateFigureCoordinatesIndexedForLocation(to, getCount(to));
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
            animationFactory.getAnimationEngine().startAnimation(a);
        } else {
            if (cb != null) {
                System.out.println("<animation failed to get piece, fireing all change events>");
                cb.beforeStart(from, to);
                cb.afterStart(from, to);
                cb.beforeEnd(from, to);
                cb.afterEnd(from, to);
            }
        }

    }

    private Animation createAnimation(BoardPiece piece, Point to) {

        Point from = piece.displayBox().getLocation();
        animationFactory.updateTimeInterval(from, to);
        animationFactory.updateEasingFunction(from, to);
        Animation animation = new MoveAnimation(piece, to, animationFactory.getTimeInterval(), animationFactory.getEasingFunction());
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
