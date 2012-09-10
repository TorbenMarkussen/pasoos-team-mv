
public class Main {
    public static void main(String[] args) {
        ToolKitFactory factory = null;
        switch (OSDetector.get()) {
            case Windows:
                factory = new WindowsToolkitFactory();
            case Linux:
                factory = new LinuxToolkitFactory();
        }
        YButton button = new YButton(factory) ;//new YButton(factory);
        YListBox listbox = new YListBox(factory);
        // click the button
        button.click();
        // select item 7 on the listbox
        listbox.select(7);
    }
}
