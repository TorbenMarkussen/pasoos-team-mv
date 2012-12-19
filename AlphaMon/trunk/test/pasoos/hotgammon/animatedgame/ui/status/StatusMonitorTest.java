package pasoos.hotgammon.animatedgame.ui.status;


import minidraw.framework.DrawingEditor;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class StatusMonitorTest {
    @Test
    public void should_update_editor() {
        StatusMonitor statusMonitor = new StatusMonitor();
        DrawingEditor editor = mock(DrawingEditor.class);
        statusMonitor.setEditor(editor);
        statusMonitor.updateStatus("Test");
        verify(editor, times(1)).showStatus("Test");
    }
}



