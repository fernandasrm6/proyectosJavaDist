/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarestfulclient;

import webserviceclients.NewJerseyClient;

/**
 *
 * @author fernandasramirezm
 */
public class JavaRESTfulClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         NewJerseyClient client = new NewJerseyClient();
         System.out.println(client.getHtml());
         client.putHtml("iMPTIMIR EN LA CONSOLA SERVIDOR");
         // do whatever with response
         client.close();
    }
    
}
