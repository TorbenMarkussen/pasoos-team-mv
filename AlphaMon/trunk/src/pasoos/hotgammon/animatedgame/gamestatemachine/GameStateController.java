package pasoos.hotgammon.animatedgame.gamestatemachine;

import pasoos.hotgammon.Location;

import java.util.Arrays;

public class GameStateController implements GammonState {

    private StateContext context;

    public GameStateController() {
    }

    @Override
    public void setContext(StateContext context) {
        this.context = context;
    }

    @Override
    public void onEntry() {
    }

    @Override
    public void onExit() {
    }

    @Override
    public StateId getStateId() {
        return StateId.Main;
    }


    @Override
    public void diceRolled(int[] values) {
        System.out.println("diceRolled " + Arrays.toString(values));
        context.getCurrentState().diceRolled(values);
    }

    @Override
    public void turnEnded() {
        System.out.println("turnEnded");
        context.getCurrentState().turnEnded();
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        System.out.println("moveRequest");
        return context.getCurrentState().moveRequest(from, to);
    }

    @Override
    public void rollDiceRequest() {
        System.out.println("rollDiceRequest");
        context.getCurrentState().rollDiceRequest();
    }

    @Override
    public void blackPlayerActive() {
        System.out.println("blackPlayerActive");
        context.getCurrentState().blackPlayerActive();
    }

    @Override
    public void redPlayerActive() {
        System.out.println("redPlayerActive");
        context.getCurrentState().redPlayerActive();
    }

    @Override
    public void winnerFound() {
        System.out.println("winnerFound");
        context.setState(StateId.Winner);
    }

    @Override
    public void initiateGame() {
        System.out.println("initiateGame");
        context.getCurrentState().initiateGame();
    }

    @Override
    public void checkerMoved(Location from, Location to) {
        System.out.println("checkerMoved");
        context.getCurrentState().checkerMoved(from, to);
    }


}
