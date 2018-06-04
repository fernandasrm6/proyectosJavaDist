/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaces.Compute;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author fernandasramirezm
 */
public class MonsterClient extends Thread{
    JButton botones[];
    RejillaJuego juego;
    static Integer round;
     String multicastAddress;
    int multicastPort;
    
    public MonsterClient(String m, int mp, JButton[] b, RejillaJuego r){
        multicastAddress=m;
        multicastPort=mp;
        botones=b;
        juego= r;
    }
    @Override
    public void run() {
        MulticastSocket socket = null;
        try {
            InetAddress group = InetAddress.getByName(multicastAddress);
            socket = new MulticastSocket(multicastPort);
            socket.joinGroup(group);
            while(true){
                byte[] buffer = new byte[100];
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                socket.receive(messageIn);
                String roundInfo[] = new String(messageIn.getData()).trim().split(" ");
                
                if (roundInfo[0].equals("n")){ // 1-ronda 2-monstruo
                    round = Integer.parseInt(roundInfo[1]);
                    int monster = Integer.parseInt(roundInfo[2]);
                    ImageIcon imgNoMonstruo = new ImageIcon(getClass().getResource("/client.IMAGENES/A.gif"));
                    ImageIcon imgMonstruo = new ImageIcon(getClass().getResource("/client.IMAGENES/B.gif"));
                    ImageIcon img = new ImageIcon(imgMonstruo.getImage().getScaledInstance(imgNoMonstruo.getIconHeight(), imgNoMonstruo.getIconWidth(), 0));
                    botones[monster].setIcon(img);  
                    Thread.sleep(2000);
                    botones[monster].setIcon(imgNoMonstruo);
                }
                
                if (roundInfo[0].equals("ganador")) {
                    System.out.println(roundInfo[1]);
                    if (roundInfo[1].equals(juego.jugador)) {
                        JOptionPane.showMessageDialog(juego, "Felicidades!", "Eres el ganador ".concat(juego.jugador), 1);
                    } else {
                        JOptionPane.showMessageDialog(juego, "Perdiste!", "Lo sentimos ".concat(juego.jugador), 1);
                    }
                    
                    
                   System.setProperty("java.security.policy","/Users/fernandasramirezm/NetBeansProjects/PAlpha/src/client/client.policy");

                    if (System.getSecurityManager() == null) {
                        System.setSecurityManager(new SecurityManager());
                    }

                    String name = "JuegoServer";


                    Registry registry;
                    try {
                        registry = LocateRegistry.getRegistry("localhost"); // server's ip address
                        Compute gameManager = (Compute) registry.lookup(name);
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
