package pasoos.view;

import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;

import java.util.HashMap;
import java.util.Map;

public class GameStateController extends State implements StateContext {
    private Game game;
    Map<GammonState, State> states = new HashMap<GammonState, State>();
    private State currentState;

    public GameStateController() {
        game = null;
        states.put(GammonState.Initial, new Initial(this));
        currentState = states.get(GammonState.Initial);
        currentState.onEntry();
    }

    @Override
    public void diceRolled(int[] values) {
        currentState.diceRolled(values);
    }

    @Override
    public void turnEnded() {
        currentState.turnEnded();
    }

    @Override
    public void onEntry() {
        currentState.onEntry();
    }

    @Override
    public void onExit() {
        currentState.onExit();
    }

    public GameStateController setGame(Game game) {
        this.game = game;
        return this;
    }

    public GameStateController setRedPlayer(GammonPlayer player) {
        states.put(GammonState.RedPlayer, new ManualPlayerState(player, this));
        return this;
    }

    public GameStateController setBlackPlayer(GammonPlayer player) {
        states.put(GammonState.BlackPlayer, new ManualPlayerState(player, this));
        return this;
    }

    public void rollDiceRequest() {
        currentState.rollDiceRequest();
    }

    public void blackPlayerActive() {
        currentState.blackPlayerActive();
    }

    public void redPlayerActive() {
        currentState.redPlayerActive();
    }

    public void winnerFound() {
        currentState.winnerFound();
    }

    public boolean moveRequest(Location from, Location to) {
        return currentState.moveRequest(from, to);
    }

    public void checkerMoved(Location from, Location to) {
        currentState.checkerMoved(from, to);
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void setState(GammonState state) {
        State previousState = currentState;
        currentState = states.get(state);

        previousState.onExit();
        currentState.onEntry();
    }

    @Override
    public State getState() {
        return this;
    }
}
