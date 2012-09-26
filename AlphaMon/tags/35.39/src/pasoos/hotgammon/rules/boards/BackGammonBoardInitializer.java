package pasoos.hotgammon.rules.boards;

import pasoos.hotgammon.gameengine.InitializableBoard;
import pasoos.hotgammon.rules.BoardInitializerStrategy;

import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;
import static pasoos.hotgammon.Location.*;

public class BackGammonBoardInitializer implements BoardInitializerStrategy {
    @Override
    public void initialize(InitializableBoard board) {
        board.clear();
        board.set(R1, BLACK, 2);
        board.set(R6, RED, 5);
        board.set(R8, RED, 3);
        board.set(R12, BLACK, 5);

        board.set(B1, RED, 2);
        board.set(B6, BLACK, 5);
        board.set(B8, BLACK, 3);
        board.set(B12, RED, 5);
    }
}
