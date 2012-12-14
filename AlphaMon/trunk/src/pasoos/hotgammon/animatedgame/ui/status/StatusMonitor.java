package pasoos.hotgammon.animatedgame.ui.status;

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
