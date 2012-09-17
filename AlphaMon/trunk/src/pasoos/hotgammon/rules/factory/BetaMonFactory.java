package pasoos.hotgammon.rules.factory;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.gameengine.GameState;
import pasoos.hotgammon.rules.MoveValidatorStrategy;
import pasoos.hotgammon.rules.validator.BackgammonMoveValidatorStrategy;

public class BetaMonFactory extends AlphaMonFactory {
    @Override
    public MoveValidatorStrategy createMoveValidatorStrategy(GameState gameState, Board board) {
        return new BackgammonMoveValidatorStrategy(board);
    }
}
