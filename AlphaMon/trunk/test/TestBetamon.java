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
        game = new GameImpl(new MoveValidatorFactoryImpl(HotGammonTypes.BetaMon));
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
    public void should_move_opponent_checker_to_bar_yada_yada() {

    }
    //should_move_from_bar_to_opponent_inner_table


}
