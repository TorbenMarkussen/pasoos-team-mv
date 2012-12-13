package pasoos.hotgammon.ai.t6;

import org.junit.Before;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.ai.AIPlayerTest;

public class DummyBlackPlayerTest extends AIPlayerTest {
    @Before
    public void setup() {
        aiPlayer = new Dummy(game, Color.BLACK);
    }

}
