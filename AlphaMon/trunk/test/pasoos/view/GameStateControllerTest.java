package pasoos.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import pasoos.hotgammon.Game;

import static org.mockito.Mockito.*;
import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;

public class GameStateControllerTest {
    private Game game;
    private GammonPlayer redplayer;
    private GammonPlayer blackplayer;
    private GameStateController gameStateController;

    @Before
    public void setUp() throws Exception {
        game = mock(Game.class);
        redplayer = mock(GammonPlayer.class);
        blackplayer = mock(GammonPlayer.class);

        gameStateController = new GameStateController(game, redplayer, blackplayer);
    }

    @Test
    public void should_initially_set_black_player_active() {
        InOrder inOrder = inOrder(redplayer, blackplayer);
        inOrder.verify(redplayer).setInactive();
        inOrder.verify(blackplayer).setActive();
    }

    @Test
    public void should_set_black_player_active_if_black_is_playerInTurn() {
        InOrder inOrder = inOrder(redplayer, blackplayer);
        inOrder.verify(redplayer).setInactive();
        inOrder.verify(blackplayer).setActive();

        when(game.getPlayerInTurn()).thenReturn(BLACK);
        gameStateController.turnEnded();

        inOrder = inOrder(redplayer, blackplayer);
        inOrder.verify(redplayer).setInactive();
        inOrder.verify(blackplayer).setActive();
    }

    @Test
    public void should_set_red_player_active_when_redplayer_is_playerInTurn() {
        InOrder inOrder = inOrder(redplayer, blackplayer);
        inOrder.verify(redplayer).setInactive();
        inOrder.verify(blackplayer).setActive();

        when(game.getPlayerInTurn()).thenReturn(RED);

        gameStateController.turnEnded();
        inOrder = inOrder(redplayer, blackplayer);
        inOrder.verify(blackplayer).setActive();
        inOrder.verify(redplayer).setInactive();
    }

    @Test
    public void should_s_et_red_player_active_when_redplayer_is_playerInTurn() {
    }
}