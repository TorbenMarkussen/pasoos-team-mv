package pasoos.hotgammon.rules.validator.alphamon;

import pasoos.hotgammon.gameengine.Board;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.rules.MoveValidatorStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 28-08-12
 * Time: 20:27
 * To change this template use File | Settings | File Templates.
 */
public class AlphamonMoveValidatorStrategyImpl implements MoveValidatorStrategy {

    private Board board;

    public AlphamonMoveValidatorStrategyImpl(Board board) {
        this.board = board;
    }

    @Override
    public boolean isValidMove(Location from, Location to, int dice) {
        Color fromColor = board.getColor(from);
        Color toColor = board.getColor(to);
        return toColor == Color.NONE || fromColor == toColor;
    }
}
