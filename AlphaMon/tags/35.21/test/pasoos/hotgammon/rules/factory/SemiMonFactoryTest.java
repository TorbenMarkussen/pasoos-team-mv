package pasoos.hotgammon.rules.factory;

import org.junit.Test;
import pasoos.hotgammon.gameengine.BoardImpl;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.diceroller.RandomDiceRoller;

import static junit.framework.Assert.assertTrue;


public class SemiMonFactoryTest {

    @Test
    public void should_return_semimon_factory() {
        HotGammonFactory factory = new SemiMonFactory();
        assertTrue(factory.createBoard() instanceof BoardImpl);
        assertTrue(factory.createDiceRoller() instanceof RandomDiceRoller);
    }
}
