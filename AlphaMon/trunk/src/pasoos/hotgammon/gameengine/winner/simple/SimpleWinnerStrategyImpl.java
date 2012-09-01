package pasoos.hotgammon.gameengine.winner.simple;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.winner.WinnerStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: tor
 * Date: 01-09-12
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 */
public class SimpleWinnerStrategyImpl implements WinnerStrategy {
    private Board board;

    public SimpleWinnerStrategyImpl(Board board) {
        this.board = board;
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
