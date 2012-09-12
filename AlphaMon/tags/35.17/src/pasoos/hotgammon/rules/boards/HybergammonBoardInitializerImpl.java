package pasoos.hotgammon.rules.boards;

import pasoos.hotgammon.gameengine.InitializableBoard;
import pasoos.hotgammon.rules.BoardInitializerStrategy;

import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;
import static pasoos.hotgammon.Location.*;

public class HybergammonBoardInitializerImpl implements BoardInitializerStrategy {
    @Override
    public void initialize(InitializableBoard board) {
        board.clear();

        board.set(R1, BLACK, 1);
        board.set(R2, BLACK, 1);
        board.set(R3, BLACK, 1);

        board.set(B1, RED, 1);
        board.set(B2, RED, 1);
        board.set(B3, RED, 1);
    }
}
