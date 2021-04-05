/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 2: Arca de Noe.
 */

package arcaDeNoe;

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
	}

}
