import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sp
 * Date: 27-08-12
 * Time: 22:51
 * To change this template use File | Settings | File Templates.
 */
public class ClockTime {

    private int hour ;
    private int minute ;

    public Date clock ()
    {
        return new Date(1, 1, 2000, hour, minute, 0);
    }

    public ClockTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public void addMinutes(int minutes) {
        while (minutes > 60)
          minutes =- 60 ;
        while (minutes < -60)
            minutes =+ 60 ;
        if (this.minute + minutes > 59)
            this.minute -= 60 ;
        if (this.minute + minutes < 0)
            this.minute += 60 ;
        this.minute += minutes;
    }

    public void addHours (int hours)
    {
        while (hours > 24)
            hours =- 24 ;
        while (hours < -24)
            hours =+ 24 ;
        if (this.hour + hours > 23)
            this.hour -= 24 ;
        if (this.hour + hours < 0)
            this.hour += 24 ;
        this.hour += hours;
    }
}
