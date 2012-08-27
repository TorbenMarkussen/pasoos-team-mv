import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sp
 * Date: 27-08-12
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
public class ClockModeAlarmTimeMinute  extends ClockModeTime implements ClockMode {

    public ClockModeAlarmTimeMinute(ClockTime alarmTime) {
        super (alarmTime) ;
    }

    @Override
    public void increase() {
        addMinutes(1);
    }

    @Override
    public void decrease() {
        addMinutes(-1);
    }
}
