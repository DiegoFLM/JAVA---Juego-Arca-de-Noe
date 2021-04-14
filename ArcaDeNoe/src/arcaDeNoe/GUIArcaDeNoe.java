/* Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 2: Arca de Noe.
 *Imágenes tomadas de: https://www.unobrain.com/juegos-de-memoria/elarcadenoe/
 */

package arcaDeNoe;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.util.*;

//import arcaDeNoe.GUIArcaDeNoe.Escucha;
import misComponentes.Titulos;


/*GUIArcaDeNoe hereda de JFrame, y hace uso de la clase Titulos que se encuentra en el proyecto MisComponentes.
 * Sus funciones son generar la interfaz gráfica que le permita al usuario jugar el juego empleando 
 * exclusivamente el mouse, y a la vez gestionar los eventos generados por el usuario para que el juego se comporte
 * de manera acorde a las acciones del usuario. Esta clase tiene en sus atributos un objeto de clase ControlArcaDeNoe, 
 * por medio del cual gestiona la lógica básica de estadísticas y generación de nuevos grupos de parejas de cartas en sus
 * respectivas posiciones.
 * */
public class GUIArcaDeNoe extends JFrame {
    
    private JPanel superior, opciones, inicio, zonaJuego, zonaCartas, puntosPartidaEnCurso, estadisticas;
    private CardLayout card;
    private GridBagConstraints constraints;
    private Escucha escucha;
    private ControlArcaDeNoe controlJuego;
    private SelectorCartas selector;
    private Carta c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24;
    private Carta[] arrayCartas, arrayTemp;
    private Integer[] cartas, parejaEncontrada, stats;
    private Carta[] cartasDisponibles;
    private Titulos tituloJuego, tituloPuntosPartidaEnCurso, tituloEstadisticas, tituloPuntos, tituloAciertos, tituloFallos, 
    				cantPuntos, cantAciertos, cantFallos;
    private JButton salir, nuevoJuego, abandonar, iniciarJuego;
    private ImageIcon imagenInicio;
    private JLabel labelImagenInicio, JLEstadisticas;
    private JTextField TFPuntosPartidaEnCurso;
    private Timer timerVerCartas;
    
