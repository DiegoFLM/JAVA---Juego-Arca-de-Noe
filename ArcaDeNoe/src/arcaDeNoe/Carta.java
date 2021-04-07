/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 2: Arca de Noe.
 */

package arcaDeNoe;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Carta extends JButton {

    //Atributos:

    int idCarta;
    boolean estaBocaArriba;
    boolean haSidoVista;    
    
    //Métodos:
    
        //Constructor:
    public Carta(int idCarta) {
        this.idCarta = idCarta;
        
        setIcon(new ImageIcon(new ImageIcon("src/images/" + "back" + ".png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
        estaBocaArriba = false;
        haSidoVista = false;
        
        this.setBackground(Color.WHITE);
        this.setBorder(null);
        this.setFocusPainted(false);
    }
    
    public Carta (String string) {
    	this.idCarta = -1;
        
        setIcon(new ImageIcon(new ImageIcon("src/images/" + string + ".png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
        estaBocaArriba = false;
        haSidoVista = false;
        
        this.setBackground(Color.WHITE);
        this.setBorder(null);
        this.setFocusPainted(false);
    }
    
    
        //Si una carta está boca arriba, la pone boca abajo y viceversa.
    public void voltear() {
        if (estaBocaArriba) {
            estaBocaArriba = false;
            setIcon(new ImageIcon(new ImageIcon("src/images/" + "back" + ".png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
            
        } else {
            estaBocaArriba = true;
            setIcon(new ImageIcon(new ImageIcon("src/images/" + String.valueOf(idCarta) + ".png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
            
        }
    }
    
    public int getIdCarta() {
        return idCarta;
    }
    
    public boolean getEstaBocaArriba(){
        return estaBocaArriba;
    }
    
    public boolean getHaSidoVista() {
        return haSidoVista;
    }

    public void setIdCarta(int id) {
        idCarta = id;
    }

}

