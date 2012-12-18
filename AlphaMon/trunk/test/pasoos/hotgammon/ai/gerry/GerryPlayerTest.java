package pasoos.hotgammon.ai.gerry;

import org.junit.Before;
import pasoos.hotgammon.ai.AIPlayerTestBase;

public class GerryPlayerTest extends AIPlayerTestBase {

    @Before
    public void setup() {
        aiPlayer = new GerryPlayer(game);
    }

}
