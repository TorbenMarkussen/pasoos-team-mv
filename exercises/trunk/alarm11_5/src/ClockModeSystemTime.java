import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sp
 * Date: 27-08-12
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */
public class ClockModeSystemTime extends ClockModeTime implements ClockMode {

    public ClockModeSystemTime(ClockTime initialTime) {
        super (initialTime) ;
    }

    @Override
    public void increase() {
    }

    @Override
    public void decrease() {
    }
}
