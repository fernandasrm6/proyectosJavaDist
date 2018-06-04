/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import interfaces.Compute;
/**
 *
 * @author fernandasramirezm
 */

public class MonsterClient extends Thread{
    
    JButton[] botones;
    static Integer partida;
    RejillaJuego juego;
    String MulticastA;
    int multicastPort;

    public MonsterClient(String multicastAddress, int multicastPort, JButton[] btns, RejillaJuego jugador) {
        this.MulticastA=multicastAddress;
        this.multicastPort=multicastPort;
        this.botones = btns;
        this.juego = jugador;
    }
    
    @Override
    public void run() {
        MulticastSocket socket = null;
        try {
            InetAddress group = InetAddress.getByName(MulticastA);
            socket = new MulticastSocket(multicastPort);
            socket.joinGroup(group);
            while(true){
                byte[] buffer = new byte[100];
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                socket.receive(messageIn);
                String roundInfo[] = new String(messageIn.getData()).trim().split(" ");
                
                if (roundInfo[0].equals("n")){ // 1-ronda 2-mc
                    partida = Integer.parseInt(roundInfo[1]);
                    int pos = Integer.parseInt(roundInfo[2]);
                    ImageIcon im1 = new ImageIcon(getClass().getResource("/client/black.png"));
                    ImageIcon im2 = new ImageIcon(getClass().getResource("/client/mnstr.png"));
                  
                    botones[pos].setIcon(im2);
                    Thread.sleep(1000);
                    botones[pos].setIcon(im1);
                }
                
                if (roundInfo[0].equals("ganador")) {
                    System.out.println(roundInfo[1]);
                    if (roundInfo[1].equals(juego.jugador)) {
                        JOptionPane.showMessageDialog(juego, juego.jugador.concat(" felicidades!!!!, ganaste"), "Ganaste!", 1);
                    } else {
                        JOptionPane.showMessageDialog(juego, "El juego terminó", "GAME OVER ".concat(juego.jugador), 1);
                    }
                    
                    
                    System.setProperty("java.security.policy","/Users/fernandasramirezm/NetBeansProjects/PASD/src/client/client.policy");

                    if (System.getSecurityManager() == null) {
                        System.setSecurityManager(new SecurityManager());
                    }



                    Registry registry;
                    try {
                        registry = LocateRegistry.getRegistry("localhost"); // server's ip address
                        Compute gameManager = (Compute) registry.lookup("MonsterServer");
                        gameManager.Exit(juego.jugador);
                        juego.dispose();
                        IniciaJuego ventana = new IniciaJuego();
                        ventana.setVisible(true);
                        this.stop();
                    } catch (RemoteException | NotBoundException ex) {
                        Logger.getLogger(IniciaJuego.class.getName()).log(Level.SEVERE, null, ex);
                    }    }
                    
                    
                }              
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(MonsterClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MonsterClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MonsterClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
