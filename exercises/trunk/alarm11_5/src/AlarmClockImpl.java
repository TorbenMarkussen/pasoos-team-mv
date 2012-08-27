import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 24-08-12
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
public class AlarmClockImpl implements AlarmClock {

    ClockMode currentState;
    private int currentMode = 0;
    private ClockTime alarmTime = new ClockTime(6, 15);
    private ClockTime currentTime = new ClockTime(11, 32);
    private ClockMode[] stateMachine;

    AlarmClockImpl() {
        stateMachine = new ClockMode[]{
                new ClockModeSystemTime(currentTime),
                new ClockModeAlarmTimeHour(alarmTime),
                new ClockModeAlarmTimeMinute(alarmTime)
        };
        currentState = stateMachine[currentMode];
    }

    @Override
    public String readDisplay() {
        Date currentTime = currentState.getTime();
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

}
