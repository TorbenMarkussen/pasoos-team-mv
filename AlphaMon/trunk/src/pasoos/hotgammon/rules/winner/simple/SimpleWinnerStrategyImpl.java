package pasoos.hotgammon.rules.winner.simple;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.rules.WinnerStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: tor
 * Date: 01-09-12
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 */
public class SimpleWinnerStrategyImpl implements WinnerStrategy {

    public SimpleWinnerStrategyImpl() {
    }

    @Override
    public Color determineWinner(int turnCount) {
        if (turnCount < 6) {
            return Color.NONE;
        } else {
            return Color.RED;
        }
    }
}
