package pasoos.hotgammon.ai.t6;

import org.junit.Before;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.ai.AIPlayerTestBase;

public class DummyRedPlayerTest extends AIPlayerTestBase {

    @Before
    public void setup() {
        aiPlayer = new Dummy(game, Color.RED);
    }

}
