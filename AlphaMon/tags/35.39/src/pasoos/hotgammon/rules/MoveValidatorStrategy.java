package pasoos.hotgammon.rules;

import pasoos.hotgammon.Location;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 28-08-12
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
public interface MoveValidatorStrategy {
    boolean isValidMove(Location from, Location to, int dice);
}
