package pasoos.hotgammon;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pasoos.hotgammon.rules.factory.AlphaMonFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static pasoos.hotgammon.Location.*;

public class TestGameObserver {

    private Game game;
    private GameObserver observer;

    @Before
    public void setup() throws InstantiationException, IllegalAccessException {
        game = GameFactory.createGame(AlphaMonFactory.class);
        observer = mock(GameObserver.class);
        game.addObserver(observer);
    }

    @Test
    public void observer_should_be_notified_about_dice_rolls() {
        game.nextTurn();
        Mockito.verify(observer).diceRolled(new int[]{2, 1});

        game.nextTurn();
        Mockito.verify(observer).diceRolled(new int[]{4, 3});
    }

    @Test
    public void observer_should_be_notified_about_checker_moves() {
        game.nextTurn();
        assertTrue(game.move(R12, B11));
        Mockito.verify(observer).checkerMove(R12, B11);

        game.nextTurn();
        assertTrue(game.move(R6, R4));
        Mockito.verify(observer).checkerMove(R6, R4);
    }

    @Test
    public void should_not_notify_if_move_was_invalid() {
        game.nextTurn();

        assertFalse(game.move(R12, R8));

        Mockito.verify(observer, times(1)).diceRolled(new int[]{2, 1});
        Mockito.verify(observer, times(0)).checkerMove(any(Location.class), any(Location.class));
    }

}
