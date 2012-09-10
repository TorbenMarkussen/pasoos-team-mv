/**
 * Created with IntelliJ IDEA.
 * User: sp
 * Date: 09-09-12
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */
public class WindowsToolkitFactory implements ToolKitFactory{
    @Override
    public YButtonBridge getYButtonBridge() {
        return new YButtonWin32Bridge();
    }

    @Override
    public YListBoxBridge getYListBoxBridge() {
        return new YListBoxWin32Bridge();
    }
}
