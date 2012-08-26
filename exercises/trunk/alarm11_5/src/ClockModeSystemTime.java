import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 24-08-12
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
public class ClockModeSystemTime implements ClockMode {
    @Override
    public Date getDate() {
        return new Date(1,1,2000,11,32,00);
    }

    @Override
    public void increase() {

    }

    @Override
    public void decrease() {

    }
}
