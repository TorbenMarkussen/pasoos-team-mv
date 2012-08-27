import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sp
 * Date: 27-08-12
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
public interface ClockMode {
    public Date getTime() ;

    void increase();

    void decrease();
}
