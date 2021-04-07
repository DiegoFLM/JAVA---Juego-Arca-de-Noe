/* Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 2: Arca de Noe.
 */

package arcaDeNoe;


public class ControlArcaDeNoe {
    //Atributos:
        //Constantes:
    private SelectorCartas selector;
    private int cantidadInicialCartas;
    
        //Variables:
    private Integer[] cartas;
    private int ronda;
    private int cantidadCartas;
    private int puntos;        //la variable puntos siempre es mayor o igual a cero.
    private int aciertos;
    private int fallos;
    private Integer[] estadisticas;

    
    
    //Metodos:
    public ControlArcaDeNoe() {
        selector = new SelectorCartas();
        cantidadInicialCartas = 4;
        
        ronda = 1;
        cantidadCartas = cantidadInicialCartas;
        puntos = 0;        
        aciertos = 0;
        fallos = 0;
        estadisticas = new Integer[3];
    }
    
    /*Reestablece los valores necesarios para iniciar un nuevo juego. No es necesario acudir a este método la primera
     * vez que se juega tras iniciar la ejecución del programa.*/    
    public Integer[] nuevoJuego() {        
        ronda = 1;
        cantidadCartas = cantidadInicialCartas;
        puntos = 0;
        aciertos = 0;
        fallos = 0;
        this.nuevaRonda();
        return cartas;
    }
    
    /*Nueva ronda genera nuevas cartas sin alterar los valores de puntos, aciertos y fallos.*/
    public Integer[] nuevaRonda() {
        if (cantidadCartas == 12) {
            //cantidadCartas permanece igual
        } else {
            cantidadCartas = cantidadCartas + 2;
        }
        cartas = selector.NuevasCartas(cantidadCartas);
        return cartas;
    }
    
    /*Aumenta el punto y el aciertos correspondiente, y retorna los puntos actualizados*/
    public int acierto() {
        aciertos++;
        puntos++;
        return puntos;
    }
    
    /*Resta el puntos y el fallo correspondiente, y retorna los puntos actualizados*/
    public int fallo() {
        fallos++;
        
        if (puntos == 0) {
            //Permanece en cero puntos.
        } else {
            puntos--;
        }
        
        return puntos;
    }
    
    
    /*Retorna un arreglo de enteros con las estadísticas del juego en un orden concreto: aciertos, fallos, puntos.*/
    public Integer [] estadisticas() {
        estadisticas = new Integer[] {aciertos, fallos, puntos};
        return estadisticas;
    }
    
    
    
    
    
    public int getCantidadCartas() {
        return cantidadCartas;
    }
    
    public int getPuntos() {
        return puntos;
    }
    
    public int getRonda() {
        return ronda;
    }
    

}


