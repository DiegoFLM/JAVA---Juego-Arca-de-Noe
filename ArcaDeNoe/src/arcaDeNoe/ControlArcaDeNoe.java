/* Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 2: Arca de Noe.
 * Imágenes tomadas de: https://www.unobrain.com/juegos-de-memoria/elarcadenoe/
 */

package arcaDeNoe;

/*ControlArcaDeNoe se encarga de gestionar el aumento de cartas en juego y
 * solicitarle a SelectorCartas generar arreglos aleatorios de números que permitirán asignar las imágenes a las cartas
 * en cada ronda. Adicionalmente, esta clase gestiona las estadísticas del juego, las cuales cambian 
 * según el jugador acierte o falle, o si se inicia un nuevo juego.*/
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
    
    //Constructor
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
    
    /*Reestablece los valores necesarios para iniciar un nuevo juego.*/    
    public Integer[] nuevoJuego() {        
        ronda = 1;
        cantidadCartas = cantidadInicialCartas;
        puntos = 0;
        aciertos = 0;
        fallos = 0;
        cartas = selector.NuevasCartas(4);
        return cartas;
    }
    
    /*nuevaRonda() genera nuevas cartas sin alterar los valores de puntos, aciertos y fallos. 
     * También aumenta en 2 la cantidad de cartas, hasta un máximo de 12.*/
    public Integer[] nuevaRonda() {
        if (cantidadCartas == 12) {
            //cantidadCartas permanece igual
        } else {
            cantidadCartas = cantidadCartas + 2;
        }
        cartas = selector.NuevasCartas(cantidadCartas);
        return cartas;
    }
    
    /*Aumenta el punto y el acierto correspondiente, y retorna los puntos actualizados*/
    public void acierto() {
        aciertos++;
        puntos++;
    }
    
    /*Resta el punto y aumenta el fallo correspondiente*/
    public void fallo() {
        fallos++;
        //No puede tenerse una cantidad negativa de puntos.
        if (puntos == 0) {
            //Permanece en cero puntos.
        } else {
            puntos--;
        }
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


