package pasoos.hotgammon.rules.winner;


import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.gameengine.GameState;
import pasoos.hotgammon.rules.WinnerStrategy;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleWinnerStrategyTest {

    WinnerStrategy winnerStrategy;
    GameState state;

    @Before
    public void setup() {
        winnerStrategy = new SimpleWinnerStrategy();
        state = mock(GameState.class);
    }

    @Test
    public void should_have_none_as_winner() {
        when(state.getTurnCount()).thenReturn(5);
        assertEquals(Color.NONE, winnerStrategy.determineWinner(state));
    }

    @Test
    public void should_have_red_as_winner_after_6_turns() {
        when(state.getTurnCount()).thenReturn(6);

        assertEquals(Color.RED, winnerStrategy.determineWinner(state));
    }
}
