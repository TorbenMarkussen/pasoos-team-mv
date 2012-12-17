package pasoos.hotgammon.rules.winner;


import org.junit.Test;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.gameengine.GameState;
import pasoos.hotgammon.rules.WinnerStrategy;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pasoos.hotgammon.Color.BLACK;

public class BackgammonWinnerStrategyTest {

    @Test
    public void should_have_none_as_winner() {
        WinnerStrategy winnerStrategy = new BackgammonWinnerStrategy();
        GameState state = mock(GameState.class);

        assertEquals(Color.NONE, winnerStrategy.determineWinner(state));
    }

    @Test
    public void should_have_black_as_winner() {
        WinnerStrategy winnerStrategy = new BackgammonWinnerStrategy();
        GameState state = mock(GameState.class);

        when(state.allCheckersOnBearOff(BLACK)).thenReturn(true);

        assertEquals(Color.BLACK, winnerStrategy.determineWinner(state));
    }

    @Test
    public void should_have_red_as_winner() {
        WinnerStrategy winnerStrategy = new BackgammonWinnerStrategy();
        GameState state = mock(GameState.class);

        when(state.allCheckersOnBearOff(Color.RED)).thenReturn(true);

        assertEquals(Color.RED, winnerStrategy.determineWinner(state));
    }
}
