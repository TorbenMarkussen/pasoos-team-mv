package pasoos.hotgammon.gameengine;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 28-08-12
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
public class Board {

    //private int[] board;
    private EnumMap<Location, Integer> board = new EnumMap<Location, Integer>(Location.class);
    private static final HashMap<Color, List<Location>> outerLocations = new HashMap<Color, List<Location>>();

    static {
        List<Location> all = new ArrayList<Location>(Arrays.asList(Location.values()));

        List<Location> outerRed = new ArrayList<Location>(all);
        outerRed.remove(Location.R1);
        outerRed.remove(Location.R2);
        outerRed.remove(Location.R3);
        outerRed.remove(Location.R4);
        outerRed.remove(Location.R5);
        outerRed.remove(Location.R6);
        outerRed.remove(Location.R_BEAR_OFF);

        outerLocations.put(Color.RED, outerRed);

        List<Location> outerBlack = new ArrayList<Location>(all);
        outerBlack.remove(Location.B1);
        outerBlack.remove(Location.B2);
        outerBlack.remove(Location.B3);
        outerBlack.remove(Location.B4);
        outerBlack.remove(Location.B5);
        outerBlack.remove(Location.B6);
        outerBlack.remove(Location.B_BEAR_OFF);
        outerLocations.put(Color.BLACK, outerBlack);

    }

    public Board() {
        initialize();
    }

    private void initBoard() {
        clear();

        board.put(Location.R1, Color.BLACK.getSign() * 2);
        board.put(Location.R6, Color.RED.getSign() * 5);
        board.put(Location.R8, Color.RED.getSign() * 3);
        board.put(Location.R12, Color.BLACK.getSign() * 5);

        board.put(Location.B1, Color.RED.getSign() * 2);
        board.put(Location.B6, Color.BLACK.getSign() * 5);
        board.put(Location.B8, Color.BLACK.getSign() * 3);
        board.put(Location.B12, Color.RED.getSign() * 5);

    }

    public void clear() {
        board.clear();
        for (Location l : Location.values())
            board.put(l, 0);
    }

    public Color getColor(Location location) {
        return Color.getColorFromNumerical(board.get(location));
    }

    public void decrementLocation(Location location, Color color) {
        board.put(location, board.get(location) - color.getSign());
    }

    public void incrementLocation(Location location, Color color) {
        board.put(location, board.get(location) + color.getSign());

    }

    public int getCount(Location location) {
        return Math.abs(board.get(location));
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
            board.put(location, 0);
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
        List<Location> ol = outerLocations.get(color);

        for (Location l : ol) {
            if (Color.getColorFromNumerical(board.get(l)) == color)
                return false;
        }
        return true;
    }

    public void set(Location location, Color color, int count) {
        board.put(location, color.getSign() * count);
    }
}
