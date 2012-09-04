package pasoos.hotgammon.rules.boards;

import pasoos.hotgammon.Location;
import pasoos.hotgammon.rules.BoardStrategy;

import java.util.EnumMap;

import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;
import static pasoos.hotgammon.Location.*;

public class BackGammonBoardStrategyImpl implements BoardStrategy {
    @Override
    public EnumMap<Location, Integer> create() {
        EnumMap<Location, Integer> board = new EnumMap<Location, Integer>(Location.class);

        for (Location l : Location.values())
            board.put(l, 0);

        board.put(R1, BLACK.getSign() * 2);
        board.put(R6, RED.getSign() * 5);
        board.put(R8, RED.getSign() * 3);
        board.put(R12, BLACK.getSign() * 5);

        board.put(B1, RED.getSign() * 2);
        board.put(B6, BLACK.getSign() * 5);
        board.put(B8, BLACK.getSign() * 3);
        board.put(B12, RED.getSign() * 5);

        return board;
    }
}
