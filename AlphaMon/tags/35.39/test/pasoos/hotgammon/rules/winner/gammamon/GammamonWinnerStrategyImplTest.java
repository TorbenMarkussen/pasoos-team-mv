package pasoos.hotgammon.rules.winner.gammamon;

import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gameengine.BoardImpl;
import pasoos.hotgammon.gameengine.GameState;
import pasoos.hotgammon.rules.WinnerStrategy;
import pasoos.hotgammon.rules.winner.BackgammonWinnerStrategy;

import static junit.framework.Assert.assertEquals;

public class GammamonWinnerStrategyImplTest {
    private BoardImpl board;
    private WinnerStrategy winner;

    @Before
    public void setUp() throws Exception {
        board = new BoardImpl();
        winner = new BackgammonWinnerStrategy();
    }

    @Test
    public void check_winner_is_undetermined_as_long_checkers_of_both_colors_are_on_the_board() {
        assertEquals(Color.NONE, winner.determineWinner(getGameState(47)));
    }

    @Test
    public void the_winner_is_BLACK_if_BLACK_has_beared_off() {
        board.clear();
        board.set(Location.R_BAR, Color.RED, 1);
        board.set(Location.B_BEAR_OFF, Color.BLACK, 15);
        assertEquals(Color.BLACK, winner.determineWinner(getGameState(247)));
    }

    private GameState getGameState(final int turnCount) {

        return new GameState() {
            @Override
            public int getTurnCount() {
                return turnCount;
            }

            @Override
            public boolean allCheckersOnBearOff(Color color) {
                return board.hasAllCheckersOnBearOff(color);
            }

            @Override
            public Color getPlayerInTurn() {
                return Color.NONE;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
    }

    @Test
    public void the_winner_is_RED_if_RED_has_beared_off() {
        board.clear();
        board.set(Location.B_BAR, Color.BLACK, 5);
        board.set(Location.R_BEAR_OFF, Color.RED, 15);
        assertEquals(Color.RED, winner.determineWinner(getGameState(647)));
    }
}
