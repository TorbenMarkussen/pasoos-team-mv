package pasoos.view.gamestatemachine;

import pasoos.hotgammon.Location;

public class GameStateController implements GammonStateMachine {

    private StateContext context;

    public GameStateController() {
    }

    public void setContext(StateContext context) {
        this.context = context;
    }

    @Override
    public void diceRolled(int[] values) {
        context.getCurrentState().diceRolled(values);
    }

    @Override
    public void turnEnded() {
        context.getCurrentState().turnEnded();
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        return context.getCurrentState().moveRequest(from, to);
    }

    @Override
    public void rollDiceRequest() {
        context.getCurrentState().rollDiceRequest();
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
    public void winnerFound() {
        context.setState(StateId.Winner);
    }

    @Override
    public void checkerMoved(Location from, Location to) {
        context.getCurrentState().checkerMoved(from, to);
    }
}
