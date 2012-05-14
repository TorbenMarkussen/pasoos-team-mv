package paystation.monitor;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import paystation.domain.StatusListener;

/**
 * A crude graphical monitor application.
 */
public class StatusFrame extends JFrame {
    private JLabel vLabel, eLabel;
    private StatusListener myListener;

    public StatusFrame(int x, int y) {
        super("Supervisor");
        setLocation(x, y);
        vLabel = new JLabel("  Total Earning xxxx  ");
        eLabel = new JLabel("  Number of vacant: 200 ");
        Container pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(vLabel);
        pane.add(eLabel);

        myListener = new LabelUpdater(vLabel, eLabel);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        pack();
        setVisible(true);
    }

    public StatusListener getStatusListener() {
        return myListener;
    }
}
