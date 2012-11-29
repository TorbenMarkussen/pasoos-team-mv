package pasoos.view;

import pasoos.hotgammon.Location;

public class Initial extends NullState {
    private StateId stateId;
    private StateContext context;
    private int[] diceValues;

    public Initial(StateId stateId, StateContext context) {
        this.stateId = stateId;
        this.context = context;
    }

    @Override
    public void rollDiceRequest() {
        context.getGame().nextTurn();
    }

    @Override
    public void blackPlayerActive() {
        context.setState(StateId.BlackPlayer);
        context.getState().diceRolled(diceValues);
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        return false;
    }

    @Override
    public void diceRolled(int[] values) {
        diceValues = values;
    }

    @Override
    public StateId getStateId() {
        return stateId;
    }
}
