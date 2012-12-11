package pasoos.hotgammon.gamestatemachine;

import pasoos.hotgammon.Location;

public abstract class BaseState implements GammonState {

    private StateId stateId;
    protected StateContext context;

    protected BaseState(StateId stateId) {
        this.stateId = stateId;
    }

    @Override
    public StateId getStateId() {
        return stateId;
    }

    @Override
    public void setContext(StateContext context) {
        this.context = context;
    }

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
    public void initiateGame() {

    }
}
