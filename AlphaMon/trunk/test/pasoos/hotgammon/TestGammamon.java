package pasoos.hotgammon;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: tor
 * Date: 02-09-12
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
public class TestGammamon {
    private Game game;

    @Before
    public void setup() {
        game = GameFactory.Get(HotGammonTypes.Gammamon);
        game.newGame();
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

    @Test
    public void play_gammamon_until_game_has_finished_and_winner_is_found() {
        blackMoving(Location.R1, Location.R1, Location.B_BEAR_OFF);
        redMoving(Location.B1, Location.B1, Location.R_BEAR_OFF);
        blackMoving(Location.R12, Location.R12, Location.B_BEAR_OFF);
        redMoving(Location.B12, Location.B12, Location.R_BEAR_OFF);
        blackMoving(Location.R12, Location.R12, Location.B_BEAR_OFF);
        redMoving(Location.B12, Location.B12, Location.R_BEAR_OFF);
        blackMoving(Location.R12, Location.B8, Location.B_BEAR_OFF);
        redMoving(Location.B12, Location.R8, Location.R_BEAR_OFF);
        blackMoving(Location.B8, Location.B8, Location.B_BEAR_OFF);
        redMoving(Location.R8, Location.R8, Location.R_BEAR_OFF);
        blackMoving(Location.B6, Location.B6, Location.B_BEAR_OFF);
        redMoving(Location.R6, Location.R6, Location.R_BEAR_OFF);
        blackMoving(Location.B6, Location.B6, Location.B_BEAR_OFF);
        redMoving(Location.R6, Location.R6, Location.R_BEAR_OFF);
        assertEquals(Color.NONE, game.winner());
        game.nextTurn();
        assertTrue(game.move(Location.B6, Location.B_BEAR_OFF));
        assertEquals(Color.BLACK, game.winner());
        assertFalse(game.move(Location.B_BEAR_OFF, Location.B6));

        game.nextTurn();
        assertFalse(game.move(Location.R6, Location.R_BEAR_OFF));
        assertEquals(Color.BLACK, game.winner());
    }
}
