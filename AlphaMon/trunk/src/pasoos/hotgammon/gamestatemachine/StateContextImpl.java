package pasoos.hotgammon.gamestatemachine;

import minidraw.animatedboard.AnimatedBoard;
import minidraw.animatedboard.MoveAnimationCallbacks;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.sounds.SoundResource;
import pasoos.hotgammon.ui.GammonDice;
import pasoos.hotgammon.ui.status.StatusObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateContextImpl implements StateContext {
    Map<StateId, GammonState> states = new HashMap<StateId, GammonState>();
    private GammonState currentState;
    private Game game;
    private AnimatedBoard<Location> boardDrawing;
    private GammonDice dice;
    private List<StatusObserver> statusObservers = new ArrayList<StatusObserver>();
    private SoundResource soundMachine;
    private Map<Color, String> playerNames = new HashMap<Color, String>();
    private GammonState mainState;

    private StateContextImpl(Builder builder) {
        game = builder.getGame();
        boardDrawing = builder.getBoardDrawing();
        dice = builder.getGammonDice();
        soundMachine = builder.getSounds();
        mainState = builder.getMainState();
        mainState.setContext(this);

        states.put(StateId.Initial, builder.getInitial());
        states.put(StateId.Winner, builder.getWinner());
        states.put(StateId.BlackPlayer, builder.getBlackplayer());
        states.put(StateId.RedPlayer, builder.getRedplayer());

        for (Map.Entry<StateId, GammonState> e : states.entrySet()) {
            e.getValue().setContext(this);
        }

        currentState = states.get(StateId.Initial);
        currentState.onEntry();

        playerNames.put(Color.BLACK, builder.getBlackplayer().getName());
        playerNames.put(Color.RED, builder.getRedplayer().getName());
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

    @Override
    public void updateStatus(GammonState state, String s) {
        if (state.getStateId() == currentState.getStateId()) {
            for (StatusObserver so : statusObservers) {
                so.updateStatus(s);
            }
        }
    }

    @Override
    public void rollDice() {
        dice.roll();
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

    @Override
    public String getWinnerName() {
        if (!playerNames.containsKey(getGame().winner()))
            return "";
        return playerNames.get(getGame().winner());
    }

    @Override
    public SoundResource getSoundMachine() {
        return soundMachine;
    }

    @Override
    public void moveAnimated(Location from, Location to, MoveAnimationCallbacks<Location> cb) {
        boardDrawing.moveAnimated(from, to, cb);
    }

    @Override
    public void addStatusObserver(StatusObserver statusObserver) {
        statusObservers.add(statusObserver);
    }

    @Override
    public GammonState getCurrentState() {
        return currentState;
    }

    @Override
    public void playerTurnEnded(GammonPlayer player) {
        if (player.getStateId() == StateId.BlackPlayer) {
            setState(StateId.RedPlayer);
        } else {
            setState(StateId.BlackPlayer);
        }
    }

    public static class Builder {
        GammonState initial;
        GammonState winner;
        GammonPlayer redplayer;
        GammonPlayer blackplayer;
        Game game;
        AnimatedBoard<Location> boardDrawing;
        GammonDice gammonDice;
        private SoundResource sounds;
        private GammonState mainState;

        public Builder(GammonPlayer redplayer,
                       GammonPlayer blackplayer,
                       Game game,
                       AnimatedBoard<Location> boardDrawing,
                       GammonDice gammonDice,
                       GammonState mainState) {
            this.mainState = mainState;
            initial = new Initial(StateId.Initial);
            winner = new WinnerState(StateId.Winner);
            sounds = new SoundResource(true);

            this.redplayer = redplayer;
            this.blackplayer = blackplayer;
            this.game = game;
            this.boardDrawing = boardDrawing;
            this.gammonDice = gammonDice;
        }

        public GammonState getInitial() {
            return initial;
        }

        public GammonState getWinner() {
            return winner;
        }

        public GammonPlayer getRedplayer() {
            return redplayer;
        }

        public GammonPlayer getBlackplayer() {
            return blackplayer;
        }

        public Game getGame() {
            return game;
        }

        public AnimatedBoard<Location> getBoardDrawing() {
            return boardDrawing;
        }

        public GammonDice getGammonDice() {
            return gammonDice;
        }

        public void setInitial(GammonState initial) {
            this.initial = initial;
        }

        public void setWinner(GammonState winner) {
            this.winner = winner;
        }

        public void setRedplayer(GammonPlayer redplayer) {
            this.redplayer = redplayer;
        }

        public void setBlackplayer(GammonPlayer blackplayer) {
            this.blackplayer = blackplayer;
        }

        public void setGame(Game game) {
            this.game = game;
        }

        public void setBoardDrawing(AnimatedBoard<Location> boardDrawing) {
            this.boardDrawing = boardDrawing;
        }

        public void setGammonDice(GammonDice gammonDice) {
            this.gammonDice = gammonDice;
        }

        public StateContext build() {
            return new StateContextImpl(this);
        }

        public SoundResource getSounds() {
            return sounds;
        }

        public void setSounds(SoundResource sounds) {
            this.sounds = sounds;
        }

        public GammonState getMainState() {
            return mainState;
        }
    }
}
