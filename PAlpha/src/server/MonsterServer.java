/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


import interfaces.Compute;
import java.net.*;
import java.io.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernandasramirezm
 */
//Este servidor incluye el de TCP y multicast 
public class MonsterServer implements Compute{
    
    static Partida juego= new Partida();
    static int puertoTCP= 58973;
    static String MCA = "224.8.8.8";
    static int puertoMC = 52685;
    static String TCPA;

    
    public MonsterServer(){
        super();
    }
        
    @Override
     public boolean logIn(String name) throws RemoteException {        
        if (juego.participantes.get(name) == null)
            juego.add(name);
        if (juego.conectados.get(name) == null || !juego.conectados.get(name)){
            //Connect
            juego.conectados.put(name, true);
            return true;
        }        
        return false;        
    }  
         @Override
    public void Exit(String name) throws RemoteException {
        juego.conectados.put(name, false);
    }

    public Partida getJuego() {
        return juego;
    }

    @Override
    public int getPuertoTCP() {
        return puertoTCP;
    }

    @Override
    public String getMCA() {
        return MCA;
    }

    @Override
    public int getPuertoMC() {
        return puertoMC;
    }

    @Override
    public String getTCPA() {
        return TCPA;
    }
    
    public static void main(String args[]) throws UnknownHostException, InterruptedException{ 
        System.setProperty("java.net.preferIPv4Stack" , "true"); 
        System.setProperty("java.security.policy","/Users/fernandasramirezm/NetBeansProjects/PAlpha/src/server/server.policy");
     if (System.getSecurityManager() == null) { 
         System.setSecurityManager(new SecurityManager());
     }
      try {            
            LocateRegistry.createRegistry(1099);
            MonsterServer engine = new MonsterServer();
           Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("MonsterServer", stub);
                       
        } catch (RemoteException ex) {
            Logger.getLogger(MonsterServer.class.getName()).log(Level.SEVERE, null, ex);
        }   //Fin RMI
       TCPA = Inet4Address.getLocalHost().getHostAddress();
        
        
//Parte MC
	MulticastSocket s =null;
        Integer posicion; //random int que da la posicion del monstruo
   	 try {
                
                InetAddress group = InetAddress.getByName(MCA); // destination multicast group 
	    	s = new MulticastSocket(puertoMC);
	   	s.joinGroup(group); 
                 System.out.println("Multicast inicializado");
                String myMessage;
                TCPServer tcp = new TCPServer(juego,puertoTCP);
                tcp.start();
              
               
               while(true){
                   if(!juego.gameOver){
                       juego.nextRound();
                       posicion= juego.generarMonstruo();
                       myMessage= "n ".concat(((Integer)juego.partida).toString().concat(" ").concat(posicion.toString())); 
                   }
                   else{ //ganó alguien así que enviamos ganador y reiniciamos todo
                       myMessage = "ganador ".concat(juego.ganador);
                       juego.newGame();
                   }
                     byte [] m = myMessage.getBytes();
                     DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6789);
                    s.send(messageOut);

                    Thread.sleep(2500); //HACE esto cada 2.5 segundos
               }
               
               
                
              
         }catch (SocketException e){
             System.out.println("Socket: " + e.getMessage());
	 }
         catch (IOException e){
             System.out.println("IO: " + e.getMessage());
         }  
          finally {
            if(s != null) s.close();
        }
}

   
    
    }


