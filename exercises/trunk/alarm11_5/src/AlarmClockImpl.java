import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 24-08-12
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
public class AlarmClockImpl implements AlarmClock, AlarmTime {

    ClockMode currentState;
    private int currentMode = 0;
    private Date alarmTime = new Date(1, 1, 2000, 6, 15, 0);
    private ClockMode[] stateMachine;

    AlarmClockImpl() {
        stateMachine = new ClockMode[]{
                new ClockModeSystemTime(),
                new ClockModeAlarmTime(this, 60),
                new ClockModeAlarmTime(this, 1)
        };
        currentState = stateMachine[currentMode];
    }

    @Override
    public String readDisplay() {
        Date currentTime = currentState.getDate();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(currentTime).toString();
    }

    @Override
    public void mode() {
        currentMode++;
        if (currentMode >= stateMachine.length)
            currentMode = 0;

        currentState = stateMachine[currentMode];
    }

    @Override
    public void increase() {
        currentState.increase();
    }

    @Override
    public void decrease() {
        currentState.decrease();
    }

    @Override
    public Date getAlarm() {
        return alarmTime;
    }

    @Override
    public void setAlarm(Date d) {
        alarmTime = d;
    }
}
