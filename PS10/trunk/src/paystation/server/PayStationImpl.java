package paystation.server;

import paystation.common.StatusEvent;
import paystation.common.StatusListener;
import paystation.common.StatusObservable;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the pay station.
 * <p/>
 * Responsibilities:
 * <p/>
 * 1) Accept payment; 2) Calculate parking time based on payment; 3) Know earning, parking time bought; 4) Issue receipts; 5) Handle buy and
 * cancel events.
 */

public class PayStationImpl implements PayStation, StatusObservable {
    private int insertedSoFar;
    private int timeBought;

    public void addPayment(int coinValue) throws IllegalCoinException {
        switch (coinValue) {
            case 5:
                break;
            case 10:
                break;
            case 25:
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        timeBought = insertedSoFar / 5 * 2;
    }

    public int readDisplay() {
        return timeBought;
    }

    public Receipt buy() {
        Receipt r = new StandardReceipt(timeBought, false);
        _notify(timeBought, insertedSoFar);
        reset();
        return r;
    }

    public void cancel() {
        reset();
    }

    private void reset() {
        timeBought = insertedSoFar = 0;
    }

    // Implementation of an Observer pattern for monitoring this
    // paystation for the PS10 exercise. The Observer pattern is
    // explained in FRS Chapter 28.

    /**
     * Internal list of the associated listeners
     */
    private List<StatusListener> listeners;

    public PayStationImpl(String payStationName, Registry registry) throws RemoteException, MalformedURLException {
        StatusObservable so = this;
        Remote proxy = UnicastRemoteObject.exportObject(so, 0);

        registry.rebind(payStationName, proxy);
        reset();

        listeners = new ArrayList<StatusListener>();
    }

    public void addStatusListener(StatusListener listener) {
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    private void _notify(int time, int earned) {
        StatusEvent e = new StatusEvent(time, earned);
        synchronized (listeners) {
            for (StatusListener l : listeners) {
                try {
                    l.update(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }

}
