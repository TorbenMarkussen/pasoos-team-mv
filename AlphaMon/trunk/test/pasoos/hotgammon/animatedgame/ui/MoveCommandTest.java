package pasoos.hotgammon.animatedgame.ui;

import minidraw.boardgame.Command;
import org.junit.Test;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.animatedgame.GammonStateMachine;

import static org.mockito.Mockito.*;

public class MoveCommandTest {

    @Test
    public void should_invoke_move_request_on_game_state() {
        GammonStateMachine state = mock(GammonStateMachine.class);
        Command moveCommand = new MoveCommand(state);
        moveCommand.setFromCoordinates(501, 21);
        moveCommand.setToCoordinates(461, 21);
        moveCommand.execute();

        verify(state, times(1)).moveRequest(Location.B1, Location.B2);
    }
}
