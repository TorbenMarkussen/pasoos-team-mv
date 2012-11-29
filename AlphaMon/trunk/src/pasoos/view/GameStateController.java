package pasoos.view;

import minidraw.boardgame.BoardDrawing;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStateController implements State, StateContext {
    private Game game;
    Map<StateId, State> states = new HashMap<StateId, State>();
    private State currentState;
    List<StatusObserver> statusObservers = new ArrayList<StatusObserver>();

    private BoardDrawing<Location> boardDrawing;

    public GameStateController() {
        game = null;
        states.put(StateId.Initial, new Initial(StateId.Initial, this));
        states.put(StateId.Winner, new WinnerState(StateId.Winner, this));
        currentState = states.get(StateId.Initial);
        currentState.onEntry();
    }

    public GameStateController setRedPlayer(GammonPlayer player) {
        states.put(StateId.RedPlayer, player);
        return this;
    }

    public GameStateController setBlackPlayer(GammonPlayer player) {
        states.put(StateId.BlackPlayer, player);
        return this;
    }

    public GameStateController setGame(Game game) {
        this.game = game;
        return this;
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

    @Override
    public StateId getStateId() {
        return null;
    }

    @Override
    public void rollDiceRequest() {
        currentState.rollDiceRequest();
    }

    @Override
    public void blackPlayerActive() {
        currentState.blackPlayerActive();
    }

    @Override
    public void redPlayerActive() {
        currentState.redPlayerActive();
    }

    @Override
    public void winnerFound() {
        currentState.winnerFound();
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        return currentState.moveRequest(from, to);
    }

    @Override
    public void checkerMoved(Location from, Location to) {
        currentState.checkerMoved(from, to);
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void setState(StateId stateId) {
        State previousState = currentState;
        currentState = states.get(stateId);

        previousState.onExit();
        currentState.onEntry();
    }

    @Override
    public State getState() {
        return this;
    }

    public void addStatusObserver(StatusObserver statusObserver) {
        statusObservers.add(statusObserver);
    }

    public void updateStatus(State state, String s) {
        if (state.getStateId() == currentState.getStateId()) {
            for (StatusObserver so : statusObservers) {
                so.updateStatus(s);
            }
        }
    }

    @Override
    public BoardDrawing<Location> getBoardDrawing() {
        return boardDrawing;
    }

    public void setBoardDrawing(BoardDrawing<Location> boardDrawing) {
        this.boardDrawing = boardDrawing;
    }

}
