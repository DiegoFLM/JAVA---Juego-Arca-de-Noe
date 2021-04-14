/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 2: Arca de Noe.
 * Imágenes tomadas de: https://www.unobrain.com/juegos-de-memoria/elarcadenoe/
 */

package arcaDeNoe;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * Carta hereda de JButton, esta clase es la que representa las cartas del juego sobre
 * las cuales el jugador puede dar click. Las cartas pueden pasar de estar por el reverso a 
 * estar boca arriba (mostrando la imagen de un animal) y viceversa. Las cartas pueden desaparecer del juego.
 * Todas las cartas muestran la misma imagen cuando están por el reverso.**/

public class Carta extends JButton {

    //Atributos:

    int idCarta;
    boolean estaBocaArriba;
    boolean haSidoVista;    
    
    //Métodos:
    
        //Constructor:
    public Carta(int idCarta) {
    	/*idCarta es un atributo de cada carta que está asociado a su imagen, para que dos cartas muestren la misma imagen,
    	 * sencillamente se les debe asignar el mismo idCarta.*/
        this.idCarta = idCarta;
        
        //Al crear una carta, la misma se encuentra por el reverso.
        setIcon(new ImageIcon(new ImageIcon("src/images/" + "back" + ".png").getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));

        estaBocaArriba = false;
        haSidoVista = false;
        
        this.setBackground(Color.WHITE);
        this.setBorder(null);
        this.setFocusPainted(false);
    }
    
    
    
        //Si una carta está boca arriba (mostrando la imagen de un animal), la pone boca abajo y viceversa.
    public void voltear() {
    	if (estaBocaArriba) {
            estaBocaArriba = false;
            setIcon(new ImageIcon(new ImageIcon("src/images/" + "back" + ".png").getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
            haSidoVista = true;
    	} else {
            estaBocaArriba = true;
            setIcon(new ImageIcon(new ImageIcon("src/images/" + String.valueOf(idCarta) + ".png").getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
        }
    }
    
    /*Este método hace que la carta deje de ser visible, y el hecho de que idCarta valga -1 permite 
     * identificar las cartas deshabilitadas, las cuales no causan ningún efecto adicional en el juego.*/
    public void deshabilitar() {
    	idCarta = -1;
        setIcon(new ImageIcon(new ImageIcon("src/images/" + "transparent" + ".png").getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
    	estaBocaArriba = false;
        haSidoVista = false;
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

