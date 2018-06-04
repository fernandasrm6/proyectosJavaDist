package server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import interfaces.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author fernandasramirezm
 */
//Este servidor incluye el de TCP y multicast 

public class MonsterServer implements Compute{
    
    static JuegoM juego = new JuegoM();
    static String MCA = "224.8.8.8";
    static String TCPA;
    static int MCP = 52685;
    static int TCPP = 58973;
    
    public static void getIP() throws UnknownHostException{
        TCPA = Inet4Address.getLocalHost().getHostAddress();
    }
    
    @Override
    public void Exit(String username) throws RemoteException {
        juego.conectados.put(username, false);
    }

    @Override
    public String getMCA() throws RemoteException {
        return MCA;
    }
    @Override
    public int getMCP() throws RemoteException {
        return MCP;
    }
    @Override
    public String getTCPA() throws RemoteException {
        return TCPA;
    }
    @Override
    public int getTCPP() throws RemoteException {
        return TCPP;
    }
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        System.setProperty("java.net.preferIPv4Stack" , "true");
        System.setProperty("java.security.policy","/Users/fernandasramirezm/NetBeansProjects/PASD/src/server/server.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
            String name = "MonsterServer";        
        
        try {            
            LocateRegistry.createRegistry(1099);
            MonsterServer engine = new MonsterServer();
            Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
                       
        } catch (RemoteException ex) {
            Logger.getLogger(MonsterServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Fin RMI
        TCPA=Inet4Address.getLocalHost().getHostAddress();
        getIP();
      
                   
        MulticastSocket s = null;
        Integer pos;
        try {

            InetAddress group = InetAddress.getByName(MCA); // destination multicast group 
            s = new MulticastSocket(MCP);
            s.joinGroup(group); 
            System.out.println("Multicast inicializado");
            TCPServer tcp = new TCPServer(juego, TCPP);
            String myMessage;
            tcp.start();
            
            
            
            while (true) {
                if (!juego.terminado) {
                    juego.nextRound();
                    pos = juego.generaMonstruo();
                    myMessage = "n ".concat(((Integer)(juego.ronda)).toString()).concat(" ").concat(pos.toString());
                                      
                } else {
                    myMessage = "ganador ".concat(juego.ganador);                    
                    juego.reset();
                }
                
                byte [] m = myMessage.getBytes();
                DatagramPacket messageOut = new DatagramPacket(m, m.length, group, MCP);
                s.send(messageOut);
                Thread.sleep(2000);
                
            }          

        }
        catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }
        catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(MonsterServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if(s != null) s.close();
        } 
    }

    
    public MonsterServer() throws RemoteException{
        super();
    }

    @Override
    public boolean logIn(String nombreJugador) throws RemoteException {        
        if (juego.usuarios.get(nombreJugador) == null)
            juego.agregaParticipante(nombreJugador);
        if (juego.conectados.get(nombreJugador) == null || !juego.conectados.get(nombreJugador)){
            //Connect
            juego.conectados.put(nombreJugador, true);
            return true;
        }        
        return false;        
    }    

}
