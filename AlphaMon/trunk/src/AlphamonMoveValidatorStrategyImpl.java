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
    public int isValidMove(Location from, Location to, int[] dices) {
        Color fromColor = board.getColor(from);
        Color toColor = board.getColor(to);
        boolean isValid = toColor == Color.NONE || fromColor == toColor;
        if (!isValid)
            return 0;
        return dices[0];
    }
}
