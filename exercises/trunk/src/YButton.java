public class YButton {
    private YButtonBridge peer;

    public YButton(ToolKitFactory factory) {
        peer = factory.getYButtonBridge();
        // Read environment variable "OperatingSystemType"

    }

    public void click() {
        peer.click();
    }
}

interface YButtonBridge {
    public void click();
}

// The bridge to the Win32 platform
class YButtonWin32Bridge implements YButtonBridge {
    private Win32Button w32button;

    public YButtonWin32Bridge() {
        w32button = new Win32Button();
    }

    public void click() {
        w32button.press();
    }
}

// The real Win32 Button class
final class Win32Button {
    public void press() {
        System.out.println("Win32 Button pressed.");
    }
}


class YButtonXBridge implements YButtonBridge {
    private XButton xbutton;

    public YButtonXBridge() {
        xbutton = new XButton();
    }

    public void click() {
        xbutton.buttonPressEvent();
    }
}

// The real X server button class
final class XButton {
    public void buttonPressEvent() {
        System.out.println("X server Button pressed.");
    }
}