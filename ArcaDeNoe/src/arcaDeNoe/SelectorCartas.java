/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 2: Arca de Noe.
 * Imágenes tomadas de: https://www.unobrain.com/juegos-de-memoria/elarcadenoe/
 */
package arcaDeNoe;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/*SelectorCartas se encarga de generar un arreglo de enteros equivalente a un nuevo grupo de cartas para jugar,
 * garantizando que cada número aparezca en el arreglo exactamente dos veces.*/
public class SelectorCartas {
    //Constantes:
    private int numMaxCartasDiferentes;
    
    //Variables:
    private Integer[] cartas;
    private Random aleatorio;
    private Integer[] cartasNoRepetidas;
    Integer[] cartasRepetidas;
    
    public SelectorCartas() {
        numMaxCartasDiferentes = 12;
        aleatorio = new Random();
    }
    
    
    
    /*Recibe un entero numCartas, y devuelve un arreglo de enteros de tamaño numCartas, en el cual cada entero aparece exactamente dos veces.
     * Cada entero representa una carta.*/
    public Integer[] NuevasCartas(int numCartas) {
        cartas = new Integer[numCartas];
        Set<Integer> setCartas = new LinkedHashSet<Integer>();
        
        while (setCartas.size() < (numCartas / 2)) {
            setCartas.add(aleatorio.nextInt(numMaxCartasDiferentes)); //Se genera un set aleatorio de enteros no repetidos.
        }
        cartasNoRepetidas = setCartas.toArray(new Integer[setCartas.size()]);
        
        /*Se genera un array de enteros en el que cada entero aparece dos veces. El problema es que la secuencia de la primera 
         * mitad del arreglo resultante se repite en la segunda mitad del mismo.*/
        for(int g = 0; g < 2; g++) {        
            for (int h = 0; h < cartasNoRepetidas.length; h++) {        
                cartas[(g * cartasNoRepetidas.length) + h] = cartasNoRepetidas[h];
            }
        }
        
      /*Se reordena de forma aleatoria el array para que la secuencia de la primera mitad no se repita.*/
        for (int j = 0; j < (2 * cartasNoRepetidas.length); j++) {  
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

