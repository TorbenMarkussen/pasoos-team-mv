package paystation.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The subject role of the observer pattern: a StatusObservable instance can be observed for pay station state changes.
 */

public interface StatusObservable extends Remote {
    /** Add a status listener to this instance */
    public void addStatusListener(StatusListener listener) throws RemoteException;
}
