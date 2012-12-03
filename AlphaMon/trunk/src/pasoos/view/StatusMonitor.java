package pasoos.view;

import minidraw.framework.DrawingEditor;
import pasoos.hotgammon.Location;

public class StatusMonitor implements StatusObserver {
    private DrawingEditor editor = null;
    private String status = "";

    public StatusMonitor() {
    }

    @Override
    public void updateStatus(String s) {
        status = s;
        if (editor != null)
            editor.showStatus(s);
    }

    @Override
    public void checkerLogicalMove(Location from, Location to) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setEditor(DrawingEditor editor) {
        this.editor = editor;
        editor.showStatus(status);
    }

    @Override
    public void checkerMove(Location from, Location to) {
    }

    @Override
    public void diceRolled(int[] values) {
    }
}
