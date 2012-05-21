package paystation.client;

import paystation.common.StatusObservable;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pasma00t
 * Date: 17-05-12
 * Time: 19:42
 * To change this template use File | Settings | File Templates.
 */
public class RegistryScanner implements Runnable {
    private String rootUrl;
    private StatusFrame sf;
    private Thread thread;
    private Map<String, StatusObservable> payStationMap = new HashMap<String, StatusObservable>();

    public RegistryScanner(String rooturl, StatusFrame sf) {
        this.rootUrl = rooturl;
        this.sf = sf;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true){

            try {
                String[] l = Naming.list(rootUrl);
                for(String url : l){
                    Remote o = Naming.lookup(url);

                    if(o instanceof StatusObservable){
                        if(!payStationMap.containsKey(url)) {
                            sf.addPayStation(url, (StatusObservable) o);
                            payStationMap.put(url, (StatusObservable) o);
                        }
                    }
                }

                Thread.sleep(2000);

            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
