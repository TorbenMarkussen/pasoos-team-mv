package pasoos.hotgammon.rules.factory;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.rules.MoveValidatorStrategy;
import pasoos.hotgammon.rules.validator.betamon.BetamonMoveValidatorStrategyImpl;

public class BetaMonRules extends AlphaMonRules {
    @Override
    public MoveValidatorStrategy createMoveValidatorStrategy(Board board) {
        return new BetamonMoveValidatorStrategyImpl(board);
    }
}
