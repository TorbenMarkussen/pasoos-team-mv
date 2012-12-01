package pasoos.view;

import minidraw.boardgame.BoardPiece;
import minidraw.framework.*;
import minidraw.standard.AnimationTimerImpl;
import pasoos.hotgammon.Location;
import pasoos.physics.Convert;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManualPlayerState extends NullState implements GammonPlayer {
    private StateId stateId;
    private StateContext context;
    private String name;
    private boolean allowRoll;
    private List<BoardPiece> pieces = new ArrayList<BoardPiece>();
    private AnimationEngineImpl aEngine;
    private boolean animationActive = false;
    private List<EventCommand> cmdQueue = new ArrayList<EventCommand>();

    public ManualPlayerState(StateId stateId, StateContext context, String name) {
        this.stateId = stateId;
        this.context = context;
        this.name = name;
        aEngine = new AnimationEngineImpl(new AnimationTimerImpl());
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        boolean moveSucces = context.getGame().move(from, to);
        refreshStatusText();
        return moveSucces;
    }

    private void refreshStatusText() {
        int movesLeft = context.getGame().getNumberOfMovesLeft();

        if (movesLeft == 0)
            writeStatus("Illegal " + name + " is in turn");
        if (movesLeft == 1)
            writeStatus(name + " has 1 move left");
        else if (movesLeft > 1)
            writeStatus(name + " has " + movesLeft + " moves left");
    }

    private void writeStatus(String s) {
        context.updateStatus(this, s);
    }

    @Override
    public void rollDiceRequest() {
        if ((context.getGame().getNumberOfMovesLeft() == 0) && allowRoll)
            context.getGame().nextTurn();
    }

    @Override
    public void onEntry() {
        allowRoll = true;
        System.out.println("entry:" + name);
        writeStatus(name + " in turn - roll dice");
    }

    @Override
    public void winnerFound() {
        writeStatus(name + " wins game");
    }

    @Override
    public StateId getStateId() {
        return stateId;
    }

    @Override
    public void diceRolled(int[] values) {
        allowRoll = false;
        refreshStatusText();
    }

    @Override
    public void turnEnded() {
        allowRoll = false;
    }

    @Override
    public void blackPlayerActive() {
        if (animationActive) {
            cmdQueue.add(new EventCommand() {
                @Override
                public void execute() {
                    blackPlayerActive();
                }
            });
            return;
        }
        context.setState(StateId.BlackPlayer);
    }

    @Override
    public void redPlayerActive() {
        if (animationActive) {
            cmdQueue.add(new EventCommand() {
                @Override
                public void execute() {
                    redPlayerActive();
                }
            });
            return;
        }
        context.setState(StateId.RedPlayer);
    }

    @Override
    public void addPiece(BoardPiece piece) {
        pieces.add(piece);
    }

    @Override
    public void checkerMoved(final Location from, final Location to) {
        if (animationActive) {
            cmdQueue.add(new CheckerMovedCommand(this, from, to));
            return;
        }

        if (to == Location.R_BAR || to == Location.B_BAR) {
            animationActive = true;
            BoardPiece bp = context.getBoardDrawing().getPiece(from);
            Animation a = new MoveAnimation(bp, Convert.locationAndCount2xy(to, 0), TimeInterval.fromNow().duration(900),
                    new BezierMovement(new Point(bp.displayBox().x, 220), new Point(300, 220)));
            a.addAnimationChangeListener(new AnimationChangeListener() {
                @Override
                public void onAnimationCompleted(AnimationChangeEvent ace) {
                    animationActive = false;
                    notifyPieceMovedEvent(from, to);
                }
            });
            aEngine.startAnimation(a);
        } else {
            notifyPieceMovedEvent(from, to);
        }

    }

    private void notifyPieceMovedEvent(Location from, Location to) {
        context.getBoardDrawing().pieceMovedEvent(from, to);
        if (cmdQueue.size() > 0)
            cmdQueue.remove(0).execute();
    }


}
