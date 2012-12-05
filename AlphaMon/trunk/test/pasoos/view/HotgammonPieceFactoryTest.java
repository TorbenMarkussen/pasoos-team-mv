package pasoos.view;

import minidraw.boardgame.BoardPiece;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gamestatemachine.GammonPlayer;
import pasoos.hotgammon.ui.GammonDice;
import pasoos.hotgammon.ui.HotgammonPieceFactory;

import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pasoos.hotgammon.Color.NONE;

public class HotgammonPieceFactoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_return_empty_map_for_empty_game_board() {
        Game g = mock(Game.class);
        GammonPlayer redPlayer = mock(GammonPlayer.class);
        GammonPlayer blackPlayer = mock(GammonPlayer.class);
        GammonDice dice = mock(GammonDice.class);
        HotgammonPieceFactory pf = new HotgammonPieceFactory();
        when(g.getCount(any(Location.class))).thenReturn(0);
        when(g.getColor(any(Location.class))).thenReturn(NONE);

        Map<Location, List<BoardPiece>> m = pf.generatePieceMultiMap();

        for (Location loc : Location.values()) {
            assertTrue(m.containsKey(loc));
            assertNotNull(m.get(loc));

        }
    }

}
