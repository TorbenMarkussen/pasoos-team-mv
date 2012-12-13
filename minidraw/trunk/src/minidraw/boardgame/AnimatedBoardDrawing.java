package minidraw.boardgame;

import minidraw.framework.*;

import java.awt.*;
import java.util.List;

public class AnimatedBoardDrawing<LOCATION> extends BoardDrawing<LOCATION> {

    private AnimationEngine animationEngine;
    private MoveAnimationConfiguratorStrategy animationConfigurator;

    public AnimatedBoardDrawing(AnimatedBoardDrawingFactory<LOCATION> factory) {
        super(factory.getFigureFactory(), factory.getPositioningStrategy(), factory.getAppearanceStrategy());
        animationEngine = factory.getAnimationEngine();
        animationConfigurator = factory.createAnimationConfigurator();
    }

    public void moveAnimated(final LOCATION from, final LOCATION to, final AnimationCallbacks<LOCATION> cb) {
        // Setup animation
        final BoardPiece piece = getPiece(from);
        if (piece != null) {
            Point destination = adjuster.calculateFigureCoordinatesIndexedForLocation(to, getCount(to));
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
            animationEngine.startAnimation(a);
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
        AnimationConfiguration cfg = new AnimationCfg();
        animationConfigurator.configureAnimation(from, to, cfg);
        return new MoveAnimation(piece, to, cfg.getTimeInterval(), cfg.getEasingFunction());
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

    private class AnimationCfg implements AnimationConfiguration {
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
