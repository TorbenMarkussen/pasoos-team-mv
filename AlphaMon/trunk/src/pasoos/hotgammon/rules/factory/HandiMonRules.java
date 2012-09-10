package pasoos.hotgammon.rules.factory;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.GameState;
import pasoos.hotgammon.rules.MoveValidatorStrategy;
import pasoos.hotgammon.rules.validator.HandiMoveStrategyImpl;
import pasoos.hotgammon.rules.validator.alphamon.AlphamonMoveValidatorStrategyImpl;
import pasoos.hotgammon.rules.validator.betamon.BetamonMoveValidatorStrategyImpl;


public class HandiMonRules extends AlphaMonRules {
    @Override
    public MoveValidatorStrategy createMoveValidatorStrategy(GameState gameState, Board board) {
        return new HandiMoveStrategyImpl(gameState,
                new AlphamonMoveValidatorStrategyImpl(board),
                new BetamonMoveValidatorStrategyImpl(board));
    }
}
