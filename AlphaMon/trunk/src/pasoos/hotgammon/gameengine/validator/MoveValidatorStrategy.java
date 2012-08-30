package pasoos.hotgammon.gameengine.validator;

import pasoos.hotgammon.Location;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 28-08-12
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
public interface MoveValidatorStrategy {
    int isValidMove(Location from, Location to, int[] dices);
}
