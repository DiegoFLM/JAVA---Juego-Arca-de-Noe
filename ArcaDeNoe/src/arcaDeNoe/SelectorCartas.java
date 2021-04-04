/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 2: Arca de Noe.
 */
package arcaDeNoe;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class SelectorCartas {
	//Constantes:
	private int numMaxCartasDiferentes;
	
	//Variables:
	private Integer[] cartas;
	private Random aleatorio;
	private Integer[] cartasNoRepetidas;
	Integer[] cartasRepetidas; 
	
	public SelectorCartas() {
		numMaxCartasDiferentes = 16;
		aleatorio = new Random();
	}
	
	
	
	/*Recibe un entero numCartas, y devuelve un arreglo de enteros de tamaño numCartas, en el cual cada entero aparece exactamente dos veces. 
	 * Cara entero representa una carta.*/
	public Integer[] NuevasCartas(int numCartas) {
		cartas = new Integer[numCartas];
		Set<Integer> setCartas = new LinkedHashSet<Integer>();
		
		while (setCartas.size() < (numCartas / 2)) {
			setCartas.add(aleatorio.nextInt(numMaxCartasDiferentes)); //Se genera un set aleatorio de cartas no repetidas.
		}
		cartasNoRepetidas = setCartas.toArray(new Integer[setCartas.size()]);
		//System.out.printf("\n" + setCartas.size() + "\n");
		
		
		for(int g = 0; g < 2; g++) {		//Se genera un array cartas en el que cada carta aparece dos veces.
			for (int h = 0; h < cartasNoRepetidas.length; h++) {		
				cartas[(g * cartasNoRepetidas.length) + h] = cartasNoRepetidas[h];
			}
		}
		/*System.out.printf("\ncartasNoRandom.length: " + cartas.length + "\n");
		System.out.printf("CartasNoRandom: ");
		for(int j = 0; j < cartas.length; j++) {
			System.out.printf(" " + cartas[j].toString());
		}
		System.out.printf("\n");*/
		
		for (int j = 0; j < (2 * cartasNoRepetidas.length); j++) {  //Se reordena de forma aleatoria el array cartas.
			int b;
			int k =aleatorio.nextInt(2 * cartasNoRepetidas.length);
			if (j!=k) {
				b = cartas[j];
				cartas[j] =  cartas[k];
				cartas[k] = b;
			}
		}

		return cartas;
	}
	
	
	
	
}
