package pasoos.view;

public class Initial extends NullState {
    private StateContext context;
    private int[] diceValues;

    public Initial(StateContext context) {
        this.context = context;
    }

    @Override
    public void rollDiceRequest() {
        context.getGame().nextTurn();
    }

    @Override
    public void blackPlayerActive() {
        context.setState(GammonState.BlackPlayer);
        context.getState().diceRolled(diceValues);
    }

    @Override
    public void diceRolled(int[] values) {
        diceValues = values;
    }
}
