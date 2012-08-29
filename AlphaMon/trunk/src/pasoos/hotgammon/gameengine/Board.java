package pasoos.hotgammon.gameengine;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.gameengine.Location;

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

    private int[] initBoard() {
        int[] result = new int[28];
        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }

        result[Location.R1.ordinal()] = Color.BLACK.getSign() * 2;
        result[Location.R6.ordinal()] = Color.RED.getSign() * 5;
        result[Location.R8.ordinal()] = Color.RED.getSign() * 3;
        result[Location.R12.ordinal()] = Color.BLACK.getSign() * 5;

        result[Location.B1.ordinal()] = Color.RED.getSign() * 2;
        result[Location.B6.ordinal()] = Color.BLACK.getSign() * 5;
        result[Location.B8.ordinal()] = Color.BLACK.getSign() * 3;
        result[Location.B12.ordinal()] = Color.RED.getSign() * 5;

        return result;
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
        board = initBoard();
    }

    public void decrementLocation(Location location) {
        decrementLocation(location, getColor(location));
    }
}
