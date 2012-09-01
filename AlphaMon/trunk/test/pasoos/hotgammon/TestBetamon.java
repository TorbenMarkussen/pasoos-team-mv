package pasoos.hotgammon;

import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 28-08-12
 * Time: 20:08
 * To change this template use File | Settings | File Templates.
 */
public class TestBetamon {

    private Game game;

    @Before
    public void setup() {
        game = GameFactory.Get(HotGammonTypes.BetaMon);
        game.newGame();
    }

    //red case + black case
    @Test
    public void should_not_be_allowed_to_move_in_wrong_direction() {
        game.nextTurn(); // will throw [1,2] and thus black starts
        assertFalse(game.move(Location.R12, Location.R10));
    }

    @Test
    public void should_allow_to_move_in_correct_direction() {
        game.nextTurn(); // will throw [1,2] and thus black starts
        assertTrue(game.move(Location.R12, Location.B11));
    }

    @Test
    public void should_only_allow_to_move_distances_given_by_dice_2_before_1() {
        game.nextTurn(); // will throw [1,2] and thus black starts
        assertTrue(game.move(Location.B6, Location.B4));
        assertFalse(game.move(Location.B6, Location.B4));
        assertTrue(game.move(Location.B6, Location.B5));
    }

    @Test
    public void should_only_allow_to_move_distances_given_by_dice_1_before_2() {
        game.nextTurn(); // will throw [1,2] and thus black starts
        assertTrue(game.move(Location.B6, Location.B5));
        assertFalse(game.move(Location.B6, Location.B5));
        assertTrue(game.move(Location.B6, Location.B4));
    }

    @Test
    public void should_move_opponent_checker_to_bar() {
        game.nextTurn(); // will throw [1,2] and thus black starts
        assertTrue(game.move(Location.B8, Location.B7));
        assertTrue(game.move(Location.B7, Location.B5));
        game.nextTurn(); // will throw [3,4] and white has the turn
        assertTrue(game.move(Location.B1, Location.B5));// Red strikes Black to the bar
        assertEquals("There should be 1 checker on B5 after strike", 1, game.getCount(Location.B5));
        assertEquals("There should be a RED checker on B5 after strike", Color.RED, game.getColor(Location.B5));
        assertEquals("There should be 1 checker on B_BAR after strike", 1, game.getCount(Location.B_BAR));
    }


}
