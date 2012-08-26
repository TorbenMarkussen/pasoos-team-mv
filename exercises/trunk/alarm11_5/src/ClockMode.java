import javax.print.attribute.standard.DateTimeAtCompleted;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 24-08-12
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public interface ClockMode {

    Date getDate();
    void increase();
    void decrease();

}
