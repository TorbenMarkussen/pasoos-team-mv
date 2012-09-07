public class YListBox {
    private YListBoxBridge peer;

    public YListBox(ToolKitFactory factory) {
        peer = factory.getYListBoxBridge ();
    }

    public void select(int index) {
        peer.select(index);
    }
}

interface YListBoxBridge {
    public void select(int index);
}

// The bridge to the Win32 platform
class YListBoxWin32Bridge implements YListBoxBridge {
    private Win32ListBox w32listBox;

    public YListBoxWin32Bridge() {
        w32listBox = new Win32ListBox();
    }

    public void select(int index) {
        w32listBox.press(index);
    }
}

// The real Win32 ListBox class
final class Win32ListBox {
    public void press(int index) {
        System.out.println("Win32 ListBox select of item #" + index);
    }
}


class YListBoxXBridge implements YListBoxBridge {
    private XListBox xlistBox;

    public YListBoxXBridge() {
        xlistBox = new XListBox();
    }

    public void select(int index) {
        xlistBox.listBoxChoiceEvent(index);
    }
}

// The real X server listBox class
final class XListBox {
    public void listBoxChoiceEvent(int index) {
        System.out.println("X server ListBox choice event for item " + index);
    }
}