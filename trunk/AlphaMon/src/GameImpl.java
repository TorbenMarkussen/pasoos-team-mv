import java.util.Arrays;

/**
 * Skeleton implementation of HotGammon.
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */

public class GameImpl implements Game {

    private Color playerInTurn;
	private int[] dice;
	private int[] board;
	private int[] diceValuesLeft;

	public void newGame() {
		playerInTurn = Color.NONE;
		dice = new int[] { -1, 0 };
		board = InitBoard();
	}

	private int[] InitBoard() {
		int[] result = new int[28];
		for (int i = 0; i < result.length; i++) {
			result[i] = 0;
		}

		result[Location.R1.ordinal()] = Color.BLACK.getSign()*2;
		result[Location.R6.ordinal()] = Color.RED.getSign()*5;
		result[Location.R8.ordinal()] = Color.RED.getSign()*3;
		result[Location.R12.ordinal()] = Color.BLACK.getSign()*5;

		result[Location.B1.ordinal()] = Color.RED.getSign()*2;
		result[Location.B6.ordinal()] = Color.BLACK.getSign()*5;
		result[Location.B8.ordinal()] = Color.BLACK.getSign()*3;
		result[Location.B12.ordinal()] = Color.RED.getSign()*5;

		result[Location.B_BAR.ordinal()] = Color.BLACK.getSign()*0;
		result[Location.B_BEAR_OFF.ordinal()] = Color.BLACK.getSign()*0;
		result[Location.R_BAR.ordinal()] = Color.RED.getSign()*0;
		result[Location.R_BEAR_OFF.ordinal()] = Color.RED.getSign()*0;

		return result;
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
		if (dice[1] < 6) {
			dice[0] += 2;
			dice[1] += 2;
		} else {
			dice[0] = 1;
			dice[1] = 2;
		}
	}

	public boolean move(Location from, Location to) {
		board[from.ordinal()]--;
		board[to.ordinal()]++;
		//int distance = Location.distance(from, to) ;
		diceValuesLeft = Arrays.copyOf(diceValuesLeft, diceValuesLeft.length-1) ;
		return true;		
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
		return Color.NONE;
	}

	public Color getColor(Location location) {
		return Color.getColorFromNumerical(board[location.ordinal()]);
	}

	public int getCount(Location location) {
		return Math.abs(board[location.ordinal()]);
	}
}
