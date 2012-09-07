
public class Main {
    public static void main(String[] args) {
        ToolKitFactory factory = new ToolKitFactoryImpl(OSDetector.get());
        YButton button = factory.getButton();//new YButton(factory);
        YListBox listbox = factory.getListbox();
        // click the button
        button.click();
        // select item 7 on the listbox
        listbox.select(7);
    }
}
