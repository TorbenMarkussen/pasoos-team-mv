package pasoos.hotgammon.rules.boards;

import pasoos.hotgammon.Location;
import pasoos.hotgammon.rules.BoardStrategy;

import java.util.EnumMap;

import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;
import static pasoos.hotgammon.Location.*;

public class HybergammonBoardStrategyImpl implements BoardStrategy {
    @Override
    public EnumMap<Location, Integer> create() {
        EnumMap<Location, Integer> board = new EnumMap<Location, Integer>(Location.class);

        for (Location l : Location.values())
            board.put(l, 0);

        board.put(R1, BLACK.getSign());
        board.put(R2, BLACK.getSign());
        board.put(R3, BLACK.getSign());

        board.put(B1, RED.getSign());
        board.put(B2, RED.getSign());
        board.put(B3, RED.getSign());

        return board;
    }
}
