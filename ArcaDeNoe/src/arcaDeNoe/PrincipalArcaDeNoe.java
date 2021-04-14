/* Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 2: Arca de Noe.
 *Imágenes tomadas de: https://www.unobrain.com/juegos-de-memoria/elarcadenoe/
 */

package arcaDeNoe;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import atentoYRapido.VistaGUIAtentoYRapido;

import java.awt.EventQueue;

/*Clase principal que crea el objeto de clase GUIArcadeNoe.*/
public class PrincipalArcaDeNoe {

    public static void main(String[] args) {
       
    	EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//VistaGUICraps myWindow = new VistaGUICraps(); 	
				GUIArcaDeNoe myVista = new GUIArcaDeNoe();
			}
		});
    }
}


