package pasoos.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.rules.factory.SemiMonFactory;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GammonBuildDirectorTest {

    @Mock
    private GameSettings settings;
    @Mock
    private GammonBuilder gammonBuilder;
    @Mock
    private Game game;

    @Before
    public void setUp() throws Exception {
        doReturn(SemiMonFactory.class).when(settings).getGameFactoryType();
        when(settings.getPlayerType(any(Color.class))).thenReturn(PlayerType.ManualPlayer);
        when(settings.getName(Color.RED)).thenReturn("red player");
        when(settings.getName(Color.BLACK)).thenReturn("black player");

        when(gammonBuilder.getGame()).thenReturn(game);
        when(game.getColor(any(Location.class))).thenReturn(Color.NONE);
        when(game.getCount(any(Location.class))).thenReturn(0);
    }

    @Test
    public void should_set_gametype_from_gamesettings() {

        GammonBuildDirector buildDirector = new GammonBuildDirector(settings, gammonBuilder);
        buildDirector.construct();

        verify(gammonBuilder, times(1)).setGameType(SemiMonFactory.class);
        verify(gammonBuilder, times(1)).setPlayer(Color.RED, PlayerType.ManualPlayer, "red player");
        verify(gammonBuilder, times(1)).setPlayer(Color.BLACK, PlayerType.ManualPlayer, "black player");
        verify(gammonBuilder, times(0)).addPiece(any(Location.class), any(Color.class));
        verify(gammonBuilder, times(1)).addDie("die1");
        verify(gammonBuilder, times(1)).addDie("die1");
    }

    @Test
    public void should_add_3_pieces() {

        when(game.getColor(Location.B1)).thenReturn(Color.RED);
        when(game.getCount(Location.B1)).thenReturn(3);

        GammonBuildDirector buildDirector = new GammonBuildDirector(settings, gammonBuilder);
        buildDirector.construct();

        verify(gammonBuilder, times(1)).setGameType(SemiMonFactory.class);
        verify(gammonBuilder, times(1)).setPlayer(Color.RED, PlayerType.ManualPlayer, "red player");
        verify(gammonBuilder, times(1)).setPlayer(Color.BLACK, PlayerType.ManualPlayer, "black player");
        verify(gammonBuilder, times(3)).addPiece(Location.B1, Color.RED);
        verify(gammonBuilder, times(1)).addDie("die1");
        verify(gammonBuilder, times(1)).addDie("die1");
    }
}
