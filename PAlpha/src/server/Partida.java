/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author fernandasramirezm
 */
public class Partida {
    int partida=0;
    int cuantos=5; //a los cuantos golpes se gana
    int pmonstruo= 1;
    boolean gameOver=false;
    boolean valido=false;
    String ganador;
    HashMap<String,Integer> participantes = new HashMap(); //Guarda el nombre y los golpes del jugador
  HashMap<String,Boolean> conectados = new HashMap();
    //genera el aleatorio de la posicion 
    public int generarMonstruo(){
        Random rand= new Random();
        pmonstruo= rand.nextInt(13);
        return pmonstruo;
    }
    public boolean add(String nombre){
        if(!participantes.containsKey(nombre)){
            participantes.put(nombre, 0); 
            return true;
        }
        return false;
    }
    public void validar(String nombre){
        if(!valido){
            int golpes = participantes.get(nombre)+1;
            if(golpes == cuantos && !gameOver){
                gameOver=true;
                ganador= nombre;
            }
            participantes.put(nombre, golpes);
            valido = true;
            
        }
    }
    public boolean newGame(){
        partida=0;
        participantes.clear();
        gameOver=false;
        valido=false;
        return false;  
    }
    
    public void nextRound(){
        partida ++;
        valido=false;
    }
}
