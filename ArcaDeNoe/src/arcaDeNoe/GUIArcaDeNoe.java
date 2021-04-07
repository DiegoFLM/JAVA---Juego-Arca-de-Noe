/* Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Miniproyecto 2: Arca de Noe.
 *
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

//import arcaDeNoe.GUIArcaDeNoe.Escucha;
import misComponentes.Titulos;

public class GUIArcaDeNoe extends JFrame {
    
    private JPanel superior, opciones, inicio, zonaJuego, zonaCartas, puntosPartidaEnCurso, estadisticas;
    private CardLayout card;
    private GridBagConstraints constraints;
    private Escucha escucha;
    
    private ControlArcaDeNoe controlJuego;
    private SelectorCartas selector;
    private Carta c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24;
    private Carta[] arrayCartas;
    private Integer[] cartas;
    private Carta[] cartasDisponibles;
    private Titulos tituloJuego, tituloEstadisticas;
    private JButton salir, nuevoJuego, abandonar, iniciarJuego;
    private ImageIcon imagenInicio;
    private JLabel labelImagenInicio, JLPuntosPartidaEnCurso;
    private JTextField TFPuntosPartidaEnCurso;
    
    private Timer timer;

    
    public GUIArcaDeNoe() {
        initGUI();
        
        this.setUndecorated(true);
        this.setBackground(new Color(255, 255, 25, 100));
        this.pack();
        //this.setSize(310, 180);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void initGUI() {
        this.getContentPane().setLayout(new GridBagLayout());;
        constraints = new GridBagConstraints();

        //Creamos objetos
        SelectorCartas selector = new SelectorCartas();
        Integer[] cartas = selector.NuevasCartas(4);
        escucha = new Escucha();
        controlJuego = new ControlArcaDeNoe();
        
        //set up GUIComponents
        tituloJuego = new Titulos("Arca de Noe", 30, new Color (0, 0, 0)); //RGB
        tituloEstadisticas = new Titulos("Estadisticas: ", 30, new Color (50, 200, 100));
        
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
 		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(opciones, constraints);	

		
        //Inicio
		inicio = new JPanel();
		
		//Zona de Juego 
		zonaJuego = new JPanel();
		zonaJuego.setLayout(new BorderLayout());
		
		puntosPartidaEnCurso = new JPanel();
		zonaCartas = new JPanel();
		

		JLPuntosPartidaEnCurso = new JLabel("Puntos: ");
	    TFPuntosPartidaEnCurso = new JTextField(String.valueOf(controlJuego.estadisticas()[2]));
		
	    puntosPartidaEnCurso.setLayout(new GridLayout(1,2, 80, 0));
	    puntosPartidaEnCurso.add(JLPuntosPartidaEnCurso);
	    puntosPartidaEnCurso.add(TFPuntosPartidaEnCurso);
	    
	    
		zonaJuego.add(puntosPartidaEnCurso, BorderLayout.NORTH);
		zonaJuego.add(zonaCartas, BorderLayout.CENTER);
		
		
		
		
		//Estadisticas
		estadisticas = new JPanel();
		
		//Panel superior
		superior = new JPanel();
		
		card = new CardLayout(0, 0);
		superior.setLayout(card);
		superior.add("inicio", inicio);
		superior.add("zonaJuego", zonaJuego);
		superior.add("estadisticas", estadisticas);
		
		card.show(superior, "inicio");
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(superior, constraints);
		
		
		//Componentes gráficos
		imagenInicio = new ImageIcon("src/imagenes/imagenInicio.png");
		Image dabImage = imagenInicio.getImage();
		Image modifiedDabIcon = dabImage.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
		imagenInicio = new ImageIcon(modifiedDabIcon);
		labelImagenInicio = new JLabel(imagenInicio);

		inicio.add(labelImagenInicio);
	
		
		//Se genera un arreglo con las cartas disponibles para el juego con dos cartas en el arreglo por cada imagen.
		

		/*
		 * 
		 * 
		 * Aquí se usará GridBagLayout para zonaCartas
		 * 
		 * */
		zonaCartas.setLayout(new GridBagLayout());
		
		arrayCartas = new Carta[] {c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24};
		
		cartas = controlJuego.nuevoJuego(); //cartas es un Integer[]
		
		/*
		//Se inicializan todas las cartas como transparentes
		for (int j = 0; j < arrayCartas.length; j++) {
			arrayCartas[j] = new Carta("transparent");
		}*/
		
		
		for (int j = 0; j < cartas.length; j++) {
			arrayCartas[j] = new Carta(cartas[j]);
			arrayCartas[j].addActionListener(escucha);
			arrayCartas[j].setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		timer = new Timer(1500, escucha);
    }

    
    private void iniciarJuego() {
    	
    }
    
    
    
    
    private class Escucha implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // TODO Auto-generated method stub
            if (actionEvent.getSource() == salir) {
                System.exit(0);
            }else if (actionEvent.getSource() == nuevoJuego) {
                System.out.println("nj");
                iniciarJuego();
                timer.stop();
                card.show(superior, "zonaJuego");
                superior.revalidate();
                superior.repaint();
                timer.start();
                
                
                
                
            }/*else if(actionEvent.getSource() == timer) {
                System.out.println("t");
                if (controlAtentoYRapido.validarJugada(false)) {
                    if(controlAtentoYRapido.getHayImagenRepetida()) {
                        System.out.println("t1");
                        nuevaRonda();
                    }else {
                        System.out.println("t2");
                        cambioCuadro();
                    }
                    System.out.println("t3");
                    timer.restart();
                }else {
                    System.out.println("t4");
                    if (controlAtentoYRapido.determinarEstadoJuego()) {
                        System.out.println("t5");
                        nuevaRonda();
                        timer.restart();
                    }else {
                        System.out.println("t6");
                        mostrarEstadisticas();
                        timer.stop();
                        card.show(superior, "estadisticas");
                    }
                }
                
                superior.revalidate();
                superior.repaint();
                
            }else if(actionEvent.getSource() == pulsar) {
                System.out.println("p");
                if (controlAtentoYRapido.validarJugada(true)) {
                    System.out.println("p1");
                    nuevaRonda();
                    timer.restart();
                }else {
                    System.out.println("p2");
                    if (controlAtentoYRapido.determinarEstadoJuego()) {
                        System.out.println("p3");
                        nuevaRonda();
                        timer.restart();
                    }else {
                        System.out.println("p4");
                        mostrarEstadisticas();
                        timer.stop();
                        card.show(superior, "estadisticas");
                    }
                }
                
                superior.revalidate();
                superior.repaint();
                
                
                
            }else if(actionEvent.getSource() == abandonar) {
                controlAtentoYRapido.abandonar();
                timer.stop();
                inicio.hide();
                zonaJuego.hide();
                mostrarEstadisticas();
                card.show(superior, "estadisticas");
                superior.revalidate();
                superior.repaint();
            }
            */
        }
        
    }
    
}

