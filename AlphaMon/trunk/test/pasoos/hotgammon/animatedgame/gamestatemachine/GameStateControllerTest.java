package pasoos.hotgammon.animatedgame.gamestatemachine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import pasoos.hotgammon.Game;

import static org.mockito.Mockito.*;
import static pasoos.hotgammon.Location.B1;
import static pasoos.hotgammon.Location.B2;

public class GameStateControllerTest {
    private Game game;
    private GameStateController gameStateController;
    private StateContext context;
    private GammonState gammonState;

    @Before
    public void setUp() throws Exception {
        context = mock(StateContext.class);
        game = mock(Game.class);
        gammonState = mock(GammonState.class);

        configureDefaultContextBehaviour();

        gameStateController = new GameStateController();
        gameStateController.setContext(context);

    }

    private void configureDefaultContextBehaviour() {
        when(context.getGame()).thenReturn(game);
        when(context.getCurrentState()).thenReturn(gammonState);
    }

    @Test
    public void should_forward_events_to_current_state() {
        gameStateController.rackGame();
        gameStateController.blackPlayerActive();
        gameStateController.redPlayerActive();
        gameStateController.checkerMoved(B1, B2);
        gameStateController.moveRequest(B1, B2);
        gameStateController.rollDiceRequest();
        gameStateController.turnEnded();
        gameStateController.winnerFound();

        InOrder inOrder = inOrder(gammonState, context);
        inOrder.verify(gammonState).rackGame();
        inOrder.verify(gammonState).blackPlayerActive();
        inOrder.verify(gammonState).redPlayerActive();
        inOrder.verify(gammonState).checkerMoved(B1, B2);
        inOrder.verify(gammonState).moveRequest(B1, B2);
        inOrder.verify(gammonState).rollDiceRequest();
        inOrder.verify(gammonState).turnEnded();

        inOrder.verify(context).setState(StateId.Winner);
    }


}