package pasoos.hotgammon.rules.winner.gammamon;

import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.rules.WinnerStrategy;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: tor
 * Date: 02-09-12
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 */
public class GammamonWinnerStrategyImplTest {
    private Board board;
    private WinnerStrategy winner;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        winner = new GammamonWinnerStrategyImpl(board);
    }

    @Test
    public void check_winner_is_undetermined_as_long_checkers_of_both_colors_are_on_the_board() {
        assertEquals(Color.NONE, winner.determineWinner(47));
    }

    @Test
    public void the_winner_is_BLACK_if_BLACK_has_beared_off() {
        board.clear();
        board.set(Location.R_BAR, Color.RED, 1);
        board.set(Location.B_BEAR_OFF, Color.BLACK, 15);
        assertEquals(Color.BLACK, winner.determineWinner(247));
    }

    @Test
    public void the_winner_is_RED_if_RED_has_beared_off() {
        board.clear();
        board.set(Location.B_BAR, Color.BLACK, 5);
        board.set(Location.R_BEAR_OFF, Color.RED, 15);
        assertEquals(Color.RED, winner.determineWinner(647));
    }
}
