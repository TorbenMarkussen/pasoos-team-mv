import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 24-08-12
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
public class ClockModeAlarmTime implements ClockMode {
    private AlarmTime alarmTime;
    private int delta;

    public ClockModeAlarmTime(AlarmTime currentAlarm, int delta) {
        this.alarmTime = currentAlarm;
        this.delta = delta;
    }

    @Override
    public Date getDate() {
        return alarmTime.getAlarm();
    }

    @Override
    public void increase() {
        modifyAlarm(delta);
    }

    private void modifyAlarm(int deltaMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(alarmTime.getAlarm());
        cal.add(Calendar.MINUTE, deltaMinutes);
        alarmTime.setAlarm(cal.getTime());
    }

    @Override
    public void decrease() {
        modifyAlarm(-delta);
    }
}
