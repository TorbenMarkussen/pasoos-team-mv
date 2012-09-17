package pasoos.hotgammon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.rules.factory.AlphaMonFactory;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing skeleton for AlphaMon.
 * <p/>
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * <p/>
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class TestAlphamon {
    private Game game;

    private void assertColorAndCount(Location location, Color color, int count) {
        Assert.assertEquals(color, game.getColor(location));
        Assert.assertEquals(count, game.getCount(location));
    }

    @Before
    public void setup() throws IllegalAccessException, InstantiationException {
        game = GameFactory.createGame(AlphaMonFactory.class);
        game.newGame();
    }

    @Test
    public void should_have_no_player_in_turn_after_new_game() {
        Assert.assertEquals(Color.NONE, game.getPlayerInTurn());
    }

    @Test
    public void should_have_black_player_in_turn_after_first_turn_and_die_values_are_1_and_2() {
        game.nextTurn(); // will throw [1,2] and thus black starts
        Assert.assertEquals(Color.BLACK, game.getPlayerInTurn());
        assertTrue(Arrays.equals(new int[]{2, 1}, game.diceThrown()));
    }

    @Test
    public void should_have_red_player_in_turn_after_second_turn_and_die_values_are_3_and_4() {
        game.nextTurn(); // will throw [1,2] and thus black starts
        game.nextTurn(); // will throw [3,4] and red is in turn
        Assert.assertEquals(Color.RED, game.getPlayerInTurn());
        assertTrue(Arrays.equals(new int[]{4, 3}, game.diceThrown()));
    }

    @Test
    public void should_have_die_values_1_and_2_after_having_thrown_5_and_6() {
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        assertTrue(Arrays.equals(new int[]{6, 5}, game.diceThrown()));
        game.nextTurn();
        assertTrue(Arrays.equals(new int[]{2, 1}, game.diceThrown()));
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


    // Moving a checker from R1 to R2 at the start of the game is valid
    // according to the AlphaMon rules. After the move there is only one move
    // left for Black to make.
    @Test
    public void should_have_one_black_checker_on_r1_and_r2_after_first_move() {
        game.nextTurn();
        assertTrue(game.move(Location.R1, Location.R2));
        assertColorAndCount(Location.R1, Color.BLACK, 1);
        assertColorAndCount(Location.R2, Color.BLACK, 1);
        Assert.assertEquals(1, game.getNumberOfMovesLeft());
    }

    //After moving the two black checkers, the number of moves left is 0
    @Test
    public void should_have_zero_moves_left_after_black_moves_two_checkers() {
        game.nextTurn();
        assertTrue(game.move(Location.R1, Location.R2));
        assertTrue(game.move(Location.R1, Location.R3));
        Assert.assertEquals(0, game.getNumberOfMovesLeft());
    }

    // Moving a checker from R1 to B1 at the start of the game is invalid as there is an opponent (red) checker there.
    @Test
    public void should_not_allow_to_move_checker_to_blocked_location() {
        game.nextTurn();
        assertFalse(game.move(Location.R1, Location.B1));
    }

    //It is not possible to move a players checker which is not in turn
    @Test
    public void should_not_allow_to_move_opponents_checkers() {
        game.nextTurn();
        assertFalse(game.move(Location.R8, Location.R9));
    }

    //It is not possible to move a checker for a player that has no more moves left
    @Test
    public void should_not_be_possible_to_move_checkers_after_all_moves_are_used() {
        game.nextTurn();
        assertTrue(game.move(Location.R12, Location.B11));
        assertTrue(game.move(Location.R12, Location.B11));
        assertFalse(game.move(Location.R12, Location.B11));
    }

    //Moving a checker from R1 to B6 at the start of the game is valid as there is a checker of its own.
    @Test
    public void should_be_possible_to_move_checkers_to_location_with_same_color() {
        game.nextTurn();
        assertTrue(game.move(Location.R1, Location.B6));
    }

    //Start a game where white in turns will remove all checkers from B1
    //and check checkers R1 to B1 is now valid as B1 is now empty.
    @Test
    public void should_be_possible_to_move_checker_to_a_location_where_opponent_has_removed_checkers() {
        game.nextTurn();
        game.move(Location.R12, Location.R11);
        game.move(Location.R12, Location.R11);
        game.nextTurn();
        game.move(Location.B1, Location.B2);
        game.move(Location.B1, Location.B2);
        game.nextTurn();
        assertTrue(game.move(Location.R1, Location.B1));
    }

    //It is not possible to move checkers from an empty location
    @Test
    public void should_not_be_possible_to_move_from_an_empty_location() {
        game.nextTurn();
        assertFalse(game.move(Location.R2, Location.B3));
    }

    //Game is over after 6 rolls of the dices, Red is winner.
    @Test
    public void should_have_red_as_winner_after_6_rolls() {
        game.nextTurn();
        Assert.assertEquals(Color.NONE, game.winner());
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        Assert.assertEquals(Color.NONE, game.winner());
        game.nextTurn();
        Assert.assertEquals(Color.RED, game.winner());
    }

}