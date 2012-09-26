package pasoos.hotgammon.rules.factory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameFactory;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gameengine.BoardImpl;
import pasoos.hotgammon.gameengine.GameState;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.diceroller.RandomDiceRoller;
import pasoos.hotgammon.rules.validator.BackgammonMoveValidatorStrategy;
import pasoos.hotgammon.rules.winner.BackgammonWinnerStrategy;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;


public class SemiMonFactoryTest {
    private Game game;
    private HotGammonFactory factory;
    private GameState gameState;

    private void assertColorAndCount(Location location, Color color, int count) {
        Assert.assertEquals(color, game.getColor(location));
        Assert.assertEquals(count, game.getCount(location));
    }

    @Before
    public void setup() throws IllegalAccessException, InstantiationException {
        factory = new SemiMonFactory();
        game = GameFactory.createGame(factory);
        game.newGame();
        gameState = mock(GameState.class);
    }

    @Test
    public void should_return_semimon_factory() {
        assertTrue(factory.createBoard() instanceof BoardImpl);
        assertTrue(factory.createDiceRoller() instanceof RandomDiceRoller);
        assertTrue(factory.createWinnerStrategy() instanceof BackgammonWinnerStrategy);
        assertTrue(factory.createMoveValidatorStrategy(gameState, factory.createBoard()) instanceof BackgammonMoveValidatorStrategy);
    }

    @Test
    public void check_initial_setup() {
        assertColorAndCount(Location.R1, Color.BLACK, 2);
        assertColorAndCount(Location.R2, Color.NONE, 0);
        assertColorAndCount(Location.R3, Color.NONE, 0);
        assertColorAndCount(Location.R4, Color.NONE, 0);
        assertColorAndCount(Location.R5, Color.NONE, 0);
        assertColorAndCount(Location.R6, Color.RED, 5);
        assertColorAndCount(Location.R7, Color.NONE, 0);
        assertColorAndCount(Location.R8, Color.RED, 3);
        assertColorAndCount(Location.R9, Color.NONE, 0);
        assertColorAndCount(Location.R10, Color.NONE, 0);
        assertColorAndCount(Location.R11, Color.NONE, 0);
        assertColorAndCount(Location.R12, Color.BLACK, 5);

        assertColorAndCount(Location.B1, Color.RED, 2);
        assertColorAndCount(Location.B2, Color.NONE, 0);
        assertColorAndCount(Location.B3, Color.NONE, 0);
        assertColorAndCount(Location.B4, Color.NONE, 0);
        assertColorAndCount(Location.B5, Color.NONE, 0);
        assertColorAndCount(Location.B6, Color.BLACK, 5);
        assertColorAndCount(Location.B7, Color.NONE, 0);
        assertColorAndCount(Location.B8, Color.BLACK, 3);
        assertColorAndCount(Location.B9, Color.NONE, 0);
        assertColorAndCount(Location.B10, Color.NONE, 0);
        assertColorAndCount(Location.B11, Color.NONE, 0);
        assertColorAndCount(Location.B12, Color.RED, 5);

        Assert.assertEquals(0, game.getCount(Location.B_BAR));
        Assert.assertEquals(0, game.getCount(Location.R_BAR));
        Assert.assertEquals(0, game.getCount(Location.B_BEAR_OFF));
        Assert.assertEquals(0, game.getCount(Location.R_BEAR_OFF));
    }
}
