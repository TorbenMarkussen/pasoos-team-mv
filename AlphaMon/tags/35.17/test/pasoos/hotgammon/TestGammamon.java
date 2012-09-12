package pasoos.hotgammon;

import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.rules.factory.GammaMonFactoryImpl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static pasoos.hotgammon.Location.*;

public class TestGammamon {
    private Game game;

    @Before
    public void setup() throws IllegalAccessException, InstantiationException {
        game = GameFactory.createGame(GammaMonFactoryImpl.class);
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
        blackMoving(R1, R1, B_BEAR_OFF);
        redMoving(B1, B1, R_BEAR_OFF);
        blackMoving(R12, R12, B_BEAR_OFF);
        redMoving(B12, B12, R_BEAR_OFF);
        blackMoving(R12, R12, B_BEAR_OFF);
        redMoving(B12, B12, R_BEAR_OFF);
        blackMoving(R12, B8, B_BEAR_OFF);
        redMoving(B12, R8, R_BEAR_OFF);
        blackMoving(B8, B8, B_BEAR_OFF);
        redMoving(R8, R8, R_BEAR_OFF);
        blackMoving(B6, B6, B_BEAR_OFF);
        redMoving(R6, R6, R_BEAR_OFF);
        blackMoving(B6, B6, B_BEAR_OFF);
        redMoving(R6, R6, R_BEAR_OFF);
        assertEquals(Color.NONE, game.winner());
        game.nextTurn();
        assertTrue(game.move(B6, B_BEAR_OFF));
        assertEquals(Color.BLACK, game.winner());
        assertFalse(game.move(B_BEAR_OFF, B6));

        game.nextTurn();
        assertFalse(game.move(R6, R_BEAR_OFF));
        assertEquals(Color.BLACK, game.winner());
    }
}
