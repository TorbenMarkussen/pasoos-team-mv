package pasoos.view.gamestatemachine;

import minidraw.boardgame.BoardDrawing;
import minidraw.framework.Animation;
import minidraw.framework.AnimationChangeEvent;
import minidraw.framework.AnimationChangeListener;
import minidraw.framework.AnimationEngine;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.view.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStateController implements GammonStateMachine, StateContext {
    private Game game;
    Map<StateId, GammonState> states = new HashMap<StateId, GammonState>();
    private GammonState currentState;
    List<StatusObserver> statusObservers = new ArrayList<StatusObserver>();
    private List<EventCommand> cmdQueue = new ArrayList<EventCommand>();

    private BoardDrawing<Location> boardDrawing;
    private AnimationEngine aEngine;
    private boolean locked;
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

    public void setGammonDice(GammonDice gammonDice) {
        this.gammonDice = gammonDice;
    }

    public void setBoardDrawing(BoardDrawing<Location> boardDrawing) {
        this.boardDrawing = boardDrawing;
    }

    public void setAnimationEngine(AnimationEngine animationEngine) {
        aEngine = animationEngine;
    }

    @Override
    public void diceRolled(int[] values) {
        enqueueGameEvent(new DiceRolledEvent(values));
    }

    @Override
    public void turnEnded() {
        enqueueGameEvent(new TurnEndedCommand());
    }

    @Override
    public boolean moveRequest(Location from, Location to) {
        processGameEvents();
        if (locked)
            return false;

        MoveRequestCommand cmd = new MoveRequestCommand(from, to);
        executeCommand(cmd);
        return cmd.getResult();
    }

    @Override
    public void rollDiceRequest() {
        processGameEvents();
        if (locked)
            return;

        executeCommand(new RollDiceRequestCommand());
    }

    @Override
    public void blackPlayerActive() {
        enqueueGameEvent(new BlackPlayerCommand());
    }

    private void enqueueGameEvent(EventCommand eventCommand) {
        cmdQueue.add(eventCommand);
        processGameEvents();
    }

    @Override
    public void redPlayerActive() {
        enqueueGameEvent(new RedPlayerCommand());
    }

    @Override
    public void winnerFound() {
        enqueueGameEvent(new WinnerFoundCommand());
    }

    @Override
    public void checkerMoved(Location from, Location to) {
        enqueueGameEvent(new CheckerMovedCommand(from, to));
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
    public BoardDrawing<Location> getBoardDrawing() {
        return boardDrawing;
    }

    @Override
    public void startAnimation(Animation a, final Location from, final Location to) {
        locked = true;
        a.addAnimationChangeListener(new AnimationChangeListener() {
            @Override
            public void onAnimationCompleted(AnimationChangeEvent ace) {
                animationEnded(from, to);
            }
        });
        aEngine.startAnimation(a);
    }

    private void animationEnded(Location from, Location to) {
        locked = false;
        notifyPieceMovedEvent(from, to);
        processGameEvents();
    }

    @Override
    public void rollDice() {
        gammonDice.roll();
    }

    @Override
    public void notifyPieceMovedEvent(Location from, Location to) {
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

    private void processGameEvents() {
        if (!locked) {
            while (cmdQueue.size() > 0) {
                executeCommand(cmdQueue.remove(0));
            }
        }
    }

    private void executeCommand(EventCommand command) {
        System.out.println(currentState.getStateId() + " : " + command);
        command.execute(currentState);
    }

}
