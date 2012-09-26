package pasoos.hotgammon.rules.validator;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gameengine.GameState;
import pasoos.hotgammon.rules.MoveValidatorStrategy;

public class HandiMoveStrategy implements MoveValidatorStrategy {
    private GameState gameState;
    private final MoveValidatorStrategy blackStrategy;
    private final MoveValidatorStrategy redStrategy;

    public HandiMoveStrategy(GameState gameState, MoveValidatorStrategy blackStrategy, MoveValidatorStrategy redStrategy) {

        this.gameState = gameState;
        this.blackStrategy = blackStrategy;
        this.redStrategy = redStrategy;
    }

    @Override
    public boolean isValidMove(Location from, Location to, int dice) {
        if (gameState.getPlayerInTurn() == Color.BLACK) {
            return blackStrategy.isValidMove(from, to, dice);
        } else if (gameState.getPlayerInTurn() == Color.RED) {
            return redStrategy.isValidMove(from, to, dice);
        } else
            return false;

    }
}
