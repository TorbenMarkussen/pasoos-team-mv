package pasoos.hotgammon.gamestatemachine;

import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.NullAnimationCallback;
import pasoos.hotgammon.Location;

import java.util.ArrayList;
import java.util.List;

public class ManualPlayerState extends BaseState implements GammonPlayer {
    private String name;
    private boolean allowRoll;
    private List<BoardPiece> pieces = new ArrayList<BoardPiece>();
    private boolean allowMove;

    public ManualPlayerState(StateId stateId, String name) {
        super(stateId);
        this.name = name;
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        if (!allowMove)
            return false;

        boolean moveSucces = context.getGame().move(from, to);

        if (moveSucces) {
            context.getSoundMachine().playCheckerMoveSound();
        } else {
            context.getSoundMachine().playErrorSound();
        }

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
        if ((context.getGame().getNumberOfMovesLeft() == 0) && allowRoll) {
            allowRoll = false;
            context.rollDice();
        }
    }

    @Override
    public void onEntry() {
        allowRoll = true;
        allowMove = false;
        System.out.println("entry:" + name);
        writeStatus(name + " in turn - roll dice");
    }

    @Override
    public void diceRolled(int[] values) {
        allowRoll = false;
        allowMove = true;
        refreshStatusText();
        context.notifyDiceRolled(values);
    }

    @Override
    public void turnEnded() {
        allowRoll = false;
    }

    @Override
    public void addPiece(BoardPiece piece) {
        pieces.add(piece);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void checkerMoved(Location from, Location to) {
        if (to == Location.R_BAR || to == Location.B_BAR) {
            System.out.println(name + " strikes " + from + " -> " + to);
            writeStatus(name + " strikes " + from + " -> " + to);
            context.getBoard().moveAnimated(
                    from,
                    to,
                    new NullAnimationCallback<Location>() {
                        @Override
                        public void beforeEnd(Location from, Location to) {
                            context.getSoundMachine().playCheckerMoveSound();
                        }
                    });
        } else {
            context.notifyPieceMovedEvent(from, to);
        }
    }

}
