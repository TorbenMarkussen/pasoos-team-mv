package pasoos.view.gamestatemachine;

import minidraw.boardgame.AnimatedBoardDrawing;
import minidraw.framework.SoundResource;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.view.GammonDice;
import pasoos.view.Initial;
import pasoos.view.StatusObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStateController implements GammonStateMachine, StateContext {
    private Game game;
    Map<StateId, GammonState> states = new HashMap<StateId, GammonState>();
    private GammonState currentState;
    List<StatusObserver> statusObservers = new ArrayList<StatusObserver>();

    private AnimatedBoardDrawing boardDrawing;
    private SoundResource soundResource;
    private GammonDice gammonDice;

    public GameStateController() {
        game = null;
        states.put(StateId.Initial, new Initial(StateId.Initial, this));
        states.put(StateId.Winner, new WinnerState(StateId.Winner, this));
        currentState = states.get(StateId.Initial);
        currentState.onEntry();
    }

    public void setRedPlayer(GammonPlayer player) {
        states.put(StateId.RedPlayer, player);
    }

    public void setBlackPlayer(GammonPlayer player) {
        states.put(StateId.BlackPlayer, player);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String getWinnerName() {
        return game.winner().toString();
    }

    @Override
    public SoundResource getSoundMachine() {
        return soundResource;
    }

    @Override
    public AnimatedBoardDrawing getBoard() {
        return boardDrawing;
    }

    public void setGammonDice(GammonDice gammonDice) {
        this.gammonDice = gammonDice;
    }

    public void setBoardDrawing(AnimatedBoardDrawing boardDrawing) {
        this.boardDrawing = boardDrawing;
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
    public boolean moveRequest(Location from, Location to) {
        return currentState.moveRequest(from, to);
    }

    @Override
    public void rollDiceRequest() {
        currentState.rollDiceRequest();
    }

    @Override
    public void blackPlayerActive() {
        setState(StateId.BlackPlayer);
    }

    @Override
    public void redPlayerActive() {
        setState(StateId.RedPlayer);
    }

    @Override
    public void winnerFound() {
        setState(StateId.Winner);
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
        GammonState previousState = currentState;
        currentState = states.get(stateId);

        previousState.onExit();
        currentState.onEntry();
    }

    public void addStatusObserver(StatusObserver statusObserver) {
        statusObservers.add(statusObserver);
    }

    public void updateStatus(GammonState state, String s) {
        if (state.getStateId() == currentState.getStateId()) {
            for (StatusObserver so : statusObservers) {
                so.updateStatus(s);
            }
        }
    }

    @Override
    public void rollDice() {
        gammonDice.roll();
    }

    @Override
    public void notifyPieceMovedEvent(Location from, Location to) {
        System.out.println("pieceMoved(" + from + ", " + to + ")");
        for (StatusObserver so : statusObservers) {
            so.checkerMove(from, to);
        }
    }

    @Override
    public void notifyDiceRolled(int[] values) {
        for (StatusObserver so : statusObservers) {
            so.diceRolled(values);
        }
    }

    public void setSoundEngine(SoundResource soundEngine) {
        soundResource = soundEngine;
    }
}
