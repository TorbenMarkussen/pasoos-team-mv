package paystation.client;

import paystation.common.StatusListener;
import paystation.common.StatusObservable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * A crude graphical monitor application.
 */
public class StatusFrame extends JFrame {
    private JLabel vLabel, eLabel;
    private StatusListener myListener;
    private StatusListener myListenerProxy;

    public static void main(String[] args) throws RemoteException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // ignore silently, as we then just do not get the required
            // look and feel for all windows
        }
        new StatusFrame(580, 10, args);
    }

    public StatusFrame(int x, int y, String[] payStationNames) throws RemoteException {
        super("Supervisor");

        setLocation(x, y);
        vLabel = new JLabel("  Total Earning xxxx  ");
        eLabel = new JLabel("  Number of vacant: 200 ");
        Container pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(vLabel);
        pane.add(eLabel);

        myListener = new LabelUpdater(vLabel, eLabel);
        //myListenerProxy = (StatusListener) UnicastRemoteObject.exportObject(myListener);

        for (String payStationName : payStationNames)  {
            String registryURL = "rmi://localhost/"+payStationName;

            try {

                StatusObservable so = (StatusObservable) Naming.lookup(registryURL);

                so.addStatusListener((StatusListener) UnicastRemoteObject.exportObject(myListener));

            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


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
