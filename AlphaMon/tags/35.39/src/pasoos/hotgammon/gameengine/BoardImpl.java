package pasoos.hotgammon.gameengine;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.rules.BoardInitializerStrategy;
import pasoos.hotgammon.rules.boards.BackGammonBoardInitializer;

import java.util.*;

public class BoardImpl implements Board, InitializableBoard {

    private EnumMap<Location, Integer> board;
    private static final HashMap<Color, List<Location>> outerLocations = new HashMap<Color, List<Location>>();
    private BoardInitializerStrategy boardInitializerStrategy;

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

    public BoardImpl(BoardInitializerStrategy boardInitializerStrategy) {
        this.boardInitializerStrategy = boardInitializerStrategy;

        initialize();
    }

    public BoardImpl() {
        this(new BackGammonBoardInitializer());
    }

    @Override
    public void initialize() {
        board = new EnumMap<Location, Integer>(Location.class);
        for (Location l : Location.values())
            board.put(l, 0);
        boardInitializerStrategy.initialize(this);
    }

    @Override
    public void clear() {
        board.clear();
        for (Location l : Location.values())
            board.put(l, 0);
    }

    @Override
    public Color getColor(Location location) {
        return Color.getColorFromNumerical(board.get(location));
    }

    @Override
    public void decrementLocation(Location location, Color color) {
        board.put(location, board.get(location) - color.getSign());
    }

    @Override
    public void incrementLocation(Location location, Color color) {
        board.put(location, board.get(location) + color.getSign());

    }

    @Override
    public int getCount(Location location) {
        return Math.abs(board.get(location));
    }

    public void decrementLocation(Location location) {
        decrementLocation(location, getColor(location));
    }

    @Override
    public boolean removeCheckersWithColor(Location location, Color color) {
        boolean result = false;
        if (getColor(location) == color) {
            board.put(location, 0);
            result = true;
        }
        return result;
    }

    @Override
    public void incrementBar(Color barColor) {
        if (barColor == Color.BLACK)
            incrementLocation(Location.B_BAR, Color.BLACK);
        else if (barColor == Color.RED)
            incrementLocation(Location.R_BAR, Color.RED);
    }

    @Override
    public boolean hasAllInInnerTable(Color color) {
        List<Location> ol = outerLocations.get(color);

        for (Location l : ol) {
            if (Color.getColorFromNumerical(board.get(l)) == color)
                return false;
        }
        return true;
    }

    @Override
    public void set(Location location, Color color, int count) {
        board.put(location, color.getSign() * count);
    }

    @Override
    public boolean hasAllCheckersOnBearOff(Color color) {

        for (Location l : board.keySet()) {
            Color locationColor = Color.getColorFromNumerical(board.get(l));
            if (l != Location.getBearOff(color))
                if (locationColor == color)
                    return false;
        }
        return true;
    }
}
