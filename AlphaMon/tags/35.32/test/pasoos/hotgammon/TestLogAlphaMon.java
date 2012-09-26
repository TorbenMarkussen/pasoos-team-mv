package pasoos.hotgammon;

import pasoos.hotgammon.gamelogger.GameLogDecoratorImpl;
import pasoos.hotgammon.rules.factory.AlphaMonFactory;

public class TestLogAlphaMon extends TestAlphamon {

    @Override
    public void setup() throws IllegalAccessException, InstantiationException {
        game = new GameLogDecoratorImpl(new AlphaMonFactory());
        game.newGame();

    }
}
