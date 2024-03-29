package pasoos.hotgammon;

import org.junit.Test;
import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.BoardImpl;
import pasoos.hotgammon.rules.factory.BetaMonFactory;
import pasoos.hotgammon.rules.BoardInitializerStrategy;
import pasoos.hotgammon.gameengine.InitializableBoard;

import static junit.framework.Assert.assertTrue;
import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;
import static pasoos.hotgammon.Location.*;

public class TestBetamonCustomBoard {

    private Game createGame(final BoardInitializerStrategy boardInitializerStrategy) {
        Game game = GameFactory.createGame(new BetaMonFactory() {
            @Override
            public Board createBoard() {
                return new BoardImpl(boardInitializerStrategy);
            }
        });
        game.newGame();
        return game;
    }

    private Game createGameCustomBoardA() {
        return createGame(new BoardInitializerStrategy() {
            @Override
            public void initialize(InitializableBoard board) {
                board.clear();
                board.set(R1, RED, 1);
                board.set(R2, RED, 1);
                board.set(R3, RED, 1);
                board.set(R4, RED, 1);
                board.set(R5, RED, 1);
                board.set(R6, RED, 1);

                board.set(B1, BLACK, 1);
                board.set(B2, BLACK, 1);
                board.set(B3, BLACK, 1);
                board.set(B4, BLACK, 1);
                board.set(B5, BLACK, 1);
                board.set(B6, BLACK, 1);
            }
        });
    }

    @Test
    public void should_allow_bear_off_since_all_checkers_are_in_inner_table() {

        Game game = createGameCustomBoardA();

        game.nextTurn();
        assertTrue(game.move(B1, B_BEAR_OFF));
        assertTrue(game.move(B2, B_BEAR_OFF));

        game.nextTurn();
        assertTrue(game.move(R3, R_BEAR_OFF));
        assertTrue(game.move(R4, R_BEAR_OFF));

        game.nextTurn();
        assertTrue(game.move(B5, B_BEAR_OFF));
        assertTrue(game.move(B6, B_BEAR_OFF));
    }
}
