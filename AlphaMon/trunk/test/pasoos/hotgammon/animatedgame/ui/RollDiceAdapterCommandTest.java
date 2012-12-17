package pasoos.hotgammon.animatedgame.ui;

import minidraw.boardgame.Command;
import org.junit.Test;
import pasoos.hotgammon.animatedgame.GammonStateMachine;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RollDiceAdapterCommandTest {

    @Test
    public void should_invoke_roll_dice() {
        GammonStateMachine state = mock(GammonStateMachine.class);
        Command command = new RollDiceAdapterCommand(state);
        command.execute();
        verify(state, times(1)).rollDiceRequest();
    }
}
