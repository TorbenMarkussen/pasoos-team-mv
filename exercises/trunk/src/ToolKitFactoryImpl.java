/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 07-09-12
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */
public class ToolKitFactoryImpl implements ToolKitFactory {
    private OperatingSystemType operatingSystemType;

    public ToolKitFactoryImpl(OperatingSystemType operatingSystemType) {
        //To change body of created methods use File | Settings | File Templates.
        this.operatingSystemType = operatingSystemType;
    }

    @Override
    public YButtonBridge getYButtonBridge() {
        if (operatingSystemType==OperatingSystemType.Windows ) {
            return new YButtonWin32Bridge();
        } else if (operatingSystemType==OperatingSystemType.Linux) {
            return new YButtonXBridge();
        }
        return null ;
    }

    @Override
    public YListBoxBridge getYListBoxBridge() {
        if (operatingSystemType==OperatingSystemType.Windows ) {
            return new YListBoxWin32Bridge();
        } else if (operatingSystemType==OperatingSystemType.Linux) {
            return new YListBoxXBridge();
        }
        return null ;

    }

    @Override
    public YButton getButton() {
        return new YButton(this);

    }

    @Override
    public YListBox getListbox() {
        return new YListBox(this);
    }
}
