package pasoos.hotgammon;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.BoardImpl;
import pasoos.hotgammon.gameengine.InitializableBoard;
import pasoos.hotgammon.rules.BoardInitializerStrategy;
import pasoos.hotgammon.rules.factory.BetaMonFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;
import static pasoos.hotgammon.Location.*;

public class TestGameObserver implements BoardInitializerStrategy {

    private Game game;
    private GameObserver observer;

    @Before
    public void setup() throws InstantiationException, IllegalAccessException {
        final BoardInitializerStrategy boardInitializerStrategy = this;
        game = GameFactory.createGame(new BetaMonFactory() {
            @Override
            public Board createBoard() {
                return new BoardImpl(boardInitializerStrategy);
            }
        });
        observer = mock(GameObserver.class);
        game.addObserver(observer);
    }

    @Override
    public void initialize(InitializableBoard board) {
        board.clear();
        board.set(R12, BLACK, 1);
        board.set(R6, RED, 1);

        board.set(B6, BLACK, 1);
        board.set(B5, RED, 1);
        board.set(R3, BLACK, 1);
    }

    @Test
    public void observer_should_be_notified_about_dice_rolls() {
        game.nextTurn();
        verify(observer).diceRolled(new int[]{2, 1});

        game.nextTurn();
        verify(observer).diceRolled(new int[]{4, 3});
    }

    @Test
    public void observer_should_be_notified_about_checker_moves() {
        game.nextTurn();
        assertTrue(game.move(R12, B11));
        verify(observer).checkerMove(R12, B11);

        game.nextTurn();
        assertTrue(game.move(R6, R3));
        verify(observer).checkerMove(R6, R3);
    }

    @Test
    public void should_not_notify_if_move_was_invalid() {
        game.nextTurn();

        assertFalse(game.move(R12, R8));

        verify(observer, times(1)).diceRolled(new int[]{2, 1});
        verify(observer, times(0)).checkerMove(any(Location.class), any(Location.class));
    }

    @Test
    public void should_notify_twice_if_a_black_move_causes_a_bar_move() {
        game.nextTurn();

        assertTrue(game.move(B6, B5));

        verify(observer, times(1)).diceRolled(new int[]{2, 1});
        verify(observer, times(1)).checkerMove(B5, R_BAR);
        verify(observer, times(1)).checkerMove(B6, B5);
        verify(observer, times(2)).checkerMove(any(Location.class), any(Location.class));
    }

    @Test
    public void should_notify_twice_if_a_red_move_causes_a_bar_move() {
        game.nextTurn();
        assertTrue(game.move(R12, B11));
        verify(observer).checkerMove(R12, B11);

        game.nextTurn();

        assertTrue(game.move(R6, R3));
        verify(observer).checkerMove(R6, R3);
        verify(observer).checkerMove(R3, B_BAR);
        verify(observer, times(3)).checkerMove(any(Location.class), any(Location.class));
    }

    @Test
    public void should_notify_turn_ended() {
        game.nextTurn();
        assertTrue(game.move(R12, B11));
        assertTrue(game.move(B11, B10));

        InOrder inOrder = inOrder(observer);
        inOrder.verify(observer).checkerMove(R12, B11);
        inOrder.verify(observer).checkerMove(B11, B10);
        inOrder.verify(observer, times(1)).turnEnded();
    }

    @Test
    public void should_notify_turn_ended_if_no_more_valid_moves() {
        game.nextTurn();
        assertTrue(game.move(R12, B11));
        assertTrue(game.move(B11, B10));

        InOrder inOrder = inOrder(observer);
        inOrder.verify(observer).checkerMove(R12, B11);
        inOrder.verify(observer).checkerMove(B11, B10);
        inOrder.verify(observer, times(1)).turnEnded();
    }


}
