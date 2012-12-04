package minidraw.boardgame;

import minidraw.framework.*;

import java.awt.*;

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

    public void moveAnimated(final LOCATION from, final LOCATION to, final AnimationChangeListener listener) {
        // Setup animation
        final BoardPiece piece = getPiece(from);
        Point destination = positioningStrategy.calculateFigureCoordinatesIndexedForLocation(to, getCount(to)) ; // Convert.locationAndCount2xy(to, 0);
        Animation a = getAnimation(piece, destination, listener);
        // Make move
        startMove(piece, from);
        aengine.startAnimation(a);
    }

    /*            a.addAnimationChangeListener(new AnimationChangeListener() {
                @Override
            public void onAnimationCompleted(AnimationChangeEvent animationChangeEvent) {
                if (AnimationMoveListener.OnAnimationStarted  != null)
                    AnimationMoveListener.OnAnimationStarted(a);
                endMove(piece, to);
                if (AnimationMoveListener.OnEndMove != null)
                    AnimationMoveListener.OnEndMove(a);
            }
        });*/


    private Animation getAnimation(BoardPiece piece, Point destination, AnimationChangeListener callBacks) {
        Animation animation = new MoveAnimation(piece, destination, TimeInterval.fromNow().duration(1000), new LinearMove());
        animation.addAnimationChangeListener(callBacks) ;
        return animation ;
    }

    public void startMove(BoardPiece piece, LOCATION location) {
        movingPieces.add(piece);
        java.util.List<BoardPiece> l = figureMap.get(location);
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
