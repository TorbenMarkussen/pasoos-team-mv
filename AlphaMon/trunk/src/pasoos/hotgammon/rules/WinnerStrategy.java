package pasoos.hotgammon.rules;

import pasoos.hotgammon.Color;

/**
 * Created with IntelliJ IDEA.
 * User: sp
 * Date: 31-08-12
 * Time: 00:10
 * To change this template use File | Settings | File Templates.
 */
public interface WinnerStrategy {
    Color determineWinner(int turnCount);
}
