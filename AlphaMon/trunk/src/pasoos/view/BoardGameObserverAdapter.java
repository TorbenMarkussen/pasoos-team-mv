package pasoos.view;

import minidraw.boardgame.BoardDrawing;
import minidraw.boardgame.BoardGameObserver;
import minidraw.framework.AnimationEngine;
import minidraw.framework.AnimationEngineImpl;
import minidraw.standard.AnimationTimerImpl;
import pasoos.hotgammon.GameObserver;
import pasoos.hotgammon.Location;

public class BoardGameObserverAdapter implements GameObserver {
    private BoardGameObserver<Location> boardGameObserver;
    private BoardDrawing<Location> boardDrawing;
    private AnimationEngine aEngine;

    public BoardGameObserverAdapter(BoardGameObserver<Location> boardGameObserver,
                                    BoardDrawing<Location> boardDrawing) {
        this.boardGameObserver = boardGameObserver;
        this.boardDrawing = boardDrawing;
        aEngine = new AnimationEngineImpl(new AnimationTimerImpl());
    }

    @Override
    public void checkerMove(final Location from, final Location to) {
//        if (to == Location.R_BAR || to == Location.B_BAR) {
//            BoardPiece bp = boardDrawing.getPiece(from);
//            boardGameObserver.pieceLogicalMoveEvent(from, to);
//            boardGameObserver.adjustPieces(from);
//            Animation a = new MoveAnimation(bp, Convert.locationAndCount2xy(to, 0), TimeInterval.fromNow().duration(900),
//                    new BezierMovement(new Point(bp.displayBox().x, 220), new Point(300, 220)));
//            a.addAnimationChangeListener(new AnimationChangeListener() {
//                @Override
//                public void onAnimationCompleted(AnimationChangeEvent ace) {
//                    boardGameObserver.adjustPieces(to);
//                }
//            });
//            aEngine.startAnimation(a);
//        } else
//            boardGameObserver.pieceMovedEvent(from, to);
        boardGameObserver.propChangeEvent("die1");
        boardGameObserver.propChangeEvent("die2");
    }

    @Override
    public void diceRolled(int[] values) {
        boardGameObserver.propChangeEvent("die1");
        boardGameObserver.propChangeEvent("die2");
    }

    @Override
    public void turnEnded() {
        boardGameObserver.propChangeEvent("die1");
        boardGameObserver.propChangeEvent("die2");
    }

    @Override
    public void winnerFound() {
    }
}
