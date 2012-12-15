package minidraw.animatedboard;

import minidraw.animation.*;
import minidraw.animation.easings.EasingFunctionStrategy;
import minidraw.animation.easings.LinearMove;
import minidraw.animation.engine.AnimationEngine;
import minidraw.boardgame.BoardDrawing;
import minidraw.boardgame.BoardPiece;

import java.awt.*;
import java.util.List;

public class AnimatedBoardDrawing<LOCATION> extends BoardDrawing<LOCATION> implements AnimatedBoard<LOCATION> {

    private AnimationEngine animationEngine;
    private MoveAnimationConfiguratorStrategy animationConfigurator;

    public AnimatedBoardDrawing(AnimatedBoardDrawingFactory<LOCATION> factory) {
        super(factory.getFigureFactory(), factory.getPositioningStrategy(), factory.getAppearanceStrategy());
        animationEngine = factory.getAnimationEngine();
        animationConfigurator = factory.createAnimationConfigurator();
    }

    public void moveAnimated(LOCATION from, LOCATION to, MoveAnimationCallbacks<LOCATION> cb) {
        BoardPiece piece = getPiece(from);

        if (piece != null) {
            movePieceAnimated(from, to, cb, piece);
        } else {
            executeCallbacks(from, to, cb);
        }
    }

    private void movePieceAnimated(final LOCATION from, final LOCATION to, MoveAnimationCallbacks<LOCATION> cb, final BoardPiece piece) {
        Point destination = adjuster.calculateFigureCoordinatesIndexedForLocation(to, getCount(to));
        Animation a = createAnimation(piece, destination);

        final MoveAnimationCallbacks<LOCATION> cbLocal = (cb == null) ? new NullMoveAnimationCallback<LOCATION>() : cb;

        // Make move
        cbLocal.beforeMoveStart(from, to);
        startMove(piece, from);
        cbLocal.afterMoveStart(from, to);
        a.addAnimationChangeListener(new AnimationChangeListener() {

            @Override
            public void onAnimationCompleted(AnimationChangeEvent ace) {
                cbLocal.beforeMoveEnd(from, to);
                endMove(piece, to);
                cbLocal.afterMoveEnd(from, to);
            }
        });
        animationEngine.startAnimation(a);
    }

    private void executeCallbacks(LOCATION from, LOCATION to, MoveAnimationCallbacks<LOCATION> cb) {
        if (cb != null) {
            System.out.println("<animation failed to get piece, fireing all change events>");
            cb.beforeMoveStart(from, to);
            cb.afterMoveStart(from, to);
            cb.beforeMoveEnd(from, to);
            cb.afterMoveEnd(from, to);
        }
    }

    private Animation createAnimation(BoardPiece piece, Point to) {
        MoveAnimationBuilder mab = new MoveAnimationBuilder(piece, to);
        Point from = piece.displayBox().getLocation();
        animationConfigurator.configureAnimation(from, to, mab);
        return mab.build();
    }

    private void startMove(BoardPiece piece, LOCATION location) {
        movingPieces.add(piece);
        List<BoardPiece> l = figureMap.get(location);
        if (l.contains(piece)) {
            l.remove(piece);
            adjustPieces(location);
        }
    }

    private void endMove(BoardPiece piece, LOCATION location) {
        movingPieces.remove(piece);
        figureMap.get(location).add(piece);
        adjustPieces(location);
    }

    private static class MoveAnimationBuilder implements AnimationConfiguration {
        private TimeInterval timeInterval;
        private EasingFunctionStrategy easingFunction;
        private final BoardPiece piece;
        private final Point destination;

        private MoveAnimationBuilder(BoardPiece piece, Point destination) {
            this.piece = piece;
            this.destination = destination;
            timeInterval = TimeInterval.fromNow().duration(1000);
            easingFunction = new LinearMove();
        }

        @Override
        public void setTimeline(TimeInterval timeInterval) {
            this.timeInterval = timeInterval;
        }

        @Override
        public void setEasingFuntion(EasingFunctionStrategy easingFunction) {
            this.easingFunction = easingFunction;
        }

        @Override
        public TimeInterval getTimeInterval() {
            return timeInterval;
        }

        @Override
        public EasingFunctionStrategy getEasingFunction() {
            return easingFunction;
        }

        public Animation build() {
            return new MoveAnimation(piece, destination, timeInterval, easingFunction);
        }
    }
}
