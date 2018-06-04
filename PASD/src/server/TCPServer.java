package server;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JGUTIERRGARC
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//Lee los mensajes de los usuarios y valida los golpes 
public class TCPServer extends Thread {
    
    JuegoM juego;
    int TCPP;

    public TCPServer(JuegoM game, int tcpPort) {
        this.juego = game;
        this.TCPP = tcpPort;
    }   
    
    @Override
    public void run() {
	try{
            ServerSocket listenSocket = new ServerSocket(TCPP);
            while(true) {
                Socket clientSocket = listenSocket.accept();  
                Connection c = new Connection(clientSocket, juego);
                c.start();
            }
	} catch(IOException e) {System.out.println("Listen :"+ e.getMessage());}
    }
   
}

class Connection extends Thread {
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;
        JuegoM game; 
	public Connection (Socket aClientSocket, JuegoM game) {
	    try {
		clientSocket = aClientSocket;
                this.game = game;
		out =new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
	     } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
	}
        
        @Override
	public void run(){
	    try {
                Respuesta data = (Respuesta) in.readObject();
                String username = data.nombreJugador;
                System.out.println("Message received from: " + username +" " +data.pos);
                if(data.n == game.ronda  && data.pos == game.pos) {
                    game.ValidaGolpe(username);
                }
	    } 
            catch(EOFException e) {
                System.out.println("EOF:"+e.getMessage());
	    } 
            catch(IOException e) {
                System.out.println("IO:"+e.getMessage());
	    } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                try {
                    clientSocket.close();
                } catch (IOException e){
                    System.out.println(e);
                }
                }
            }
}
