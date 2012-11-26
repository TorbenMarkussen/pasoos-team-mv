package pasoos.view;

import minidraw.framework.DrawingEditor;

public class StatusMonitor implements StatusObserver {
    private DrawingEditor editor = null;
    private String status = "";

    public StatusMonitor() {
    }

    @Override
    public void updateStatus(String s) {
        status = s;
        //System.out.println(s);
        if (editor != null)
            editor.showStatus(s);
    }

    public void setEditor(DrawingEditor editor) {
        this.editor = editor;
        editor.showStatus(status);
        //System.out.println(":" + status);
    }
}
