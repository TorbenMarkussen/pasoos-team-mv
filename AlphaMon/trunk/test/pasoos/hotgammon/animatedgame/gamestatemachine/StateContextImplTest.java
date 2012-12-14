package pasoos.hotgammon.animatedgame.gamestatemachine;

import minidraw.animatedboard.AnimatedBoard;
import minidraw.animatedboard.MoveAnimationCallbacks;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.animatedgame.ui.GammonDice;
import pasoos.hotgammon.animatedgame.ui.status.StatusObserver;
import pasoos.hotgammon.sounds.SoundResource;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StateContextImplTest {

    private StateContext context;
    @Mock
    GammonState initialState;
    @Mock
    GammonState winnerState;
    @Mock
    SoundResource sounds;
    @Mock
    GammonDice dice;
    @Mock
    AnimatedBoard<Location> animatedBoard;
    @Mock
    Game game;
    @Mock
    GammonPlayer blackPlayer;
    @Mock
    GammonPlayer redPlayer;
    @Mock
    GammonState mainState;

    @Before
    public void setUp() throws Exception {
        when(redPlayer.getName()).thenReturn("Red");
        when(redPlayer.getStateId()).thenReturn(StateId.RedPlayer);
        when(blackPlayer.getName()).thenReturn("Black");
        when(blackPlayer.getStateId()).thenReturn(StateId.BlackPlayer);
        when(initialState.getStateId()).thenReturn(StateId.Initial);
        when(winnerState.getStateId()).thenReturn(StateId.Winner);

        StateContextImpl.Builder builder = new StateContextImpl.Builder(redPlayer, blackPlayer, game, animatedBoard, dice, mainState);
        builder.setInitial(initialState);
        builder.setWinner(winnerState);
        builder.setSounds(sounds);

        context = builder.build();
    }


    @Test
    public void current_state_should_be_initial_state_after_construction() {
        GammonState currentState = context.getCurrentState();
        assertEquals(initialState, currentState);
        verify(initialState).onEntry();
    }

    @Test
    public void states_should_be_initialized_with_context_after_construction() {
        verify(mainState).setContext(context);
        verify(initialState).setContext(context);
        verify(winnerState).setContext(context);
        verify(redPlayer).setContext(context);
        verify(blackPlayer).setContext(context);
    }

    @Test
    public void should_return_players_name_for_winner() {
        when(game.winner()).thenReturn(Color.RED);
        assertEquals("Red", context.getWinnerName());

        when(game.winner()).thenReturn(Color.BLACK);
        assertEquals("Black", context.getWinnerName());

        when(game.winner()).thenReturn(Color.NONE);
        assertEquals("", context.getWinnerName());
    }

    @Test
    public void should_set_oppenent_as_state_when_player_turn_ends() {
        context.setState(StateId.BlackPlayer);

        InOrder inOrder = inOrder(initialState, blackPlayer, redPlayer);
        inOrder.verify(initialState).onExit();
        inOrder.verify(blackPlayer).onEntry();

        context.playerTurnEnded(blackPlayer);

        inOrder.verify(blackPlayer).onExit();
        inOrder.verify(redPlayer).onEntry();

        context.playerTurnEnded(redPlayer);

        inOrder.verify(redPlayer).onExit();
        inOrder.verify(blackPlayer).onEntry();
    }

    @Test
    public void should_notify_observers() {
        StatusObserver so = mock(StatusObserver.class);
        context.addStatusObserver(so);
        context.notifyDiceRolled(new int[]{4, 2});
        verify(so).diceRolled(new int[]{4, 2});

        context.notifyPieceMovedEvent(Location.B1, Location.B2);
        verify(so).checkerMove(Location.B1, Location.B2);

        context.updateStatus(initialState, "hello world");
        verify(so).updateStatus("hello world");
    }

    @Test
    public void should_not_notify_status_for_a_non_active_state() {
        StatusObserver so = mock(StatusObserver.class);
        context.addStatusObserver(so);

        context.updateStatus(winnerState, "should not be forwarded to observe");
        context.updateStatus(initialState, "hello world");
        verify(so, times(1)).updateStatus(anyString());
        verify(so).updateStatus("hello world");
    }

    @Test
    public void should_notify_move() {
        MoveAnimationCallbacks mac = mock(MoveAnimationCallbacks.class);
        context.moveAnimated(Location.B1, Location.B2, mac);
        verify(animatedBoard).moveAnimated(Location.B1, Location.B2, mac);
    }

    @Test
    public void should_notify_roll() {
        context.rollDice();
        verify(dice).roll();
    }

    @Test
    public void should_return_sound_machine() {
        assertEquals(sounds, context.getSoundMachine());
    }

}
