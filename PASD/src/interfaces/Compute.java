
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote{
    

        public boolean logIn(String username) throws RemoteException;
        public void Exit(String username) throws RemoteException;
        public String getMCA() throws RemoteException;
        public int getMCP() throws RemoteException;
        public String getTCPA() throws RemoteException;
        public int getTCPP() throws RemoteException;
}
