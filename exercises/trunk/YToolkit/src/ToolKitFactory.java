/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 07-09-12
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */
public interface ToolKitFactory {

    YButtonBridge getYButtonBridge();

    YListBoxBridge getYListBoxBridge();

    YButton getButton();

    YListBox getListbox();
}
