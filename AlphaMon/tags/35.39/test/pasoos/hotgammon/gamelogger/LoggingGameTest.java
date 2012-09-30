package pasoos.hotgammon.gamelogger;

import org.junit.Test;
import org.mockito.Mockito;
import pasoos.hotgammon.rules.factory.AlphaMonFactory;
import pasoos.hotgammon.rules.factory.BetaMonFactory;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static pasoos.hotgammon.Location.*;

public class LoggingGameTest {

    @Test
    public void should_log_dice_rolled_messages() {
        GameLogDecorator loggingGame = new GameLogDecoratorImpl(new AlphaMonFactory());
        assertNotNull(loggingGame);

        LogObserver logObserver = mock(LogObserver.class);
        loggingGame.addLogObserver(logObserver);

        loggingGame.nextTurn();
        Mockito.verify(logObserver).log("Dice rolled: 2-1");

        loggingGame.nextTurn();
        Mockito.verify(logObserver).log("Dice rolled: 4-3");
    }

    @Test
    public void should_log_move_messages() {
        GameLogDecorator loggingGame = new GameLogDecoratorImpl(new AlphaMonFactory());

        LogObserver logObserver = mock(LogObserver.class);
        loggingGame.addLogObserver(logObserver);

        loggingGame.nextTurn();
        assertTrue(loggingGame.move(R12, B11));
        Mockito.verify(logObserver).log("BLACK moves (R12, B11)");

        loggingGame.nextTurn();
        assertTrue(loggingGame.move(R6, R4));
        Mockito.verify(logObserver).log("RED moves (R6, R4)");

    }

    @Test
    public void should_not_log_move_messages_if_move_is_invalid() {
        GameLogDecorator loggingGame = new GameLogDecoratorImpl(new BetaMonFactory());

        LogObserver logObserver = mock(LogObserver.class);
        loggingGame.addLogObserver(logObserver);

        loggingGame.nextTurn();
        Mockito.verify(logObserver).log("Dice rolled: 2-1");

        assertFalse(loggingGame.move(R12, B10));
        Mockito.verify(logObserver, times(1)).log(anyString());
    }

}
