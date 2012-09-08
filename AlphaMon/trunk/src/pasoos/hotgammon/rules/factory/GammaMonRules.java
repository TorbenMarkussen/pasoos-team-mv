package pasoos.hotgammon.rules.factory;

import pasoos.hotgammon.rules.WinnerStrategy;
import pasoos.hotgammon.rules.winner.gammamon.GammamonWinnerStrategyImpl;

public class GammaMonRules extends AlphaMonRules {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new GammamonWinnerStrategyImpl();
    }
}
