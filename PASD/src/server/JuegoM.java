package server;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.HashMap;
import java.util.Random;
/**
 *
 * @author fernandasramirezm
 */
//Este servidor incluye el de TCP y multicast 
public class JuegoM {
    
    int ronda=0;
    int juegoGanado=5;
    int pos = 1;
    boolean terminado = false;
    String ganador;
    boolean valido = false;
    HashMap<String,Integer> usuarios = new HashMap(); 
    HashMap<String,Boolean> conectados = new HashMap(); 
    
    public JuegoM() {
    }
    
    
    boolean reset(){
        ronda = 0;
        usuarios.clear();
        terminado = false;
        valido = false;
        return false;
    }
    
    void nextRound(){
        ronda++;
        valido = false;
    }
    
    int generaMonstruo(){
        Random rand = new Random();
        int num = rand.nextInt(12);
        pos = num;
        return num;
    }
    
    
    boolean agregaParticipante(String jugador){
        if(!usuarios.containsKey(jugador)){
            usuarios.put(jugador, 0);
            return true;
        }
        return false;
    }
    
    
    
    void ValidaGolpe(String jugador){
        if (!valido) {
            int p = usuarios.get(jugador) + 1;
            System.out.println("Usuario " +jugador +" tiene puntos: " +p);
            if (p==juegoGanado && !terminado) {
                terminado = true;
                ganador = jugador;
                return;
            }            
            usuarios.put(jugador, p);
            valido = true;
        }
    }
}
