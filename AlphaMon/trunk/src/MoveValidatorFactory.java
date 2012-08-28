/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 28-08-12
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */

public interface MoveValidatorFactory {
    MoveValidatorStrategy Get(Board board);
}
