package minidraw.animatedboard;

import minidraw.animation.*;
import minidraw.animation.easings.EasingFunctionStrategy;
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

    public void moveAnimated(final LOCATION from, final LOCATION to, final MoveAnimationCallbacks<LOCATION> cb) {
        // Setup animation
        final BoardPiece piece = getPiece(from);
        if (piece != null) {
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
        } else {
            if (cb != null) {
                System.out.println("<animation failed to get piece, fireing all change events>");
                cb.beforeMoveStart(from, to);
                cb.afterMoveStart(from, to);
                cb.beforeMoveEnd(from, to);
                cb.afterMoveEnd(from, to);
            }
        }

    }

    private Animation createAnimation(BoardPiece piece, Point to) {

        Point from = piece.displayBox().getLocation();
        AnimationConfiguration cfg = new AnimationCfg();
        animationConfigurator.configureAnimation(from, to, cfg);
        return new MoveAnimation(piece, to, cfg.getTimeInterval(), cfg.getEasingFunction());
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

    private static class AnimationCfg implements AnimationConfiguration {
        private TimeInterval timeIntervali;
        private EasingFunctionStrategy easingFunction;

        @Override
        public void setTimeline(TimeInterval timeIntervali) {
            this.timeIntervali = timeIntervali;
        }

        @Override
        public void setEasingFuntion(EasingFunctionStrategy easingFunction) {
            this.easingFunction = easingFunction;
        }

        @Override
        public TimeInterval getTimeInterval() {
            return timeIntervali;
        }

        @Override
        public EasingFunctionStrategy getEasingFunction() {
            return easingFunction;
        }
    }
}
