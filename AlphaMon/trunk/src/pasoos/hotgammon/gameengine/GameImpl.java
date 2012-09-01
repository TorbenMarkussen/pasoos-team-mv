package pasoos.hotgammon.gameengine;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gameengine.validator.MoveValidatorFactory;
import pasoos.hotgammon.gameengine.validator.MoveValidatorStrategy;

import java.util.Arrays;

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

public class GameImpl implements Game {

    private Color playerInTurn;
    private int[] dice;
    private Board board;
    private int[] diceValuesLeft;
    private int turnCount;
    private MoveValidatorStrategy moveValidator;
    private MoveValidatorFactory moveValidatorFactory;

    public GameImpl(MoveValidatorFactory mvf) {
        board = new Board();
        moveValidator = mvf.Get(board);
    }

    public void newGame() {
        playerInTurn = Color.NONE;
        dice = new int[]{-1, 0};
        turnCount = 0;
        board.initialize();
    }


    public void nextTurn() {
        if (playerInTurn != Color.BLACK)
            playerInTurn = Color.BLACK;
        else
            playerInTurn = Color.RED;

        rollDice();

        diceValuesLeft = Arrays.copyOf(dice, dice.length);
        Arrays.sort(diceValuesLeft);
    }

    private void rollDice() {
        turnCount++;
        if (dice[1] < 6) {
            dice[0] += 2;
            dice[1] += 2;
        } else {
            dice[0] = 1;
            dice[1] = 2;
        }
    }

    public boolean move(Location from, Location to) {
        Color fromColor = board.getColor(from);

        if (playerInTurn != fromColor)
            return false;

        if (getNumberOfMovesLeft() == 0)
            return false;

        int diceUsed = findValidDice(from, to);

        if (diceUsed == 0)
            return false;

        board.decrementLocation(from, playerInTurn);
        if (board.RemoveCheckersWithColor(to, playerInTurn.getOpponentColor()))
            board.IncrementBar(playerInTurn.getOpponentColor());                   // Oponent is striked to bar
        board.incrementLocation(to, playerInTurn);
        RemoveDice(diceUsed);

        return true;
    }

    private int findValidDice(Location from, Location to) {
        int diceUsed = 0;

        for (int i = 0; i < diceValuesLeft.length && diceUsed == 0; i++) {
            int d = diceValuesLeft[i];
            if (moveValidator.isValidMove(from, to, d))
                diceUsed = d;
        }
        return diceUsed;
    }

    private void RemoveDice(int diceValue) {

        int[] dice = new int[diceValuesLeft.length - 1];
        int n = 0;
        boolean skippedDice = false;
        for (int d : diceValuesLeft) {
            if (d == diceValue && !skippedDice)
                skippedDice = true;
            else
                dice[n++] = d;
        }
        diceValuesLeft = dice;
    }

    public Color getPlayerInTurn() {
        return playerInTurn;
    }

    public int getNumberOfMovesLeft() {
        return diceValuesLeft().length;
    }

    public int[] diceThrown() {
        return dice;
    }

    public int[] diceValuesLeft() {
        return diceValuesLeft;
    }

    public Color winner() {
        if (turnCount < 6) {
            return Color.NONE;
        } else {
            return Color.RED;
        }
    }

    public Color getColor(Location location) {
        return board.getColor(location);
    }

    public int getCount(Location location) {
        return board.getCount(location);
    }
}
