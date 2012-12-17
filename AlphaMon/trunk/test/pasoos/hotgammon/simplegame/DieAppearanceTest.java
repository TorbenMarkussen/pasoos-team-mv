package pasoos.hotgammon.simplegame;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DieAppearanceTest {
    @Mock
    private Game game;

    @Test
    public void empty_string_for_empty_name() {
        when(game.getPlayerInTurn()).thenReturn(Color.BLACK);
        DieAppearance dieAppearance = new DieAppearance(game);
        assertEquals("", dieAppearance.calculateImageNameForPropWithKey(""));
    }

    @Test
    public void empty_string_for_null() {
        when(game.getPlayerInTurn()).thenReturn(Color.BLACK);
        DieAppearance dieAppearance = new DieAppearance(game);
        assertEquals("", dieAppearance.calculateImageNameForPropWithKey(null));
    }

    @Test
    public void should_return_bdie4_for_die1() {
        when(game.getPlayerInTurn()).thenReturn(Color.BLACK);
        when(game.diceThrown()).thenReturn(new int[]{4, 2});
        DieAppearance dieAppearance = new DieAppearance(game);
        assertEquals("bdie4", dieAppearance.calculateImageNameForPropWithKey("die1"));
    }

    @Test
    public void should_return_bdie2_for_die2() {
        when(game.getPlayerInTurn()).thenReturn(Color.BLACK);
        when(game.diceThrown()).thenReturn(new int[]{4, 2});
        DieAppearance dieAppearance = new DieAppearance(game);
        assertEquals("bdie2", dieAppearance.calculateImageNameForPropWithKey("die2"));
    }

    @Test
    public void should_return_rdie1_for_die2() {
        when(game.getPlayerInTurn()).thenReturn(Color.RED);
        when(game.diceThrown()).thenReturn(new int[]{3, 1});
        DieAppearance dieAppearance = new DieAppearance(game);
        assertEquals("rdie1", dieAppearance.calculateImageNameForPropWithKey("die2"));
    }

    @Test
    public void should_return_rdie3_for_die1() {
        when(game.getPlayerInTurn()).thenReturn(Color.RED);
        when(game.diceThrown()).thenReturn(new int[]{3, 1});
        DieAppearance dieAppearance = new DieAppearance(game);
        assertEquals("rdie3", dieAppearance.calculateImageNameForPropWithKey("die1"));
    }
}
