package pasoos.hotgammon.gamestatemachine;

import minidraw.animatedboard.NullMoveAnimationCallback;
import pasoos.hotgammon.Location;

public class ManualPlayerState extends BaseState implements GammonPlayer {
    private String name;
    private int activeAnimationCount = 0;
    private boolean turnEnded = false;
    private ManualState manualPlayerState;

    public ManualPlayerState(StateId stateId, String name) {
        super(stateId);
        this.name = name;
    }

    @Override
    public void onEntry() {
        manualPlayerState = ManualState.AllowRoll;
        turnEnded = false;
        activeAnimationCount = 0;
        System.out.println("entry:" + name);
        writeStatus(name + " in turn - roll dice");
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        if (manualPlayerState != ManualState.Moving)
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
            writeStatus(name + " has no more moves");
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
        if (manualPlayerState == ManualState.AllowRoll) {
            manualPlayerState = ManualState.Rolling;
            context.rollDice();
        }
    }

    @Override
    public void diceRolled(int[] values) {
        manualPlayerState = ManualState.Moving;
        refreshStatusText();
        context.notifyDiceRolled(values);
    }

    @Override
    public void turnEnded() {
        turnEnded = true;
        if (activeAnimationCount == 0) {
            context.playerTurnEnded(this);
        }
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
            increaseActiveAnimationCount();
            context.moveAnimated(
                    from,
                    to,
                    new NullMoveAnimationCallback<Location>() {
                        @Override
                        public void afterMoveEnd(Location from, Location to) {
                            animationEnded();
                        }
                    });
        } else {
            context.notifyPieceMovedEvent(from, to);
        }
    }

    private void animationEnded() {
        context.getSoundMachine().playCheckerMoveSound();
        decreaseActiveAnimationCount();
    }

    private void decreaseActiveAnimationCount() {
        activeAnimationCount--;
        if (turnEnded && activeAnimationCount == 0) {
            context.playerTurnEnded(this);
        }
    }

    private void increaseActiveAnimationCount() {
        activeAnimationCount++;
    }

    enum ManualState {
        Rolling, Moving, AllowRoll

    }

}
