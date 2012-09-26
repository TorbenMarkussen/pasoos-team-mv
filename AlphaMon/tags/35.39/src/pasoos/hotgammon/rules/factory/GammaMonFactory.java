package pasoos.hotgammon.rules.factory;

import pasoos.hotgammon.rules.WinnerStrategy;
import pasoos.hotgammon.rules.winner.BackgammonWinnerStrategy;

public class GammaMonFactory extends AlphaMonFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new BackgammonWinnerStrategy();
    }
}
