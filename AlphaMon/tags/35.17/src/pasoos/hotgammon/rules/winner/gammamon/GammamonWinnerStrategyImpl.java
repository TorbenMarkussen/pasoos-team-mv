package pasoos.hotgammon.rules.winner.gammamon;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.gameengine.GameState;
import pasoos.hotgammon.rules.WinnerStrategy;

public class GammamonWinnerStrategyImpl implements WinnerStrategy {

    public GammamonWinnerStrategyImpl() {
    }

    @Override
    public Color determineWinner(GameState gameState) {
        if (gameState.allCheckersOnBearOff(Color.BLACK))
            return Color.BLACK;
        else if (gameState.allCheckersOnBearOff(Color.RED))
            return Color.RED;
        else
            return Color.NONE;
    }
}
