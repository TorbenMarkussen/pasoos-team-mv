package pasoos.view;

import pasoos.hotgammon.Location;
import pasoos.view.gamestatemachine.State;
import pasoos.view.gamestatemachine.StateId;

public abstract class NullState implements State {
    @Override
    public void blackPlayerActive() {
    }

    @Override
    public void redPlayerActive() {
    }

    @Override
    public void winnerFound() {
    }

    @Override
    public abstract boolean moveRequest(Location from, Location to);

    @Override
    public void checkerMoved(Location from, Location to) {
    }

    @Override
    public void rollDiceRequest() {
    }

    @Override
    public void diceRolled(int[] values) {
    }

    @Override
    public void turnEnded() {
    }

    @Override
    public void onEntry() {
    }

    @Override
    public void onExit() {

    }

    @Override
    public abstract StateId getStateId();
}
