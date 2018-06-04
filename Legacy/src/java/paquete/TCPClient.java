/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.net.*;
import java.io.*;

public class TCPClient {

    //función que manda el string de parte del cliente 
    public String sendData(String dtm) {
        
	Socket s = null;
         String data = "";
	    try {
	    	int serverPort = 7896;
	   	
                s = new Socket("localhost", serverPort);    
             //   s = new Socket("127.0.0.1", serverPort);    
		DataInputStream in = new DataInputStream( s.getInputStream());
		DataOutputStream out =
			new DataOutputStream( s.getOutputStream());
		out.writeUTF(dtm);        	// UTF is a string encoding 
                
		data = in.readUTF();	      
                System.out.println("Received: "+ data) ;      
       	    } 
            catch (UnknownHostException e) {
		System.out.println("Sock:"+e.getMessage()); 
	    }
            catch (EOFException e) {
                System.out.println("EOF:"+e.getMessage());
    	    } 
            catch (IOException e) {
                System.out.println("IO:"+e.getMessage());
            } finally {
                if(s!=null) 
                    try {
                        s.close();
                    } catch (IOException e){
                    System.out.println("close:"+e.getMessage());}
                    }
        return data;
            }
}

