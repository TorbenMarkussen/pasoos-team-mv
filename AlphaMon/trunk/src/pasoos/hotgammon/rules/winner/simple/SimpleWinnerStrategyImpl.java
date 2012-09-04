package pasoos.hotgammon.rules.winner.simple;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.gameengine.GameState;
import pasoos.hotgammon.rules.WinnerStrategy;

public class SimpleWinnerStrategyImpl implements WinnerStrategy {

    public SimpleWinnerStrategyImpl() {
    }

    @Override
    public Color determineWinner(GameState gameState) {
        if (gameState.getTurnCount() < 6) {
            return Color.NONE;
        } else {
            return Color.RED;
        }
    }
}
