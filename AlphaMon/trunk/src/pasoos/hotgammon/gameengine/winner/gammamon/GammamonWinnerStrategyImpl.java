package pasoos.hotgammon.gameengine.winner.gammamon;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.winner.WinnerStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: tor
 * Date: 01-09-12
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
public class GammamonWinnerStrategyImpl implements WinnerStrategy {

    private Board board;

    public GammamonWinnerStrategyImpl(Board board) {
        this.board = board;
    }

    @Override
    public Color determineWinner(int turnCount) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
