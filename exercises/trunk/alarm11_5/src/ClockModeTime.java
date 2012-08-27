import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sp
 * Date: 27-08-12
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
 */
public abstract class ClockModeTime implements ClockMode  {
    protected ClockTime currentTime;

    public ClockModeTime(ClockTime initialTime) {
        currentTime = initialTime    ;
    }

    @Override
    public Date getTime() {
        return currentTime.clock();  //To change body of implemented methods use File | Settings | File Templates.
}

    protected void addMinutes(int minutes)
    {
        currentTime.addMinutes(minutes);
        //Calendar cal = Calendar.getInstance();
        //cal.setTime(currentTime.clock);
        //cal.add(Calendar.MINUTE, minutes);
        //currentTime.clock = cal.getTime() ;
    }
    protected void addHours(int hours)
    {
        currentTime.addHours(hours);
    }
}