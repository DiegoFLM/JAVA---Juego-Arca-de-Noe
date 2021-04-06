/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 2: Arca de Noe.
 * Imagenes tomadas de: https://www.unobrain.com/juegos-de-memoria/elarcadenoe/
 * 
 */

package arcaDeNoe;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrincipalArcaDeNoe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SelectorCartas selector = new SelectorCartas();
		Integer[] cartas = selector.NuevasCartas(16);
		/*System.out.printf("Cartas: ");
		for(int j = 0; j < cartas.length; j++) {
			System.out.printf(cartas[j].toString() + " ");
		}
		System.out.printf("\n");*/
		JFrame ventana;
		ventana = new JFrame();
		JPanel panel;
		panel = new JPanel();
		ventana.getContentPane();
		
		ImageIcon imagen;
		
		JButton boton;
		boton = new JButton("");
		imagen = new ImageIcon(new ImageIcon("src/images/" + "2" + ".png").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
		boton.setIcon(imagen);
		
		//boton.setSize(500, 500);
		
		Carta carta1, carta2, carta3;
		carta1 = new Carta(2);
		carta2 = new Carta(8);
		carta3 = new Carta(11);
		
		ventana.setLayout(new FlowLayout());
		panel.setLayout(new FlowLayout());
		
		
		panel.add(carta1);
		panel.add(carta2);
		panel.add(carta3);
		ventana.setVisible(true);
		ventana.setSize(600,600);
		
		
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setSize(400, 400);
	
		ventana.add(panel);
		ventana.pack();
		
		carta2.voltear();
		
		
	}

}
