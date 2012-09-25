package pasoos.hotgammon.gamelogger;

import pasoos.hotgammon.*;
import pasoos.hotgammon.rules.HotGammonFactory;

import java.util.ArrayList;
import java.util.List;

public class GameLogDecoratorImpl implements GameLogDecorator {
    private Game game;
    private List<LogObserver> observers;

    public GameLogDecoratorImpl(HotGammonFactory factory) {
        observers = new ArrayList<LogObserver>();
        game = GameFactory.createGame(factory);
    }

    @Override
    public void addLogObserver(LogObserver logObserver) {
        observers.add(logObserver);
    }

    @Override
    public void newGame() {
        game.newGame();
    }

    @Override
    public void nextTurn() {
        game.nextTurn();
        notifyObservers(String.format("Dice rolled: %d-%d", diceThrown()[0], diceThrown()[1]));
    }

    private void notifyObservers(String s) {
        if (observers.size() > 0) {
            for (LogObserver ob : observers) {
                ob.log(s);
            }
        }
    }

    @Override
    public boolean move(Location from, Location to) {
        boolean moved = game.move(from, to);
        if (moved)
            notifyObservers(String.format("%s moves (%s, %s)", getPlayerInTurn(), from, to));
        return moved;
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
}
