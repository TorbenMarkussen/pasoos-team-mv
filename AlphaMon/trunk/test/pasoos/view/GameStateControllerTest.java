package pasoos.view;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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

        configurePlayerMock(redplayer);
        configurePlayerMock(blackplayer);
    }

    private void configurePlayerMock(GammonPlayer player) {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                gameStateController.setState(StateId.BlackPlayer);
                return null;
            }
        }).when(player).blackPlayerActive();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                gameStateController.setState(StateId.RedPlayer);
                return null;
            }
        }).when(player).redPlayerActive();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                gameStateController.setState(StateId.Winner);
                return null;
            }
        }).when(player).winnerFound();


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
        gameStateController.blackPlayerActive();
        gameStateController.diceRolled(new int[]{4, 2});

        gameStateController.moveRequest(B1, B2);
        gameStateController.checkerMoved(B1, B2);

        gameStateController.moveRequest(B4, B5);
        gameStateController.checkerMoved(B4, B5);

        gameStateController.turnEnded();

        gameStateController.redPlayerActive();

        gameStateController.rollDiceRequest();
        gameStateController.diceRolled(new int[]{4, 2});

        gameStateController.moveRequest(R6, R7);
        gameStateController.checkerMoved(R6, R7);

        gameStateController.moveRequest(R8, R9);
        gameStateController.checkerMoved(R8, R9);

        InOrder inOrder = inOrder(blackplayer, redplayer);
        inOrder.verify(blackplayer, times(1)).onEntry();
        inOrder.verify(blackplayer, times(1)).diceRolled(new int[]{4, 2});
        inOrder.verify(blackplayer, times(1)).moveRequest(B1, B2);
        inOrder.verify(blackplayer, times(1)).checkerMoved(B1, B2);
        inOrder.verify(blackplayer, times(1)).moveRequest(B4, B5);
        inOrder.verify(blackplayer, times(1)).checkerMoved(B4, B5);

        inOrder.verify(blackplayer, times(1)).turnEnded();

        inOrder.verify(blackplayer, times(1)).onExit();
        inOrder.verify(redplayer, times(1)).onEntry();

        inOrder.verify(redplayer, times(1)).rollDiceRequest();
        inOrder.verify(redplayer, times(1)).diceRolled(new int[]{4, 2});

        inOrder.verify(redplayer, times(1)).moveRequest(R6, R7);
        inOrder.verify(redplayer, times(1)).checkerMoved(R6, R7);

        inOrder.verify(redplayer, times(1)).moveRequest(R8, R9);
        inOrder.verify(redplayer, times(1)).checkerMoved(R8, R9);

    }

}