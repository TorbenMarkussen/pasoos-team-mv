package pasoos.hotgammon.gameengine;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameObserver;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.rules.DiceRoller;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.MoveValidatorStrategy;
import pasoos.hotgammon.rules.WinnerStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Skeleton implementation of HotGammon.
 * <p/>
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * <p/>
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */

public class GameImpl implements Game, GameState {

    private Color playerInTurn;
    private List<Integer> dice;
    private Board board;
    private ArrayList<Integer> diceValuesLeft;
    private int turnCount;
    private MoveValidatorStrategy moveValidator;
    private WinnerStrategy winnerStrategy;
    private Color theWinner;
    private DiceRoller diceRoller;
    private List<GameObserver> observers;


    public GameImpl(HotGammonFactory rulesFactory) {
        observers = new ArrayList<GameObserver>();
        board = rulesFactory.createBoard();
        moveValidator = rulesFactory.createMoveValidatorStrategy(this, board);
        winnerStrategy = rulesFactory.createWinnerStrategy();
        diceRoller = rulesFactory.createDiceRoller();
    }

    public void newGame() {
        playerInTurn = Color.NONE;
        dice = new ArrayList<Integer>();
        diceValuesLeft = new ArrayList<Integer>();
        turnCount = 0;
        theWinner = Color.NONE;
        board.initialize();
    }


    public void nextTurn() {
        turnCount++;
        theWinner = winnerStrategy.determineWinner(this);

        if (playerInTurn != Color.BLACK)
            playerInTurn = Color.BLACK;
        else
            playerInTurn = Color.RED;

        rollDice();

        diceValuesLeft = new ArrayList<Integer>(dice);
        if (dice.get(0).equals(dice.get(1)))
            diceValuesLeft.addAll(dice);

        handlePossibleMoves();

        notifyDiceRolled();
    }

    private void handlePossibleMoves() {
        for (Location from : Location.values()) {
            Color fromColor = board.getColor(from);
            if (fromColor == playerInTurn) {
                for (Location to : Location.values()) {
                    if (findValidDice(from, to) != 0) {
                        return;
                    }
                }
            }
        }

        // Remove all remaining dice values as no possible moves were found
        int noOfDiesLeft = diceValuesLeft.size();
        for (int i = 0; i < noOfDiesLeft; i++) {
            int d = diceValuesLeft.get(0);
            removeDice(d);
        }
    }

    private void notifyDiceRolled() {
        if (observers.size() > 0) {
            for (GameObserver go : observers) {
                go.diceRolled(diceThrown());
            }
        }
    }

    private void rollDice() {
        dice = new ArrayList<Integer>();
        for (int d : diceRoller.roll())
            dice.add(d);
        Collections.sort(dice, Collections.reverseOrder());
    }

    public boolean move(Location from, Location to) {
        Color fromColor = board.getColor(from);

        if (theWinner != Color.NONE)
            return false;

        if (playerInTurn != fromColor)
            return false;

        if (getNumberOfMovesLeft() == 0)
            return false;

        int diceUsed = findValidDice(from, to);

        if (diceUsed == 0)
            return false;

        moveChecker(from, to);

        removeDice(diceUsed);

        theWinner = winnerStrategy.determineWinner(this);

        handlePossibleMoves();

        return true;
    }

    private void moveChecker(Location from, Location to) {
        Color opponentColor = playerInTurn.getOpponentColor();

        if (board.getColor(to) == opponentColor)
            moveOpponentCheckersToBar(to, opponentColor);

        board.decrementLocation(from, playerInTurn);
        board.incrementLocation(to, playerInTurn);
        notifyMove(from, to);
    }

    private void moveOpponentCheckersToBar(Location location, Color playerColor) {
        board.getCount(location);
        for (int i = 0; i < board.getCount(location); i++) {
            board.decrementLocation(location, playerColor);
            board.incrementBar(playerColor);
            notifyMove(location, Location.getBar(playerColor));
        }
    }

    private void notifyMove(Location from, Location to) {
        if (observers.size() > 0) {
            for (GameObserver go : observers) {
                go.checkerMove(from, to);
            }
        }
    }

    private int findValidDice(Location from, Location to) {
        int diceUsed = 0;

        for (int i = 0; i < diceValuesLeft.size() && diceUsed == 0; i++) {
            int d = diceValuesLeft.get(i);
            if (moveValidator.isValidMove(from, to, d))
                diceUsed = d;
        }
        return diceUsed;
    }

    private void removeDice(int diceValue) {
        diceValuesLeft.remove((Integer) diceValue);
    }

    public Color getPlayerInTurn() {
        return playerInTurn;
    }

    public int getNumberOfMovesLeft() {
        return diceValuesLeft().length;
    }

    public int[] diceThrown() {
        return listToIntArray(dice);
    }

    private int[] listToIntArray(List<Integer> list) {
        int[] da = new int[list.size()];
        for (int i = 0; i < da.length; i++)
            da[i] = list.get(i);
        return da;
    }

    public int[] diceValuesLeft() {
        return listToIntArray(diceValuesLeft);
    }

    public Color winner() {
        return theWinner;
    }

    public Color getColor(Location location) {
        return board.getColor(location);
    }

    public int getCount(Location location) {
        return board.getCount(location);
    }

    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        if (observers.contains(observer))
            observers.remove(observer);
    }

    @Override
    public int getTurnCount() {
        return turnCount;
    }

    @Override
    public boolean allCheckersOnBearOff(Color color) {
        return board.hasAllCheckersOnBearOff(color);
    }
}
