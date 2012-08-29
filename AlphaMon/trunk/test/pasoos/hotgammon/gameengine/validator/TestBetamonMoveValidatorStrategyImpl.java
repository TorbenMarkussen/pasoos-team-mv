package pasoos.hotgammon.gameengine.validator;

import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.Location;
import pasoos.hotgammon.gameengine.validator.betamon.BetamonMoveValidatorStrategyImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 28-08-12
 * Time: 21:21
 * To change this template use File | Settings | File Templates.
 */
public class TestBetamonMoveValidatorStrategyImpl {
    private BetamonMoveValidatorStrategyImpl moveValidatorStrategy;
    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        moveValidatorStrategy = new BetamonMoveValidatorStrategyImpl(board);
    }

    @Test
    public void should_not_be_allowed_to_move_in_wrong_direction() {
        assertEquals(0, moveValidatorStrategy.isValidMove(Location.R12, Location.R10, new int[]{1, 2}));
    }

    @Test
    public void should_allow_to_move_in_correct_direction() {
        assertEquals(2, moveValidatorStrategy.isValidMove(Location.R12, Location.B11, new int[]{1, 2}));
    }

    @Test
    public void should_not_allow_to_move_invalid_distance() {
        assertEquals(0, moveValidatorStrategy.isValidMove(Location.R12, Location.B10, new int[]{1, 2}));
    }

    @Test
    public void should_not_be_allowed_to_move_to_blocked_position() {
        assertEquals(0, moveValidatorStrategy.isValidMove(Location.R12, Location.B12, new int[]{1, 2}));
    }

    @Test
    public void should_allow_to_move_to_location_with_1_opponent() {
        board.decrementLocation(Location.B12);
        board.decrementLocation(Location.B12);
        board.decrementLocation(Location.B12);
        board.decrementLocation(Location.B12);
        assertEquals(1, moveValidatorStrategy.isValidMove(Location.R12, Location.B12, new int[]{1, 2}));
    }

    //should_only_be_allowed_to_move_from_bar_if_a_checker_is_there

    //should_only_allow_to_move_to_bearoff_if_all_checker_is_in_own_inner_table

    //should_allow_to_move_checkers_to_bearoff_if_distance_is_less_than_dice_value_except_yada_yada


}
