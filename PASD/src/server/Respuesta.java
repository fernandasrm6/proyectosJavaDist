/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;
/**
 *
 * @author fernandasramirezm
 */

//OBJETO QUE RECIBE EL SERVIDOR
public class Respuesta implements Serializable{
    
    int n;
    int pos;
    String nombreJugador;

    public Respuesta(int n, int monstruo, String nombreJugador) {
        this.n = n;
        this.pos = monstruo;
        this.nombreJugador = nombreJugador;
    }   
    
}
