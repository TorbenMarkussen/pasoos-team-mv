package pasoos.hotgammon.gameengine.validator;

import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.Location;
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
        assertEquals(0, moveValidatorStrategy.isValidMove(Location.B6, Location.B1, new int[]{5, 6}));
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

    @Test
    public void should_only_be_allowed_to_move_from_bar_if_a_checker_is_there(){
        board.IncrementBar(Color.BLACK);
        assertEquals(0, moveValidatorStrategy.isValidMove(Location.R12, Location.B11, new int[]{1, 2}));
    }

    @Test
    public void should_move_from_bar_to_opponent_inner_table(){
        board.IncrementBar(Color.RED);
        board.incrementLocation(Location.B3, Color.BLACK);
        board.incrementLocation(Location.B3, Color.BLACK);
        assertEquals(0, moveValidatorStrategy.isValidMove(Location.R_BAR, Location.B3, new int[]{3, 4}));
        assertEquals(4, moveValidatorStrategy.isValidMove(Location.R_BAR, Location.B4, new int[]{3,4}));
    }


   // @Test
    public void should_only_allow_to_move_to_bearoff_if_all_checker_is_in_own_inner_table(){
        board.clear();
        board.incrementLocation(Location.R5, Color.RED);
        board.incrementLocation(Location.R5, Color.RED);

        board.incrementLocation(Location.R3, Color.RED);
        board.incrementLocation(Location.R3, Color.RED);
        board.incrementLocation(Location.R3, Color.RED);
        board.incrementLocation(Location.R3, Color.RED);
        board.incrementLocation(Location.R_BEAR_OFF, Color.RED);

        board.incrementLocation(Location.R8, Color.RED);

        board.incrementLocation(Location.R10, Color.BLACK);
        board.incrementLocation(Location.R4, Color.BLACK);
        board.incrementLocation(Location.B4, Color.BLACK);
        assertEquals("Invalid move due to checker on R8", 0, moveValidatorStrategy.isValidMove(Location.R5, Location.R_BEAR_OFF, new int[]{5, 6}));
        board.decrementLocation(Location.R8);
        assertEquals("Valid move due to all checkers in inner table", 5, moveValidatorStrategy.isValidMove(Location.R5, Location.R_BEAR_OFF, new int[]{5, 6}));
        //assertEquals("Invalid move due to checker on R10 & R4", 0, moveValidatorStrategy.isValidMove(Location.B4, Location.B_BEAR_OFF, new int[]{3, 4}));
        //board.decrementLocation(Location.R10);
        //board.decrementLocation(Location.R4);
        //assertEquals("Valid move due to all checkers in inner table", 4, moveValidatorStrategy.isValidMove(Location.B4, Location.B_BEAR_OFF, new int[]{3, 4}));

    }

    @Test
    public void should_allow_to_move_checkers_to_bearoff_if_distance_is_less_than_dice_value(){
        board.clear();

        board.incrementLocation(Location.R3, Color.RED);
        board.incrementLocation(Location.R3, Color.RED);
        board.incrementLocation(Location.R3, Color.RED);
        board.incrementLocation(Location.R3, Color.RED);
        board.incrementLocation(Location.R_BEAR_OFF, Color.RED);

        board.incrementLocation(Location.R10, Color.BLACK);
        board.incrementLocation(Location.R4, Color.BLACK);
        board.incrementLocation(Location.B4, Color.BLACK);

        assertEquals("dice values exceeds the needed distance", 6, moveValidatorStrategy.isValidMove(Location.R3, Location.R_BEAR_OFF, new int[]{6,4}));

    }

    @Test
    public void should_not_allow_to_move_checkers_to_bearoff_if_distance_is_less_than_dice_value_and_checkers_on_higher_distance(){
        board.clear();
        board.incrementLocation(Location.R5, Color.RED);
        board.incrementLocation(Location.R5, Color.RED);

        board.incrementLocation(Location.R3, Color.RED);
        board.incrementLocation(Location.R3, Color.RED);
        board.incrementLocation(Location.R3, Color.RED);
        board.incrementLocation(Location.R3, Color.RED);
        board.incrementLocation(Location.R_BEAR_OFF, Color.RED);

        board.incrementLocation(Location.R10, Color.BLACK);
        board.incrementLocation(Location.R4, Color.BLACK);
        board.incrementLocation(Location.B4, Color.BLACK);

        //assertEquals("Invalid move due to checker on R5", 6, moveValidatorStrategy.isValidMove(Location.R3, Location.R_BEAR_OFF, new int[]{6,4}));

    }





    //should_allow_to_move_checkers_to_bearoff_if_distance_is_less_than_dice_value_except_yada_yada


}