    private int numCartasBocaArriba, numCartasEnJuego;

    
    
    
    
    
    //Constructor
    public GUIArcaDeNoe() {
        initGUI();
        
        this.setUndecorated(true);
        this.setBackground(new Color(255, 255, 25, 100));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    /*Inicializa componentes y muestra la imagen de inicio del juego. Los componentes correspondientes al menú de opciones y el título
     * del juego permanecen visibles todo el tiempo.*/
    public void initGUI() {
        this.getContentPane().setLayout(new GridBagLayout());;
        constraints = new GridBagConstraints();
        numCartasBocaArriba = 0;
        parejaEncontrada = new Integer[0];
        numCartasEnJuego = 4;
        arrayTemp = new Carta[0];
        
        //Creamos objetos
        SelectorCartas selector = new SelectorCartas();
        Integer[] cartas = selector.NuevasCartas(4);
        escucha = new Escucha();
        controlJuego = new ControlArcaDeNoe();
        stats = controlJuego.estadisticas(); // stats[0] = aciertos; stats[1] = fallos ; stats[2] = puntos 
        
        //set up GUIComponents
        tituloJuego = new Titulos("Arca de Noe", 30, new Color (0, 0, 0)); //RGB
        tituloEstadisticas = new Titulos("Estadísticas: ", 30, new Color (60, 120, 80));
        tituloPuntos = new Titulos("Puntos: ", 30, new Color (80, 100, 200)); 
        cantPuntos = new Titulos(String.valueOf(stats[2]), 30, new Color (80, 100, 200)); 
        tituloAciertos = new Titulos("Aciertos: ", 30, new Color (200, 40, 100));
        cantAciertos = new Titulos(String.valueOf(stats[0]), 30, new Color (200, 40, 100));
        tituloFallos = new Titulos("Fallos: ", 30, new Color (50, 100, 120));
        cantFallos = new Titulos(String.valueOf(stats[1]), 30, new Color (50, 100, 120));
        
        //Opciones
        opciones = new JPanel();
        salir = new JButton("Salir");
		salir.addActionListener(escucha);
		salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		abandonar = new JButton("Abandonar");
		abandonar.addActionListener(escucha);
		abandonar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nuevoJuego = new JButton("Nuevo Juego");
		nuevoJuego.addActionListener(escucha);
		nuevoJuego.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		opciones.setLayout(new GridLayout(1,3, 50, 0));
		opciones.add(salir);
		opciones.add(abandonar);
		opciones.add(nuevoJuego);
		
		constraints.gridx = 0;		
 		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(opciones, constraints);	

		
        //Inicio
		inicio = new JPanel();

		tituloJuego = new Titulos("Arca de Noé", 30, new Color(150, 50, 200));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(tituloJuego, constraints);
		
		
		//Zona de Juego 
		zonaJuego = new JPanel();
		zonaJuego.setLayout(new BorderLayout());
		
		puntosPartidaEnCurso = new JPanel();
		zonaCartas = new JPanel();
		
		tituloPuntosPartidaEnCurso = new Titulos("Puntos: ", 30, new Color(150, 50, 150));
	    TFPuntosPartidaEnCurso = new JTextField(String.valueOf(stats[2]));
		
	    puntosPartidaEnCurso.setLayout(new GridLayout(1,2, 80, 0));
	    puntosPartidaEnCurso.add(tituloPuntosPartidaEnCurso);
	    puntosPartidaEnCurso.add(TFPuntosPartidaEnCurso);
	    
		zonaJuego.add(puntosPartidaEnCurso, BorderLayout.NORTH);
		zonaJuego.add(zonaCartas, BorderLayout.CENTER);
		
		
		//Estadisticas
		estadisticas = new JPanel();
		estadisticas.setLayout(new GridBagLayout());;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		estadisticas.add(tituloEstadisticas, constraints);
		
		//Aciertos
        constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		estadisticas.add(tituloAciertos, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		estadisticas.add(cantAciertos, constraints);
		
		//Puntos
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		estadisticas.add(tituloPuntos, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		estadisticas.add(cantPuntos, constraints);
		
		//Fallos
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		estadisticas.add(tituloFallos, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		estadisticas.add(cantFallos, constraints);
		
		
		//Panel superior
		superior = new JPanel();
		
		card = new CardLayout(0, 0);
		superior.setLayout(card);
		superior.add("inicio", inicio);
		superior.add("zonaJuego", zonaJuego);
		superior.add("estadisticas", estadisticas);
		
		card.show(superior, "inicio");
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(superior, constraints);
		
		
		imagenInicio = new ImageIcon("src/imagenes/startingScreen.PNG");
		Image dabImage = imagenInicio.getImage();
		Image modifiedDabIcon = dabImage.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		imagenInicio = new ImageIcon(modifiedDabIcon);

		labelImagenInicio = new JLabel(new ImageIcon(new ImageIcon("src/images/" + "startingScreen" + ".png").getImage().getScaledInstance(700, 500, java.awt.Image.SCALE_SMOOTH)));
		inicio.add(labelImagenInicio);
	
		zonaCartas.setLayout(new GridLayout(2,2,20,20));
		
		/*El tiempo especificado para timerVerCartas corresponde al tiempo durante el cual serán visibles dos cartas que han sido destapadas
		 * antes de que desaparezcan, se vuelvan a poner por el reverso, o se pase a la siguiente ronda.*/
		timerVerCartas = new Timer(1000, escucha);
    }

    /*Reinicia los valores y componentes necesarios para iniciar un nuevo juego sin que los resultados o eventos de juegos
     * anteriores lo afecten. Muestra la cantidad de puntos que lleva el jugador, que en este momento son cero, y cuatro cartas
     * con las cuales inicia el juego. */
    private void iniciarJuego() {
    	timerVerCartas.stop();
		zonaCartas.removeAll();
		inicio.removeAll();
		zonaCartas.setLayout(new GridLayout(2, 2, 20, 20));
		
    	numCartasBocaArriba = 0;
    	
    	cartas = controlJuego.nuevoJuego(); //cartas es un Integer[]
    	
    	stats = controlJuego.estadisticas(); 
		tituloPuntosPartidaEnCurso.revalidate();
		tituloPuntosPartidaEnCurso.repaint();
		puntosPartidaEnCurso.removeAll();
		tituloPuntosPartidaEnCurso = new Titulos("Puntos: ", 30, new Color(150, 50, 150));
	    TFPuntosPartidaEnCurso = new JTextField(String.valueOf(stats[2]));
	    puntosPartidaEnCurso.add(tituloPuntosPartidaEnCurso);
	    puntosPartidaEnCurso.add(TFPuntosPartidaEnCurso);
    	
    	//Se crea el array de objetos tipo Carta con las imágenes especificadas por el array cartas.
    	arrayCartas = new Carta[controlJuego.getCantidadCartas()];
    	for (int j = 0; j < cartas.length; j++) {
			arrayCartas[j] = new Carta(cartas[j]);
			arrayCartas[j].addActionListener(escucha);
			arrayCartas[j].setCursor(new Cursor(Cursor.HAND_CURSOR));
			zonaCartas.add(arrayCartas[j]);
	  	}
    	
    	puntosPartidaEnCurso.revalidate();
    	puntosPartidaEnCurso.repaint();
    	zonaJuego.revalidate();
    	zonaJuego.repaint();
    	superior.revalidate();
		superior.repaint();
		
		this.pack();
		this.revalidate();
		this.repaint();
		this.setLocationRelativeTo(null);
    }
    
    
    /*Genera y muestra por el reverso un nuevo grupo de cartas en orden aleatorio, con dos cartas más que la ronda anterior, hasta un máximo de 12,
     * y reajusta el tamaño de la ventana. Muestra la misma información que iniciarJuego(), pero con una mayor cantidad de cartas. */
    private void nuevaRonda() {
    	zonaCartas.removeAll();
    	
    	numCartasBocaArriba = 0;
    	cartas = controlJuego.nuevaRonda();
    	arrayCartas = new Carta[controlJuego.getCantidadCartas()];
    	
    	if (controlJuego.getCantidadCartas() == 12) {
    		zonaCartas.setLayout(new GridLayout(3, 4, 20, 20));
    	}
    	
    	//Se crea el array de objetos tipo Carta con las imágenes especificadas por el array cartas.
    	for (int j = 0; j < cartas.length; j++) {
			arrayCartas[j] = new Carta(cartas[j]);
			arrayCartas[j].addActionListener(escucha);
			arrayCartas[j].setCursor(new Cursor(Cursor.HAND_CURSOR));
			zonaCartas.add(arrayCartas[j]);
		}
    	
    	stats = controlJuego.estadisticas();
    	tituloPuntosPartidaEnCurso.revalidate();
    	tituloPuntosPartidaEnCurso.repaint();
		puntosPartidaEnCurso.removeAll();
		tituloPuntosPartidaEnCurso = new Titulos("Puntos: ", 30, new Color(150, 50, 150));
		
	    TFPuntosPartidaEnCurso = new JTextField(String.valueOf(stats[2]));
	    puntosPartidaEnCurso.add(tituloPuntosPartidaEnCurso);
	    puntosPartidaEnCurso.add(TFPuntosPartidaEnCurso);
	    
	    superior.revalidate();
		superior.repaint();
    	this.pack();
    	this.setLocationRelativeTo(null);
    }
    
    /*Muestra el número de aciertos, puntos y fallos.*/
    private void mostrarEstadisticas() {
    	estadisticas.removeAll();
    	zonaCartas.removeAll();
    	inicio.removeAll();
 		card.show(superior, "estadisticas");

    	stats = controlJuego.estadisticas(); // stats[0] = aciertos; stats[1] = fallos ; stats[2] = puntos 
         
        cantPuntos = new Titulos(String.valueOf(stats[2]), 30, new Color (80, 100, 200)); 
        cantAciertos = new Titulos(String.valueOf(stats[0]), 30, new Color (200, 40, 100));
        cantFallos = new Titulos(String.valueOf(stats[1]), 30, new Color (50, 100, 120));
    	
    	//Titulo estadísticas
        constraints.gridx = 0;
 		constraints.gridy = 0;
 		constraints.gridwidth = 2;
 		constraints.gridheight = 1;
 		constraints.fill = GridBagConstraints.BOTH;
 		constraints.anchor = GridBagConstraints.CENTER;
 		estadisticas.add(tituloEstadisticas, constraints);
 		
 		//Aciertos
         constraints.gridx = 0;
 		constraints.gridy = 1;
 		constraints.gridwidth = 1;
 		constraints.gridheight = 1;
 		constraints.fill = GridBagConstraints.BOTH;
 		constraints.anchor = GridBagConstraints.CENTER;
 		estadisticas.add(tituloAciertos, constraints);
 		
 		constraints.gridx = 1;
 		constraints.gridy = 1;
 		constraints.gridwidth = 1;
 		constraints.gridheight = 1;
 		constraints.fill = GridBagConstraints.BOTH;
 		constraints.anchor = GridBagConstraints.CENTER;
 		estadisticas.add(cantAciertos, constraints);
 		
 		
 		
 		//Puntos
 		constraints.gridx = 0;
 		constraints.gridy = 2;
 		constraints.gridwidth = 1;
 		constraints.gridheight = 1;
 		constraints.fill = GridBagConstraints.BOTH;
 		constraints.anchor = GridBagConstraints.CENTER;
 		estadisticas.add(tituloPuntos, constraints);
 		
 		constraints.gridx = 1;
 		constraints.gridy = 2;
 		constraints.gridwidth = 1;
 		constraints.gridheight = 1;
 		constraints.fill = GridBagConstraints.BOTH;
 		constraints.anchor = GridBagConstraints.CENTER;
 		estadisticas.add(cantPuntos, constraints);
 		
 		//Fallos
 		constraints.gridx = 0;
 		constraints.gridy = 3;
 		constraints.gridwidth = 1;
 		constraints.gridheight = 1;
 		constraints.fill = GridBagConstraints.BOTH;
 		constraints.anchor = GridBagConstraints.CENTER;
 		estadisticas.add(tituloFallos, constraints);
 		
 		constraints.gridx = 1;
 		constraints.gridy = 3;
 		constraints.gridwidth = 1;
 		constraints.gridheight = 1;
 		constraints.fill = GridBagConstraints.BOTH;
 		constraints.anchor = GridBagConstraints.CENTER;
 		estadisticas.add(cantFallos, constraints);
    	
 		estadisticas.revalidate();
 		estadisticas.repaint();
 		this.pack();
    	this.setLocationRelativeTo(null);
 		
    }
    
    
    
    
    private class Escucha implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // TODO Auto-generated method stub
        	
        	//cierra el juego
            if (actionEvent.getSource() == salir) {
                System.exit(0);
                
            //Inicia un nuevo juego
            }else if (actionEvent.getSource() == nuevoJuego) {
                iniciarJuego();
                card.show(superior, "zonaJuego");
                superior.revalidate();
                superior.repaint();
                
                //Se cumplió el tiempo del timer.
            } else if (actionEvent.getSource() == timerVerCartas) {
            	/*Este evento ocurre cuando se acaba el tiempo disponible para visualizar una pareja de cartas antes de que
            	 * desaparezcan, se vuelvan a poner por el reverso, o se pase a la siguiente ronda.*/
            	timerVerCartas.stop();
            	
            	/*Si el jugador acertó, parejaEncontrada guarda las posiciones de las cartas en arrayCartas que deben ser deshabilitadas.*/
            	if (parejaEncontrada.length == 2) {
            		arrayCartas[parejaEncontrada[0]].deshabilitar();
            		arrayCartas[parejaEncontrada[1]].deshabilitar();
            	}

            	//Pasado el tiempo correspondiente, las cartas que estén boca arriba se vuelven a poner boca abajo.
            	numCartasBocaArriba = 0;
            	for (int j = 0; j < arrayCartas.length; j++) {
            		if (arrayCartas[j].estaBocaArriba) {
            			arrayCartas[j].voltear();
            		}
            	}
            
            	//Se cuentan las cartas que siguen activas en el juego.
            	numCartasEnJuego = 0;
				for (int a = 0; a < arrayCartas.length; a++) {
					if (arrayCartas[a].getIdCarta() >= 0) {
						numCartasEnJuego ++;
					}
				}
				
				/*Si ya no hay más cartas activas, significa que el jugador emparejó todas las cartas, y se debe pasar
				 *a la siguiente ronda.*/
				if (numCartasEnJuego == 0) {
					nuevaRonda();
				}
				
				
				//Abandonar hace que se muestren las estadísticas. Se puede abandonar en cualquier momento.
            } else if (actionEvent.getSource() == abandonar) {
            	mostrarEstadisticas();
            
            /*Si hau un actionEvent distinto a los anteriores, necesariamente se debe a que se hizo
             * click en alguna carta:*/
            } else {
            	for (int j = 0; j < arrayCartas.length; j++) {
            		
            		/*Se averigua sobre qué carta se hizo click: arrayCartas[j], y se revisa que esté habilitada,
            		  si la carta está deshabilitada, no se hace nada.*/
            		if (actionEvent.getSource() == arrayCartas[j] & (arrayCartas[j].getIdCarta() >= 0)) {
            			//arrayCartas[j] de aquí en adelante es la carta sobre la cual se dió click.
            			
            			if (arrayCartas[j].estaBocaArriba) {//El jugador dio click a una carta que estaba boca arriba.	
            				//No hacer nada
            			} else {//Se hizo click sobre una carta que estaba boca abajo. Se actúa según el número de cartas boca arriba previamente.
            				switch (numCartasBocaArriba){
            				
            					case 0:
            						arrayCartas[j].voltear();
            						numCartasBocaArriba ++;
            						break;
            						
            					/*
            					 *A continuación se verifica si hubo acierto o error por parte del jugador */	
            					case 1:
            						arrayCartas[j].voltear();
            						numCartasBocaArriba ++;
            						timerVerCartas.start();
            						
            						/*Se busca cuál es la carta que previamente estaba boca arriba, esta carta es
            						 * arrayCartas[k]*/
            						for (int k = 0; k < arrayCartas.length; k++) {
            							if (arrayCartas[k].estaBocaArriba & (arrayCartas[k].getIdCarta() >= 0) & (j != k)) { //De aquí en adelante j != k
            								
            								/*Si los idCarta son iguales, significa que hubo un acierto*/
            								if (arrayCartas[k].getIdCarta() == arrayCartas[j].getIdCarta()) {
            									controlJuego.acierto();
            									stats = controlJuego.estadisticas();
            									tituloPuntosPartidaEnCurso.revalidate();
            									tituloPuntosPartidaEnCurso.repaint();
            									puntosPartidaEnCurso.removeAll();
            									tituloPuntosPartidaEnCurso = new Titulos("Puntos: ", 30, new Color(150, 50, 150));
            								    TFPuntosPartidaEnCurso = new JTextField(String.valueOf(stats[2]));
            								    puntosPartidaEnCurso.add(tituloPuntosPartidaEnCurso);
            								    puntosPartidaEnCurso.add(TFPuntosPartidaEnCurso);
            									
            									parejaEncontrada = new Integer[2];
            									parejaEncontrada[0] = j;
            									parejaEncontrada[1] = k;
            									
            									
            								/*Si no hubo acierto y alguna de las cartas ha sido vista previamente, hubo un fallo*/	
            								} else if (arrayCartas[j].haSidoVista || arrayCartas[k].haSidoVista) {
            										controlJuego.fallo();
            										parejaEncontrada = new Integer[0];
            										
            										stats = controlJuego.estadisticas();
            										tituloPuntosPartidaEnCurso.revalidate();
            										tituloPuntosPartidaEnCurso.repaint();
                									puntosPartidaEnCurso.removeAll();
                									tituloPuntosPartidaEnCurso = new Titulos("Puntos: ", 30, new Color(150, 50, 150));
                								    TFPuntosPartidaEnCurso = new JTextField(String.valueOf(stats[2]));
                								    puntosPartidaEnCurso.add(tituloPuntosPartidaEnCurso);
                								    puntosPartidaEnCurso.add(TFPuntosPartidaEnCurso);
            									
            								} else {
            									parejaEncontrada = new Integer[0];
            								}
            							}
            						}
            						
            						/*Si hay dos cartas boca arriba, el jugador debe esperar a que el timerVerCartas se detenga 
            						 * para poder seguir jugando, los clicks sobre cartas en ese lapso de tiempo no tienen efecto.	
            						 */
            						break;
            			   }
            			}
            		}
            	}
            }
        }
    }
}

