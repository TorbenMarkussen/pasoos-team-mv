import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 28-08-12
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
public class BetamonMoveValidatorStrategyImpl implements MoveValidatorStrategy {
    private Board board;

    public BetamonMoveValidatorStrategyImpl(Board board) {
        this.board = board;
    }

    @Override
    public int isValidMove(Location from, Location to, int[] dices) {
        Color fromColor = board.getColor(from);
        Color toColor = board.getColor(to);

        Color colorGivenByDirection = Color.getColorFromNumerical(Location.distance(from, to));
        if (colorGivenByDirection != fromColor)
            return 0;

        List<Integer> diceList = new ArrayList<Integer>();
        for (int d : dices)
            diceList.add(d);

        int distance = Math.abs(Location.distance(from, to));
        if (!diceList.contains(distance))
            return 0;

        if (toColor != fromColor && toColor != Color.NONE) {
            //opponent found at 'to' destination
            if (board.getCount(to) > 1)
                return 0;
        }

        return distance;
    }
}
