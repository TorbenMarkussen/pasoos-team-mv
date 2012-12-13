package pasoos.view;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.GameObserver;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gamestatemachine.GameEventDecorator;
import pasoos.hotgammon.gamestatemachine.GammonStateMachine;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static pasoos.hotgammon.Location.B1;
import static pasoos.hotgammon.Location.B2;

public class GameEventDecoratorTest {

    @Test
    public void should_invoke_blackplayeractive_at_newgame() {
        Game game = mock(Game.class);
        GammonStateMachine state = mock(GammonStateMachine.class);

        when(game.getPlayerInTurn()).thenReturn(Color.BLACK);
        when(game.getNumberOfMovesLeft()).thenReturn(2);
        when(game.diceThrown()).thenReturn(new int[]{4, 2});

        GameEventDecorator ged = new GameEventDecorator(game, state);

        ged.newGame();

        verify(game).newGame();

    }

    @Test
    public void should_invoke_dicerolled_and_blackplayer_events_after_nextTurn() {
        Game game = mock(Game.class);
        GammonStateMachine state = mock(GammonStateMachine.class);

        when(game.getPlayerInTurn()).thenReturn(Color.NONE).thenReturn(Color.BLACK);
        when(game.getNumberOfMovesLeft()).thenReturn(2);
        when(game.diceThrown()).thenReturn(new int[]{4, 2});
        GameEventDecorator ged = new GameEventDecorator(game, state);

        ged.nextTurn();

        verify(game).nextTurn();
        InOrder inorder = inOrder(state);
        inorder.verify(state).blackPlayerActive();
        inorder.verify(state).diceRolled(new int[]{4, 2});
    }

    @Test
    public void should_notify_move_checked() {
        Game game = mock(Game.class);
        GammonStateMachine state = mock(GammonStateMachine.class);
        final ArgumentCaptor<GameObserver> gameobs = ArgumentCaptor.forClass(GameObserver.class);

        when(game.getPlayerInTurn()).thenReturn(Color.BLACK);
        when(game.getNumberOfMovesLeft()).thenReturn(0);
        when(game.winner()).thenReturn(Color.NONE);

        GameEventDecorator ged = new GameEventDecorator(game, state);
        verify(game).addObserver(gameobs.capture());

        when(game.move(any(Location.class), any(Location.class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                gameobs.getValue().checkerMove(B1, B2);
                return null;
            }
        });

        ged.move(B1, B2);

        InOrder inOrder = inOrder(state);
        inOrder.verify(state).checkerMoved(B1, B2);
        inOrder.verify(state).turnEnded();
        inOrder.verify(state).redPlayerActive();
    }

    @Test
    public void should_invoke_winnerfound_event_after_move() {
        Game game = mock(Game.class);
        GammonStateMachine state = mock(GammonStateMachine.class);
        final ArgumentCaptor<GameObserver> gameobs = ArgumentCaptor.forClass(GameObserver.class);

        when(game.getPlayerInTurn()).thenReturn(Color.BLACK);
        when(game.getNumberOfMovesLeft()).thenReturn(0);
        when(game.winner()).thenReturn(Color.BLACK);

        GameEventDecorator ged = new GameEventDecorator(game, state);
        verify(game).addObserver(gameobs.capture());

        when(game.move(any(Location.class), any(Location.class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                gameobs.getValue().checkerMove(B1, B2);
                return null;
            }
        });

        ged.move(B1, B2);

        InOrder inOrder = inOrder(state);
        inOrder.verify(state).checkerMoved(B1, B2);
        inOrder.verify(state).winnerFound();
    }

}