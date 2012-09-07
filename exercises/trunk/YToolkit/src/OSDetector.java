/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 07-09-12
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
public class OSDetector {
    public static OperatingSystemType get ()
    {
        String os = System.getProperty("os.name");
        System.out.println("Detected OperatingSystemType = " + os);
        // force other operating system
        os = "Linux";
        if (os.indexOf("Windows") > -1) {
            return OperatingSystemType.Windows ;
        } else if (os.indexOf("Linux") > -1) {
            return OperatingSystemType.Linux;
        }else
            return OperatingSystemType.Unknown ;

    }
}
