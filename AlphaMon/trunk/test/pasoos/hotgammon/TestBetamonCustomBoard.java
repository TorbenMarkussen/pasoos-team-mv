package pasoos.hotgammon;

import org.junit.Test;
import org.mockito.InOrder;
import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.BoardImpl;
import pasoos.hotgammon.gameengine.InitializableBoard;
import pasoos.hotgammon.rules.BoardInitializerStrategy;
import pasoos.hotgammon.rules.factory.BetaMonFactory;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
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

    private Game createGameCustomBoardB() {
        return createGame(new BoardInitializerStrategy() {
            @Override
            public void initialize(InitializableBoard board) {
                board.clear();
                board.set(R_BAR, RED, 1);
                board.set(R1, RED, 2);
                board.set(R2, RED, 2);

                board.set(B_BAR, BLACK, 1);
                board.set(B3, BLACK, 1);
                board.set(B4, BLACK, 1);
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

    @Test
    public void should_give_up_turn_if_no_moves_are_possible_for_player_in_turn() {

        Game game = createGameCustomBoardB();

        game.nextTurn();
        assertEquals(0, game.diceValuesLeft().length);

        game.nextTurn();
        assertTrue(game.move(R_BAR, B3));
    }

    @Test
    public void should_notify_observer_if_no_moves_are_possible_for_player_in_turn() {

        Game game = createGameCustomBoardB();
        GameObserver observer = mock(GameObserver.class);
        game.addObserver(observer);

        game.nextTurn();
        assertEquals(0, game.diceValuesLeft().length);

        InOrder inOrder = inOrder(observer);
        inOrder.verify(observer).diceRolled(any(int[].class));
        inOrder.verify(observer).turnEnded();

        game.nextTurn();
        assertTrue(game.move(R_BAR, B3));
    }

}
