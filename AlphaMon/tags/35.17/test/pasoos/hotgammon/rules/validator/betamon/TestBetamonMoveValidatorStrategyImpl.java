package pasoos.hotgammon.rules.validator.betamon;

import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gameengine.BoardImpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestBetamonMoveValidatorStrategyImpl {
    private BetamonMoveValidatorStrategyImpl betamonMoveValidator;
    private BoardImpl board;

    @Before
    public void setUp() throws Exception {
        board = new BoardImpl();
        betamonMoveValidator = new BetamonMoveValidatorStrategyImpl(board);
    }

    @Test
    public void should_not_be_allowed_to_move_in_wrong_direction() {
        assertFalse(betamonMoveValidator.isValidMove(Location.R12, Location.R10, dice(1)));
    }

    @Test
    public void should_allow_to_move_in_correct_direction() {
        assertTrue(betamonMoveValidator.isValidMove(Location.R12, Location.B11, dice(2)));
    }

    private int dice(int d1) {
        return d1;
    }

    @Test
    public void should_not_allow_to_move_invalid_distance() {
        assertFalse(betamonMoveValidator.isValidMove(Location.R12, Location.B10, dice(1)));
    }

    @Test
    public void should_not_be_allowed_to_move_to_blocked_position() {
        assertFalse(betamonMoveValidator.isValidMove(Location.B6, Location.B1, dice(5)));
        assertFalse(betamonMoveValidator.isValidMove(Location.R12, Location.B12, dice(1)));
    }

    @Test
    public void should_allow_to_move_to_location_with_1_opponent() {
        board.decrementLocation(Location.B12);
        board.decrementLocation(Location.B12);
        board.decrementLocation(Location.B12);
        board.decrementLocation(Location.B12);
        assertTrue(betamonMoveValidator.isValidMove(Location.R12, Location.B12, dice(1)));
    }

    @Test
    public void should_only_be_allowed_to_move_from_bar_if_a_checker_is_there() {
        board.incrementBar(Color.BLACK);
        assertFalse(betamonMoveValidator.isValidMove(Location.R12, Location.B11, dice(1)));
        assertFalse(betamonMoveValidator.isValidMove(Location.R12, Location.B11, dice(2)));
    }

    @Test
    public void should_move_from_bar_to_opponent_inner_table() {
        board.incrementBar(Color.RED);
        board.incrementLocation(Location.B3, Color.BLACK);
        board.incrementLocation(Location.B3, Color.BLACK);
        assertFalse(betamonMoveValidator.isValidMove(Location.R_BAR, Location.B3, dice(3)));
        assertFalse(betamonMoveValidator.isValidMove(Location.R_BAR, Location.B3, dice(4)));
        assertTrue(betamonMoveValidator.isValidMove(Location.R_BAR, Location.B4, dice(4)));
    }


    @Test
    public void should_only_allow_to_move_to_bearoff_if_all_checker_is_in_own_inner_table() {
        board.clear();
        board.set(Location.R5, Color.RED, 2);
        board.set(Location.R3, Color.RED, 4);
        board.set(Location.R_BEAR_OFF, Color.RED, 1);
        board.set(Location.R8, Color.RED, 1);

        board.set(Location.R10, Color.BLACK, 1);
        board.set(Location.R4, Color.BLACK, 1);
        board.set(Location.B4, Color.BLACK, 1);

        assertFalse("Invalid move due to checker on R8", betamonMoveValidator.isValidMove(Location.R5, Location.R_BEAR_OFF, dice(5)));
        assertFalse("Invalid move due to checker on R8", betamonMoveValidator.isValidMove(Location.R5, Location.R_BEAR_OFF, dice(6)));
        board.decrementLocation(Location.R8);
        assertTrue("Valid move due to all checkers in inner table",
                betamonMoveValidator.isValidMove(Location.R5, Location.R_BEAR_OFF, dice(5)));

        assertFalse("Invalid move due to checker on R10 & R4",
                betamonMoveValidator.isValidMove(Location.B4, Location.B_BEAR_OFF, dice(3)));
        assertFalse("Invalid move due to checker on R10 & R4",
                betamonMoveValidator.isValidMove(Location.B4, Location.B_BEAR_OFF, dice(4)));
        board.decrementLocation(Location.R10);
        board.decrementLocation(Location.R4);
        assertTrue("Valid move due to all checkers in inner table",
                betamonMoveValidator.isValidMove(Location.B4, Location.B_BEAR_OFF, dice(4)));

    }

    @Test
    public void should_allow_to_move_checkers_to_bearoff_if_distance_is_less_than_dice_value() {
        board.clear();

        board.set(Location.R3, Color.RED, 4);
        board.set(Location.R_BEAR_OFF, Color.RED, 1);

        board.set(Location.R10, Color.BLACK, 1);
        board.set(Location.R4, Color.BLACK, 1);
        board.set(Location.B4, Color.BLACK, 1);

        assertTrue("dice values exceeds the needed distance", betamonMoveValidator.isValidMove(Location.R3, Location.R_BEAR_OFF, dice(6)));
    }

    @Test
    public void should_not_allow_to_move_checkers_to_bearoff_if_distance_is_less_than_dice_value_and_checkers_on_higher_distance() {
        board.clear();
        board.set(Location.R5, Color.RED, 2);
        board.set(Location.R3, Color.RED, 4);
        board.set(Location.R_BEAR_OFF, Color.RED, 1);

        board.set(Location.R10, Color.BLACK, 1);
        board.set(Location.R4, Color.BLACK, 1);
        board.set(Location.B4, Color.BLACK, 1);

        assertTrue(betamonMoveValidator.isValidMove(Location.R3, Location.R_BEAR_OFF, dice(6)));
    }

    @Test
    public void should_allow_to_bear_off_from_r4_with_dice_value_5_if_no_checker_on_r5_or_r6() {
        board.clear();
        board.set(Location.R4, Color.RED, 3);
        assertTrue(betamonMoveValidator.isValidMove(Location.R4, Location.R_BEAR_OFF, dice(5)));
    }

    @Test
    public void should_fail_to_bear_off_from_r4_with_dice_value_5_if_checker_on_r5() {
        board.clear();
        board.set(Location.R4, Color.RED, 3);
        board.set(Location.R5, Color.RED, 3);
        assertFalse(betamonMoveValidator.isValidMove(Location.R4, Location.R_BEAR_OFF, dice(5)));
    }

    @Test
    public void should_fail_to_bear_off_from_b4_with_dice_value_5_if_checker_on_b5() {
        board.clear();
        board.set(Location.B4, Color.BLACK, 3);
        board.set(Location.B5, Color.BLACK, 3);
        assertFalse(betamonMoveValidator.isValidMove(Location.B4, Location.B_BEAR_OFF, dice(5)));
    }

    @Test
    public void should_allow_to_bear_off_from_b4_with_dice_value_5_if_no_checker_on_r5_or_r6() {
        board.clear();
        board.set(Location.B4, Color.BLACK, 3);
        assertTrue(betamonMoveValidator.isValidMove(Location.B4, Location.B_BEAR_OFF, dice(5)));
    }

    @Test
    public void should_fail_to_bearoff_if_black_checker_on_bar() {
        board.clear();
        board.set(Location.B4, Color.BLACK, 3);
        board.set(Location.B_BAR, Color.BLACK, 3);
        assertFalse(
                betamonMoveValidator.isValidMove(Location.B4, Location.B_BEAR_OFF, dice(5)));
    }

    @Test
    public void should_fail_to_bearoff_if_red_checker_on_bar() {
        board.clear();
        board.set(Location.R4, Color.RED, 3);
        board.set(Location.R_BAR, Color.RED, 3);
        assertFalse(
                betamonMoveValidator.isValidMove(Location.R4, Location.R_BEAR_OFF, dice(5)));
    }

    @Test
    public void should_allow_to_bear_off_from_inner_locations() {
        board.clear();
        assertMove(Location.R1, Color.RED, 1);
        board.clear();
        assertMove(Location.R2, Color.RED, 2);
        board.clear();
        assertMove(Location.R3, Color.RED, 3);
        board.clear();
        assertMove(Location.R4, Color.RED, 4);
        board.clear();
        assertMove(Location.R5, Color.RED, 5);
        board.clear();
        assertMove(Location.R6, Color.RED, 6);

        board.clear();
        assertMove(Location.B1, Color.BLACK, 1);
        board.clear();
        assertMove(Location.B2, Color.BLACK, 2);
        board.clear();
        assertMove(Location.B3, Color.BLACK, 3);
        board.clear();
        assertMove(Location.B4, Color.BLACK, 4);
        board.clear();
        assertMove(Location.B5, Color.BLACK, 5);
        board.clear();
        assertMove(Location.B6, Color.BLACK, 6);
    }

    private void assertMove(Location location, Color color, int dice) {
        board.set(location, color, 3);
        assertTrue(betamonMoveValidator.isValidMove(location, Location.getBearOff(color), dice(dice)));
    }

    //should_allow_to_move_checkers_to_bearoff_if_distance_is_less_than_dice_value_except_yada_yada
    //


}
