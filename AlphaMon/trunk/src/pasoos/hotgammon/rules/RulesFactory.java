package pasoos.hotgammon.rules;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.validator.MoveValidatorStrategy;
import pasoos.hotgammon.gameengine.winner.WinnerStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 28-08-12
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */

public interface RulesFactory {
    MoveValidatorStrategy GetMoveValidatorStrategy(Board board);

    WinnerStrategy GetWinnerStrategy(Board board);
}
