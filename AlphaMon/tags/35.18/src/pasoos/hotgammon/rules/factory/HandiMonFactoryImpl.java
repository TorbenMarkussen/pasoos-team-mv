package pasoos.hotgammon.rules.factory;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.GameState;
import pasoos.hotgammon.rules.MoveValidatorStrategy;
import pasoos.hotgammon.rules.validator.HandiMoveStrategyImpl;
import pasoos.hotgammon.rules.validator.AlphamonMoveValidatorStrategyImpl;
import pasoos.hotgammon.rules.validator.BetamonMoveValidatorStrategyImpl;


public class HandiMonFactoryImpl extends AlphaMonFactoryImpl {
    @Override
    public MoveValidatorStrategy createMoveValidatorStrategy(GameState gameState, Board board) {
        return new HandiMoveStrategyImpl(gameState,
                new AlphamonMoveValidatorStrategyImpl(board),
                new BetamonMoveValidatorStrategyImpl(board));
    }
}
