package pasoos.hotgammon;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.rules.factory.ZetaMonFactory;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static pasoos.hotgammon.Location.*;

public class TestZetaMon {

    private Game game;

    @Before
    public void setup() throws IllegalAccessException, InstantiationException {
        game = GameFactory.createGame(ZetaMonFactory.class);
        game.newGame();
    }

    private void assertColorAndCount(Location location, Color color, int count) {
        Assert.assertEquals(color, game.getColor(location));
        Assert.assertEquals(count, game.getCount(location));
    }

    @Test
    public void should_have_hypergammon_board_setup() {
        assertColorAndCount(R1, Color.BLACK, 1);
        assertColorAndCount(R2, Color.BLACK, 1);
        assertColorAndCount(R3, Color.BLACK, 1);
        assertColorAndCount(R4, Color.NONE, 0);
        assertColorAndCount(R5, Color.NONE, 0);
        assertColorAndCount(R6, Color.NONE, 0);
        assertColorAndCount(R7, Color.NONE, 0);
        assertColorAndCount(R8, Color.NONE, 0);
        assertColorAndCount(R9, Color.NONE, 0);
        assertColorAndCount(R10, Color.NONE, 0);
        assertColorAndCount(R11, Color.NONE, 0);
        assertColorAndCount(R12, Color.NONE, 0);
        assertColorAndCount(R_BEAR_OFF, Color.NONE, 0);
        assertColorAndCount(R_BAR, Color.NONE, 0);

        assertColorAndCount(B1, Color.RED, 1);
        assertColorAndCount(B2, Color.RED, 1);
        assertColorAndCount(B3, Color.RED, 1);
        assertColorAndCount(B4, Color.NONE, 0);
        assertColorAndCount(B5, Color.NONE, 0);
        assertColorAndCount(B6, Color.NONE, 0);
        assertColorAndCount(B7, Color.NONE, 0);
        assertColorAndCount(B8, Color.NONE, 0);
        assertColorAndCount(B9, Color.NONE, 0);
        assertColorAndCount(B10, Color.NONE, 0);
        assertColorAndCount(B11, Color.NONE, 0);
        assertColorAndCount(B12, Color.NONE, 0);
        assertColorAndCount(B_BEAR_OFF, Color.NONE, 0);
        assertColorAndCount(B_BAR, Color.NONE, 0);
    }

    @Test
    public void should_not_have_winner_until_after_6_turns_even_if_all_checker_are_beard_off_in_3_turns() {
        blackMoving(R1, R2, B_BEAR_OFF);
        redMoving(B1, B2, R_BEAR_OFF);
        assertEquals(Color.NONE, game.winner());
        game.nextTurn();
        assertTrue(game.move(R3, B_BEAR_OFF));
        assertEquals(Color.NONE, game.winner());

        game.nextTurn();
        assertTrue(game.move(B3, R_BEAR_OFF));
        assertEquals(Color.NONE, game.winner());

        game.nextTurn();
        assertEquals(Color.NONE, game.winner());
        game.nextTurn();
        assertEquals(Color.RED, game.winner());
    }

    private void blackMoving(Location from1, Location from2, Location to) {
        game.nextTurn();
        assertTrue(game.move(from1, to));
        assertTrue(game.move(from2, to));
    }

    private void redMoving(Location from1, Location from2, Location to) {
        game.nextTurn();
        assertTrue(game.move(from1, to));
        assertTrue(game.move(from2, to));
    }

}
