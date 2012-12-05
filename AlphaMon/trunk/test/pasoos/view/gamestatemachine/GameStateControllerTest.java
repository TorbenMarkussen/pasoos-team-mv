package pasoos.view.gamestatemachine;

import minidraw.framework.Animation;
import minidraw.framework.AnimationChangeEvent;
import minidraw.framework.AnimationChangeListener;
import minidraw.framework.AnimationEngine;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gamestatemachine.GameStateController;
import pasoos.hotgammon.gamestatemachine.GammonPlayer;
import pasoos.hotgammon.gamestatemachine.StateId;

import static org.mockito.Mockito.*;
import static pasoos.hotgammon.Location.*;

public class GameStateControllerTest {
    private Game game;
    private GammonPlayer redplayer;
    private GammonPlayer blackplayer;
    private GameStateController gameStateController;
    private AnimationEngine aengine;

    @Before
    public void setUp() throws Exception {
        game = mock(Game.class);
        redplayer = mock(GammonPlayer.class);
        blackplayer = mock(GammonPlayer.class);
        aengine = mock(AnimationEngine.class);

        gameStateController = new GameStateController();

    }

    private void configurePlayerMock(GammonPlayer player, StateId sid) {
//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                gameStateController.setState(StateId.BlackPlayer);
//                return null;
//            }
//        }).when(player).blackPlayerActive();
//
//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                gameStateController.setState(StateId.RedPlayer);
//                return null;
//            }
//        }).when(player).redPlayerActive();
//
//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                gameStateController.setState(StateId.Winner);
//                return null;
//            }
//        }).when(player).winnerFound();
//
//        when(player.getStateId()).thenReturn(sid);
    }

    @Ignore
    @Test
    public void should_ignore_moveRequest_until_diceRolled() {
        gameStateController.moveRequest(B1, B2);
        verify(game, times(0)).move(any(Location.class), any(Location.class));
    }

    @Ignore
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

    @Ignore
    @Test
    public void should_lock_checker_move_event_if_animation_active() {
        Animation a = mock(Animation.class);
        ArgumentCaptor<AnimationChangeListener> aclCaptor = ArgumentCaptor.forClass(AnimationChangeListener.class);
        gameStateController.blackPlayerActive();
        gameStateController.diceRolled(new int[]{4, 2});
        //gameStateController.startAnimation(a, B1, B2);
        verify(a).addAnimationChangeListener(aclCaptor.capture());

        gameStateController.checkerMoved(B1, B2);
        aclCaptor.getValue().onAnimationCompleted(new AnimationChangeEvent(a));

    }
}