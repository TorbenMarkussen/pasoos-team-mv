package pasoos.hotgammon.rules.factory;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.GameState;
import pasoos.hotgammon.rules.MoveValidatorStrategy;
import pasoos.hotgammon.rules.validator.AlphamonMoveValidatorStrategy;
import pasoos.hotgammon.rules.validator.BackgammonMoveValidatorStrategy;
import pasoos.hotgammon.rules.validator.HandiMoveStrategy;


public class HandiMonFactory extends AlphaMonFactory {
    @Override
    public MoveValidatorStrategy createMoveValidatorStrategy(GameState gameState, Board board) {
        return new HandiMoveStrategy(gameState,
                new AlphamonMoveValidatorStrategy(board),
                new BackgammonMoveValidatorStrategy(board));
    }
}
