package pasoos.hotgammon.gameengine;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.rules.DiceRoller;
import pasoos.hotgammon.rules.MoveValidatorStrategy;
import pasoos.hotgammon.rules.RulesFactory;
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


    public GameImpl(RulesFactory rulesFactory) {
        board = rulesFactory.createBoard();
        moveValidator = rulesFactory.createMoveValidatorStrategy(board);
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

        board.decrementLocation(from, playerInTurn);
        if (board.removeCheckersWithColor(to, playerInTurn.getOpponentColor()))
            board.incrementBar(playerInTurn.getOpponentColor());                   // Oponent is striked to bar
        board.incrementLocation(to, playerInTurn);
        removeDice(diceUsed);

        theWinner = winnerStrategy.determineWinner(this);

        return true;
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
    public int getTurnCount() {
        return turnCount;
    }

    @Override
    public boolean allCheckersOnBearOff(Color color) {
        return board.hasAllCheckersOnBearOff(color);
    }
}
