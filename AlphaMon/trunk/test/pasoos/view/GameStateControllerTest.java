package pasoos.view;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InOrder;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;

import static org.mockito.Mockito.*;
import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;
import static pasoos.hotgammon.Location.*;

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

        gameStateController = new GameStateController();
        gameStateController.
                setGame(game).
                setBlackPlayer(blackplayer).
                setRedPlayer(redplayer);
    }

    @Ignore
    @Test
    public void should_set_black_player_active_if_black_is_playerInTurn() {
        when(game.getPlayerInTurn()).thenReturn(BLACK);
        gameStateController.turnEnded();

        InOrder inOrder = inOrder(redplayer, blackplayer);
        inOrder.verify(blackplayer).setActive();
        inOrder.verify(redplayer).setInactive();
    }

    @Ignore
    @Test
    public void should_set_red_player_active_when_redplayer_is_playerInTurn() {
        when(game.getPlayerInTurn()).thenReturn(RED);
        gameStateController.turnEnded();

        InOrder inOrder = inOrder(redplayer, blackplayer);
        inOrder.verify(redplayer).setInactive();
        inOrder.verify(blackplayer).setActive();
    }

    @Test
    public void should_call_nextTurn_on_rollDiceRequest() {
        gameStateController.rollDiceRequest();
        verify(game).nextTurn();
    }

    @Test
    public void should_not_call_nextTurn_on_rollDiceRequest_if_turnNotEnded() {
        gameStateController.rollDiceRequest();
        gameStateController.diceRolled(new int[]{4, 2});
        gameStateController.blackPlayerActive();
        gameStateController.rollDiceRequest();

        verify(game, times(1)).nextTurn();
    }

    @Test
    public void should_ignore_moveRequest_until_diceRolled() {
        gameStateController.moveRequest(B1, B2);
        verify(game, times(0)).move(any(Location.class), any(Location.class));
    }

    @Test
    public void default_play_scenario() {
        gameStateController.rollDiceRequest();
        gameStateController.diceRolled(new int[]{4, 2});
        verify(game, times(1)).nextTurn();

        gameStateController.blackPlayerActive();
        verify(game, times(1)).nextTurn();

        gameStateController.moveRequest(B1, B2);
        verify(game).move(B1, B2);
        gameStateController.checkerMoved(B1, B2);

        gameStateController.moveRequest(B4, B5);
        verify(game).move(B4, B5);
        gameStateController.checkerMoved(B4, B5);

        gameStateController.turnEnded();


        gameStateController.redPlayerActive();
        gameStateController.rollDiceRequest();
        gameStateController.moveRequest(B1, B2);
        gameStateController.moveRequest(B4, B5);
        gameStateController.turnEnded();

        gameStateController.winnerFound();

        verify(game, times(2)).nextTurn();
    }

}