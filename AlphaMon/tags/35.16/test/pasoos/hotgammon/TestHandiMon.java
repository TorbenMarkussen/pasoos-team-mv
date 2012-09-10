package pasoos.hotgammon;

import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.rules.factory.HandiMonRules;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static pasoos.hotgammon.Location.*;

public class TestHandiMon {
    private Game game;

    @Before
    public void setup() throws IllegalAccessException, InstantiationException {
        game = GameFactory.createGame(HandiMonRules.class);
        game.newGame();
    }

    @Test
    public void should_be_possible_to_move_to_any_location_if_BLACK_is_in_turn() {
        game.nextTurn();
        assertTrue(game.move(R1, R9));
        assertTrue(game.move(R1, B2));
    }

    @Test
    public void should_only_be_possible_to_move_to_valid_location_if_RED_is_in_turn() {
        game.nextTurn();
        assertTrue(game.move(B6, B5));
        assertTrue(game.move(B6, B4));
        game.nextTurn();
        assertTrue(game.move(B1, B5));
        assertFalse(game.move(B1, B10));
    }

    @Test
    public void should_not_be_possible_to_move_to_a_location_with_one_RED_checker_if_BLACK_is_in_turn() {
        game.nextTurn();
        game.nextTurn();
        assertTrue(game.move(R6, R3));
        assertTrue(game.move(R6, R2));
        game.nextTurn();
        assertFalse(game.move(R8, R2));
    }

}
