/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.Serializable;

/**
 *
 * @author fernandasramirezm
 */
public class GolpeaMnsj implements Serializable {
    int num;
    int monstruo;
    String nombreJugador;    
    
        public GolpeaMnsj(int n, int mns, String name) {
        num = n;
        monstruo = mns;
        nombreJugador = name;
    }

    public int getNum() {
        return num;
    }

    public int getMonstruo() {
        return monstruo;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }
        
}
