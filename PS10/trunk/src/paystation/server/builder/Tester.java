package paystation.server.builder;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 * User: pasma00t
 * Date: 17-05-12
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public interface Tester extends Remote {
    public void testA() throws RemoteException;
}
