package paystation.server.builder;

import paystation.server.PayStation;
import paystation.server.PayStationImpl;
import paystation.server.view.PayStationGUI;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created with IntelliJ IDEA.
 * User: pasma00t
 * Date: 17-05-12
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public class PayStationBuilder {

    private BuilderProperties builderProperties;

    public PayStationBuilder(BuilderProperties bp) {
        builderProperties = bp;
    }

    private void buildPaystation() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // ignore silently, as we then just do not get the required
            // look and feel for all windows
        }
        try {

            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());
            }

            Registry registry = LocateRegistry.getRegistry(1099);

            PayStation ps = new PayStationImpl(builderProperties.getServerName(), registry);

            Tester t = new TesterImpl(ps);
            Remote proxy = UnicastRemoteObject.exportObject(t, 0);
            registry.rebind(builderProperties.getServerName()+"Tester", proxy);

            PayStationGUI psGUI = new PayStationGUI(builderProperties.getX(), builderProperties.getY(), builderProperties.getServerName(), ps);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        BuilderProperties bp = new BuilderProperties(args);
        PayStationBuilder builder = new PayStationBuilder(bp);
        builder.buildPaystation();
    }

}