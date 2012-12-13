package pasoos.hotgammon.ai.gerry;

import org.junit.Before;
import pasoos.hotgammon.ai.AIPlayerTest;

public class GerryPlayerTest extends AIPlayerTest {

    @Before
    public void setup() {
        aiPlayer = new GerryPlayer(game);
    }

}
