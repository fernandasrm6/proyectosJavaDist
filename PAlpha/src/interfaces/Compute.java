/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author fernandasramirezm
 */
public interface Compute extends Remote {
 public boolean logIn(String username) throws RemoteException;
        public void Exit(String username) throws RemoteException;
        public String getMCA() throws RemoteException;
        public int getPuertoMC() throws RemoteException;
        public String getTCPA() throws RemoteException;
        public int getPuertoTCP() throws RemoteException;
}