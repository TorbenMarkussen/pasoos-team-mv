package pasoos.view;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameObserver;
import pasoos.hotgammon.Location;

public class GameEventDecorator implements GameObserver, Game {
    private Game game;
    private State state;

    public GameEventDecorator(Game game, State state) {
        this.game = game;
        this.game.addObserver(this);
        this.state = state;
    }

    @Override
    public void newGame() {
        game.newGame();
        game.nextTurn();

        if (game.getPlayerInTurn() == Color.BLACK)
            state.blackPlayerActive();
        else
            state.redPlayerActive();

        state.diceRolled(game.diceThrown());
    }

    @Override
    public void nextTurn() {
        game.nextTurn();
        state.diceRolled(game.diceThrown());
        evaluateTurnEnded();
    }

    private void evaluateTurnEnded() {
        if (game.getNumberOfMovesLeft() == 0) {
            state.turnEnded();
            if (game.getPlayerInTurn() == Color.BLACK)
                state.redPlayerActive();
            else
                state.blackPlayerActive();
        }
    }

    @Override
    public boolean move(Location from, Location to) {
        boolean moveResult = game.move(from, to);
        evaluateTurnEnded();
        return moveResult;
    }

    @Override
    public Color getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public int getNumberOfMovesLeft() {
        return game.getNumberOfMovesLeft();
    }

    @Override
    public int[] diceThrown() {
        return game.diceThrown();
    }

    @Override
    public int[] diceValuesLeft() {
        return game.diceValuesLeft();
    }

    @Override
    public Color winner() {
        return game.winner();
    }

    @Override
    public Color getColor(Location location) {
        return game.getColor(location);
    }

    @Override
    public int getCount(Location location) {
        return game.getCount(location);
    }

    @Override
    public void addObserver(GameObserver observer) {
        game.addObserver(observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        game.removeObserver(observer);
    }

    @Override
    public void checkerMove(Location from, Location to) {
        state.checkerMoved(from, to);
    }

    @Override
    public void diceRolled(int[] values) {
    }

    @Override
    public void turnEnded() {
    }
}