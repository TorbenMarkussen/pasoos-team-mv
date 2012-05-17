package paystation.client.builder;

import paystation.client.RegistryScanner;
import paystation.client.StatusFrame;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 * User: pasma00t
 * Date: 17-05-12
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
public class MonitorBuilder {

    private MonitorProperties monitorProperties;
    private StatusFrame sf;

    public MonitorBuilder(MonitorProperties mp) {
        //To change body of created methods use File | Settings | File Templates.
        this.monitorProperties = mp;
    }

    private void buildMonitor() throws RemoteException, MalformedURLException, NotBoundException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // ignore silently, as we then just do not get the required
            // look and feel for all windows
        }
        sf = new StatusFrame(monitorProperties.getX(), monitorProperties.getY());
        RegistryScanner rs = new RegistryScanner(monitorProperties.getRootUrl(), sf);
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        MonitorProperties mp = new MonitorProperties(args);
        MonitorBuilder mb = new MonitorBuilder(mp);
        mb.buildMonitor();
    }


}
