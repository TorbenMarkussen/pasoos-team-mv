/**
 * Created with IntelliJ IDEA.
 * User: sp
 * Date: 09-09-12
 * Time: 22:49
 * To change this template use File | Settings | File Templates.
 */
public class LinuxToolkitFactory implements ToolKitFactory {
    @Override
    public YButtonBridge getYButtonBridge() {
        return new YButtonXBridge();
    }

    @Override
    public YListBoxBridge getYListBoxBridge() {
        return new YListBoxXBridge();
    }
}
