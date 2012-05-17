package paystation.client;

import paystation.common.StatusListener;
import paystation.common.StatusObservable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * A crude graphical monitor application.
 */
public class StatusFrame extends JFrame {
    private JLabel vLabel, eLabel;
    private StatusListener myListener;
    private StatusListener myListenerProxy;

    public StatusFrame(int x, int y) throws RemoteException {
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

    public StatusListener getProxyStatusListener() throws RemoteException {
        if (myListenerProxy == null) {
            Remote remote = UnicastRemoteObject.exportObject((StatusListener) myListener, 0);
            myListenerProxy = (StatusListener) remote;
        }
        return myListenerProxy;
    }

    public void addPayStation(String payStationName, StatusObservable so) throws RemoteException {
        so.addStatusListener(getProxyStatusListener());
    }
}
