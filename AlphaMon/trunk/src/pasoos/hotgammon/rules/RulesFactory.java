package pasoos.hotgammon.rules;

import pasoos.hotgammon.gameengine.Board;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 28-08-12
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */

public interface RulesFactory {
    MoveValidatorStrategy getMoveValidatorStrategy(Board board);

    WinnerStrategy getWinnerStrategy(Board board);
}
