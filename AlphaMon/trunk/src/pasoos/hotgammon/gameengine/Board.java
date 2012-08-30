package pasoos.hotgammon.gameengine;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 28-08-12
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
public class Board {

    private int[] board;

    public Board() {
        initialize();
    }

    private void initBoard() {
        clear();

        board[Location.R1.ordinal()] = Color.BLACK.getSign() * 2;
        board[Location.R6.ordinal()] = Color.RED.getSign() * 5;
        board[Location.R8.ordinal()] = Color.RED.getSign() * 3;
        board[Location.R12.ordinal()] = Color.BLACK.getSign() * 5;

        board[Location.B1.ordinal()] = Color.RED.getSign() * 2;
        board[Location.B6.ordinal()] = Color.BLACK.getSign() * 5;
        board[Location.B8.ordinal()] = Color.BLACK.getSign() * 3;
        board[Location.B12.ordinal()] = Color.RED.getSign() * 5;

    }

    public void clear() {
        board = new int[28];
        for (int i = 0; i < board.length; i++) {
            board[i] = 0;
        }
    }

    public Color getColor(Location location) {
        return Color.getColorFromNumerical(board[location.ordinal()]);
    }

    public void decrementLocation(Location from, Color value) {
        board[from.ordinal()] -= value.getSign();
    }

    public void incrementLocation(Location to, Color value) {
        board[to.ordinal()] += value.getSign();
    }

    public int getCount(Location location) {
        return Math.abs(board[location.ordinal()]);
    }

    public void initialize() {
        initBoard();
    }

    public void decrementLocation(Location location) {
        decrementLocation(location, getColor(location));
    }

    public boolean RemoveCheckersWithColor(Location location, Color color) {
        boolean result = false;
        if (getColor(location) == color) {
            board[location.ordinal()] = 0;
            result = true;
        }
        return result;
    }

    public void IncrementBar(Color barColor) {
        if (barColor == Color.BLACK)
            incrementLocation(Location.B_BAR, Color.BLACK);
        else if (barColor == Color.RED)
            incrementLocation(Location.R_BAR, Color.RED);
    }

    public boolean allInInnerTable(Color color) {
/*        if (color == Color.RED) {
            for (int i = Location.R7.ordinal(); i <= Location.R_BAR.ordinal(); i++)   // 7 -> 25
                if (Color.getColorFromNumerical(board[i]) == color)
                    return false;
        } else if (color == Color.BLACK) {
            for (int i = Location.B_BAR.ordinal(); i <= Location.B7.ordinal(); i++)      // 0 -> 18
                if (Color.getColorFromNumerical(board[i]) == color)
                    return false;
        }*/
        return true;
    }
}
