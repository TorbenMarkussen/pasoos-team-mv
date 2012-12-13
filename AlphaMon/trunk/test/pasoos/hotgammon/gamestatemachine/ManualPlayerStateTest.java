package pasoos.hotgammon.gamestatemachine;

import minidraw.animatedboard.MoveAnimationCallbacks;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.sounds.SoundResource;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ManualPlayerStateTest {

    @Mock
    StateContext context;
    @Mock
    Game game;
    @Mock
    SoundResource soundResource;
    private GammonPlayer manualPlayer;
    private ArgumentCaptor<MoveAnimationCallbacks> macCaptor;

    @Before
    public void setup() {
        manualPlayer = new ManualPlayerState(StateId.BlackPlayer, "test player");
        manualPlayer.setContext(context);
        when(context.getGame()).thenReturn(game);
        when(context.getSoundMachine()).thenReturn(soundResource);
        macCaptor = ArgumentCaptor.forClass(MoveAnimationCallbacks.class);

        configureGameMock();
    }

    private void configureGameMock() {
        when(game.move(Location.B1, Location.B5)).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                manualPlayer.checkerMoved((Location) args[0], (Location) args[1]);
                return true;
            }
        });

        when(game.move(Location.B2, Location.B4)).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                manualPlayer.checkerMoved((Location) args[1], Location.R_BAR);
                manualPlayer.checkerMoved((Location) args[0], (Location) args[1]);
                return true;
            }
        });
    }

    @Test
    public void should_return_blackplayer_as_stateId() {
        assertEquals(StateId.BlackPlayer, manualPlayer.getStateId());
    }

    @Test
    public void should_return_name() {
        assertEquals("test player", manualPlayer.getName());
    }

    @Test
    public void default_play_sequence_with_bar_move() {
        assertEquals(false, manualPlayer.moveRequest(Location.B4, Location.B2));
        verify(context, times(0)).getGame();

        when(game.getNumberOfMovesLeft()).thenReturn(0);
        manualPlayer.onEntry();

        manualPlayer.rollDiceRequest();
        manualPlayer.rollDiceRequest();

        manualPlayer.diceRolled(new int[]{4, 2});

        when(game.getNumberOfMovesLeft()).thenReturn(2);
        manualPlayer.moveRequest(Location.B1, Location.B5);

        when(game.getNumberOfMovesLeft()).thenReturn(1);
        manualPlayer.moveRequest(Location.B2, Location.B4);
        verify(context).moveAnimated(any(Location.class), any(Location.class), macCaptor.capture());
        manualPlayer.turnEnded();
        macCaptor.getValue().beforeMoveStart(Location.B4, Location.R_BAR);
        macCaptor.getValue().afterMoveStart(Location.B4, Location.R_BAR);
        macCaptor.getValue().beforeMoveEnd(Location.B4, Location.R_BAR);
        macCaptor.getValue().afterMoveEnd(Location.B4, Location.R_BAR);


        InOrder inOrder = inOrder(context, game);
        inOrder.verify(context).rollDice();
        inOrder.verify(context).notifyDiceRolled(new int[]{4, 2});
        inOrder.verify(game).move(Location.B1, Location.B5);
        inOrder.verify(context).notifyPieceMovedEvent(Location.B1, Location.B5);
        inOrder.verify(game).move(Location.B2, Location.B4);
        inOrder.verify(context).notifyPieceMovedEvent(Location.B2, Location.B4);
        inOrder.verify(context).playerTurnEnded(manualPlayer);
    }

}
