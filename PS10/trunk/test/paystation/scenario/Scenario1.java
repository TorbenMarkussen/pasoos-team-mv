package paystation.scenario;

import javax.swing.UIManager;

import paystation.client.StatusFrame;
import paystation.common.StatusObservable;
import paystation.server.builder.Tester;
import paystation.server.view.PayStationGUI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public class Scenario1 {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        String[] l = Naming.list("");
        for(String url : l){
            Remote o = Naming.lookup(url);

            if(o instanceof Tester){
            }
        }



        System.out.println("Scenario 1: Supervising 4 pay stations.");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // ignore silently, as we then just do not get the required
            // look and feel for all windows
        }



//        PayStationGUI g1, g2, g3, g4;
//
//        g1 = new PayStationGUI(10, 10);
//        g2 = new PayStationGUI(300, 10);
//        g3 = new PayStationGUI(10, 250);
//        g4 = new PayStationGUI(300, 250);
//
//        StatusFrame f1 = new StatusFrame(580, 10);
//        g1.getPayStation().addStatusListener(f1.getStatusListener());
//        g2.getPayStation().addStatusListener(f1.getStatusListener());
//        g3.getPayStation().addStatusListener(f1.getStatusListener());
//        g4.getPayStation().addStatusListener(f1.getStatusListener());

//        StatusFrame f2 = new StatusFrame(580, 250);
//        g1.getPayStation().addStatusListener(f2.getStatusListener());
//        g2.getPayStation().addStatusListener(f2.getStatusListener());
//        g3.getPayStation().addStatusListener(f2.getStatusListener());
//        g4.getPayStation().addStatusListener(f2.getStatusListener());

//        System.out.println("Ready...");
    }
}
