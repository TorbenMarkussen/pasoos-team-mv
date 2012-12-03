package pasoos.view;

import minidraw.boardgame.BoardPiece;
import minidraw.framework.*;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.minidraw_controller.GammonMove;
import pasoos.physics.Convert;
import pasoos.view.gamestatemachine.GammonPlayer;
import pasoos.view.gamestatemachine.StateContext;
import pasoos.view.gamestatemachine.StateId;

import java.awt.*;
import java.util.List;

public class AIPlayerState extends NullState implements GammonPlayer {
    private final StateId stateId;
    private final StateContext context;
    private final String name;
    private AIPlayer aiPlayer;
    private List<GammonMove> moves;
    private boolean allowRoll;

    public AIPlayerState(StateId stateId, StateContext context, String name, AIPlayer aiPlayer) {
        this.stateId = stateId;
        this.context = context;
        this.name = name;
        this.aiPlayer = aiPlayer;
        allowRoll = false;
    }

    @Override
    public void onEntry() {
        System.out.println("entry:" + name);
        writeStatus(name + " in turn");
        if (allowRoll) {
            context.rollDice();
        }
        allowRoll = true;
    }

    private void writeStatus(String s) {
        context.updateStatus(this, s);
    }

    @Override
    public void blackPlayerActive() {
        context.setState(StateId.BlackPlayer);
    }

    @Override
    public void redPlayerActive() {
        context.setState(StateId.RedPlayer);
    }

    @Override
    public void addPiece(BoardPiece piece) {

    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        return false;
    }

    @Override
    public StateId getStateId() {
        return stateId;
    }

    @Override
    public void diceRolled(int[] values) {
        aiPlayer.play();
        moves = aiPlayer.getMoves();
        processGerryMoves();
        context.notifyDiceRolled(values);
    }

    @Override
    public void checkerMoved(final Location from, final Location to) {

        if (to == Location.R_BAR || to == Location.B_BAR) {
            context.getBoard().moveAnimated(from, to, null, null);
            //startAnimatedBarMove(from, to);
        } else {
            //context.notifyPieceMovedEvent(from, to);
            //processGerryMoves();
        }

    }

    private void processGerryMoves() {
        if (moves.size() > 0) {
            GammonMove move = moves.remove(0);
            startAnimatedMove(move);
        }
    }

    private void startAnimatedMove(final GammonMove move) {
        System.out.println("starting move " + move);
        context.getBoard().moveAnimated(
                move.getFrom(),
                move.getTo(),
                new AnimationChangeListener() {
                    @Override
                    public void onAnimationCompleted(AnimationChangeEvent ace) {
                        context.getGame().move(move.getFrom(), move.getTo());
                        context.getSoundMachine().playCheckerMoveSound();
                    }
                }, new AnimationChangeListener() {
                    @Override
                    public void onAnimationCompleted(AnimationChangeEvent ace) {
                        processGerryMoves();
                    }
                }
        );
    }

    private void startAnimatedBarMove(Location from, Location to) {
        final BoardPiece bp = context.getPieceFromBoard(from);
        Point destination = Convert.locationAndCount2xy(to, 0);
        TimeInterval timeInterval = TimeInterval.fromNow().duration(1000);
        EasingFunctionStrategy ef = new BezierMovement(new Point(bp.displayBox().x, 220), new Point(300, 220));
        Animation a = new MoveAnimation(bp, destination, timeInterval, ef);
        context.startAnimation(a, bp, new GammonMove(from, to));
    }

    private void _startAnimatedMove(final GammonMove move) {
        BoardPiece piece = context.getPieceFromBoard(move.getFrom());
        Point destination = Convert.locationAndCount2xy(move.getTo(), context.getGame().getCount(move.getTo()));

        TimeInterval timeline = TimeInterval.fromNow().duration(1000);
        EasingFunctionStrategy ef = new LinearMove();

        Animation a = new MoveAnimation(piece, destination, timeline, ef);
        a.addAnimationChangeListener(new AnimationChangeListener() {
            @Override
            public void onAnimationCompleted(AnimationChangeEvent ace) {
                context.getGame().move(move.getFrom(), move.getTo());
                context.getSoundMachine().playCheckerMoveSound();
            }
        });

        context.startAnimation(a, piece, move);
    }
}
