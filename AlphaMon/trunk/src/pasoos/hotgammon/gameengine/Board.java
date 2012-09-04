package pasoos.hotgammon.gameengine;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.rules.BoardStrategy;
import pasoos.hotgammon.rules.boards.BackGammonBoardStrategyImpl;

import java.util.*;

public class Board {

    private EnumMap<Location, Integer> board;
    private static final HashMap<Color, List<Location>> outerLocations = new HashMap<Color, List<Location>>();
    private BoardStrategy boardStrategy;

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

    public Board(BoardStrategy boardStrategy) {
        this.boardStrategy = boardStrategy;
        initialize();
    }

    public Board() {
        this(new BackGammonBoardStrategyImpl());
    }

    public void initialize() {
        board = boardStrategy.create();
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

    public void decrementLocation(Location location) {
        decrementLocation(location, getColor(location));
    }

    public boolean removeCheckersWithColor(Location location, Color color) {
        boolean result = false;
        if (getColor(location) == color) {
            board.put(location, 0);
            result = true;
        }
        return result;
    }

    public void incrementBar(Color barColor) {
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

    public boolean allCheckersOnBearOff(Color color) {

        for (Location l : board.keySet()) {
            Color locationColor = Color.getColorFromNumerical(board.get(l));
            if (l != Location.getBearOff(color))
                if (locationColor == color)
                    return false;
        }
        return true;
    }
}
