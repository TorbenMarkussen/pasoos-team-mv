package pasoos.hotgammon.rules.validator.alphamon;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gameengine.BoardState;
import pasoos.hotgammon.rules.MoveValidatorStrategy;

public class AlphamonMoveValidatorStrategyImpl implements MoveValidatorStrategy {

    private BoardState board;

    public AlphamonMoveValidatorStrategyImpl(BoardState board) {
        this.board = board;
    }

    @Override
    public boolean isValidMove(Location from, Location to, int dice) {
        Color fromColor = board.getColor(from);
        Color toColor = board.getColor(to);
        return toColor == Color.NONE || fromColor == toColor;
    }
}
