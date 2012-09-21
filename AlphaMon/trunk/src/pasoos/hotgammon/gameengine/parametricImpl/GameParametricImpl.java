package pasoos.hotgammon.gameengine.parametricImpl;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gameengine.Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static pasoos.hotgammon.Color.*;

public class GameParametricImpl implements Game {

    private Color playerInTurn;
    private List<Integer> dice;
    private Board board;
    private ArrayList<Integer> diceValuesLeft;
    private int turnCount;
    private Color theWinner;
    private BoardType boardType;
    private final MoveRules moveRules;
    private final WinningRules winningRules;
    private final DiceRollingType diceRollingType;
    private Random dieRandomizer;


    public GameParametricImpl(BoardType boardType, MoveRules moveRules, WinningRules winningRules, DiceRollingType diceRollingType) {
        this.boardType = boardType;
        this.moveRules = moveRules;
        this.winningRules = winningRules;
        this.diceRollingType = diceRollingType;
        dieRandomizer = new Random();
        dieRandomizer.setSeed(System.currentTimeMillis());


        if (boardType != BoardType.Backgammon &&
                boardType != BoardType.HyperGammon)
            throw new InvalidBoardTypeException();

        if (winningRules != WinningRules.BackgammonRules &&
                winningRules != WinningRules.SimpleWinningRules)
            throw new InvalidWinningRulesException();

        if (moveRules != MoveRules.AlphamonMoveRules &&
                moveRules != MoveRules.BackgammonMoveRules &&
                moveRules != MoveRules.AlternatingMoveRules)
            throw new InvalidMoveRulesException();

        if (diceRollingType != DiceRollingType.FixedDices &&
                diceRollingType != DiceRollingType.RandomDices)
            throw new InvalidDiceRollingTypeException();
    }

    public void newGame() {
        playerInTurn = NONE;
        dice = new ArrayList<Integer>();
        diceValuesLeft = new ArrayList<Integer>();
        turnCount = 0;
        theWinner = NONE;
        if (boardType == BoardType.Backgammon)
            initializeBackgammonBoard();
        else if (boardType == BoardType.HyperGammon)
            initializeHypergammonBoard();
    }

    private void initializeHypergammonBoard() {
        //todo
    }

    private void initializeBackgammonBoard() {
        //todo
    }


    public void nextTurn() {
        turnCount++;
        theWinner = determineWinner();

        if (playerInTurn != Color.BLACK)
            playerInTurn = Color.BLACK;
        else
            playerInTurn = RED;

        rollDice();
        diceValuesLeft = new ArrayList<Integer>(dice);
    }

    private Color determineWinner() {
        if (winningRules == WinningRules.SimpleWinningRules) {
            return turnCount < 6 ? NONE : RED;
        } else if (winningRules == WinningRules.BackgammonRules) {
            return allCheckersOnBearOff(BLACK) ? BLACK : allCheckersOnBearOff(RED) ? RED : NONE;
        }
        return NONE;
    }

    private boolean allCheckersOnBearOff(Color color) {
        //todo
        return false;
    }

    private void rollDice() {
        dice = new ArrayList<Integer>();
        for (int d : rollDices())
            dice.add(d);
        Collections.sort(dice, Collections.reverseOrder());
    }

    private int[] rollDices() {
        if (diceRollingType == DiceRollingType.FixedDices) {
            int nextDice = dice.get(1);
            nextDice++;
            if (nextDice > 6)
                nextDice = 1;

            int[] diceRoll = new int[2];
            diceRoll[0] = nextDice++;
            diceRoll[1] = nextDice;

            return diceRoll;

        } else if (diceRollingType == DiceRollingType.RandomDices) {
            return new int[]{dieRandomizer.nextInt(6) + 1, dieRandomizer.nextInt(6) + 1};
        }
        return new int[]{4, 2};
    }

    public boolean move(Location from, Location to) {
        Color fromColor = board.getColor(from);

        if (theWinner != NONE)
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

        theWinner = determineWinner();

        return true;
    }

    private int findValidDice(Location from, Location to) {
        int diceUsed = 0;

        for (int i = 0; i < diceValuesLeft.size() && diceUsed == 0; i++) {
            int d = diceValuesLeft.get(i);
            if (isValidMove(from, to, d))
                diceUsed = d;
        }
        return diceUsed;
    }

    private boolean isValidMove(Location from, Location to, int dieValue) {
        switch (moveRules) {
            case BackgammonMoveRules:
                return isValidBackgammonMove(from, to, dieValue);
            case AlphamonMoveRules:
                return isValidAlphamonMove(from, to, dieValue);
            case AlternatingMoveRules:
                switch (playerInTurn) {
                    case BLACK:
                        return isValidAlphamonMove(from, to, dieValue);
                    case RED:
                        return isValidBackgammonMove(from, to, dieValue);
                    default:
                        return false;
                }
        }
        return false;
    }

    private boolean isValidBackgammonMove(Location from, Location to, int dieValue) {
        //todo
        return true;
    }

    private boolean isValidAlphamonMove(Location from, Location to, int dieValue) {
        //todo
        return true;
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

    static public int main(String[] args) {
        Game alphamonGame = new GameParametricImpl(
                BoardType.Backgammon,
                MoveRules.AlphamonMoveRules,
                WinningRules.SimpleWinningRules,
                DiceRollingType.FixedDices);
        return 0;
    }
}
