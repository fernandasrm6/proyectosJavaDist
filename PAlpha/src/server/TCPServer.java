package server;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JGUTIERRGARC
 */

import interfaces.GolpeaMnsj;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCPServer extends Thread {
    Partida miPartida;
    int puerto;
    
    
    public TCPServer(Partida p, int port){
        miPartida= p;
        puerto = port;
    }
    @Override
    public void run() {
	try{
            ServerSocket listenSocket = new ServerSocket(puerto);
            while(true) {
                Socket clientSocket = listenSocket.accept();  
                Connection c = new Connection(clientSocket, miPartida);
                c.start();
            }
	} catch(IOException e) {System.out.println("Listen :"+ e.getMessage());}
    }
    
    
   
}

class Connection extends Thread {
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;
         Partida miPartida;
	public Connection (Socket aClientSocket, Partida p) {
	    try {
		clientSocket = aClientSocket;
                miPartida = p;
                
		in = new ObjectInputStream(clientSocket.getInputStream());
		out =new ObjectOutputStream(clientSocket.getOutputStream());
	     } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
	}
        
        @Override
	public void run(){

	    try {			                 // an echo server
		GolpeaMnsj data = (GolpeaMnsj) in.readObject();
                System.out.println("Message received from jugador: " + data.getNombreJugador()+ "| pos: "+data.getMonstruo());
                
                if(data.getNum() == miPartida.partida && data.getMonstruo() == miPartida.pmonstruo){
                    miPartida.validar(data.getNombreJugador());
                }
	    } 
            catch(EOFException e) {
                System.out.println("EOF:"+e.getMessage());
	    } 
            catch(IOException e) {
                System.out.println("IO:"+e.getMessage());
	    } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e){
                    System.out.println(e);
                }
                }
            }
}

