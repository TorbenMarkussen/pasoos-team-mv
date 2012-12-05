package pasoos.hotgammon.gamestatemachine;

import minidraw.boardgame.AnimatedBoardDrawing;
import pasoos.hotgammon.sounds.SoundResource;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.ui.GammonDice;
import pasoos.hotgammon.ui.StatusObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateContextImpl implements StateContext {
    Map<StateId, GammonState> states = new HashMap<StateId, GammonState>();
    private GammonState currentState;
    private Game game;
    private AnimatedBoardDrawing<Location> boardDrawing;
    private GammonDice dice;
    private List<StatusObserver> statusObservers = new ArrayList<StatusObserver>();
    private SoundResource soundMachine;

    private StateContextImpl(Builder builder) {
        game = builder.getGame();
        boardDrawing = builder.getBoardDrawing();
        dice = builder.getGammonDice();
        soundMachine = builder.getSounds();

        states.put(StateId.Initial, builder.getInitial());
        states.put(StateId.Winner, builder.getWinner());
        states.put(StateId.BlackPlayer, builder.getBlackplayer());
        states.put(StateId.RedPlayer, builder.getRedplayer());

        for (Map.Entry<StateId, GammonState> e : states.entrySet()) {
            e.getValue().setContext(this);
        }

        currentState = states.get(StateId.Initial);
        currentState.onEntry();
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
        return "i won";
    }

    @Override
    public SoundResource getSoundMachine() {
        return soundMachine;
    }

    @Override
    public AnimatedBoardDrawing<Location> getBoard() {
        return boardDrawing;
    }

    @Override
    public void addStatusObserver(StatusObserver statusObserver) {
        statusObservers.add(statusObserver);
    }

    @Override
    public GammonState getCurrentState() {
        return currentState;
    }

    public static class Builder {
        GammonState initial;
        GammonState winner;
        GammonPlayer redplayer;
        GammonPlayer blackplayer;
        Game game;
        AnimatedBoardDrawing<Location> boardDrawing;
        GammonDice gammonDice;
        private StateContextImpl context;
        private SoundResource sounds;

        public Builder(GammonPlayer redplayer, GammonPlayer blackplayer, Game game, AnimatedBoardDrawing<Location> boardDrawing, GammonDice gammonDice) {
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

        public AnimatedBoardDrawing<Location> getBoardDrawing() {
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

        public void setBoardDrawing(AnimatedBoardDrawing<Location> boardDrawing) {
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
    }
}
