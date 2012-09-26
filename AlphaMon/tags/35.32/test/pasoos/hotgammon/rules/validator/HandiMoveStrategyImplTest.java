package pasoos.hotgammon.rules.validator;

import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gameengine.GameState;
import pasoos.hotgammon.rules.MoveValidatorStrategy;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static pasoos.hotgammon.Color.*;
import static pasoos.hotgammon.Location.B1;
import static pasoos.hotgammon.Location.R12;

public class HandiMoveStrategyImplTest {
    private GameState stubbedGameState;
    private HandiMoveStrategy moveStrategy;
    private MoveValidatorStrategy stubbedBlackMoveStrategy;
    private MoveValidatorStrategy stubbedRedMoveStrategy;

    @Before
    public void setUp() {
        stubbedGameState = mock(GameState.class);
        stubbedBlackMoveStrategy = mock(MoveValidatorStrategy.class);
        stubbedRedMoveStrategy = mock(MoveValidatorStrategy.class);
        moveStrategy = new HandiMoveStrategy(stubbedGameState, stubbedBlackMoveStrategy, stubbedRedMoveStrategy);
    }

    @Test
    public void should_be_BLACK_move_strategy_if_BLACK_is_in_turn() {
        when(stubbedGameState.getPlayerInTurn()).thenReturn(BLACK);
        when(stubbedBlackMoveStrategy.isValidMove(any(Location.class), any(Location.class), anyInt())).thenReturn(true);
        assertTrue(moveStrategy.isValidMove(R12, B1, 6));
        verify(stubbedBlackMoveStrategy, times(1)).isValidMove(R12, B1, 6);
    }

    @Test
    public void should_be_RED_move_strategy_if_RED_is_in_turn() {
        when(stubbedGameState.getPlayerInTurn()).thenReturn(RED);
        when(stubbedRedMoveStrategy.isValidMove(any(Location.class), any(Location.class), anyInt())).thenReturn(false);
        assertFalse(moveStrategy.isValidMove(R12, B1, 6));
        verify(stubbedRedMoveStrategy, times(1)).isValidMove(R12, B1, 6);
    }

    @Test
    public void should_return_false_if_no_player_in_turn() {
        when(stubbedGameState.getPlayerInTurn()).thenReturn(NONE);
        assertFalse(moveStrategy.isValidMove(R12, B1, 6));
    }
}
