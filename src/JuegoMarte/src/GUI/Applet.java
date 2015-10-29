package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Heurística.AlgoritmoAEstrella;
import Útiles.CargarMapa;
import Útiles.MatrizDeJuego;

@SuppressWarnings("serial")
public class Applet extends JApplet {
	public static final int TAMANO_PANEL = 50;
	public static final int TAMANO_PANEL_DIBUJO = 170;
	public static final int MARGEN = 10;
	public static final int ANCHO_BOTON = 150;
	public static final int ANCHO_CAMPO = 50;
	public static final int ALTO_BOTON = 30;

	Border etched = BorderFactory.createEtchedBorder();
	Border blackline = BorderFactory.createLineBorder(Color.black);

	private Lienzo areaDibujo = new Lienzo();
	private JPanel areaBotones = new JPanel();

	// BOTONES
	protected JButton botonInicio = new JButton("Inicio");
	protected JButton botonFin = new JButton("Fin");
	protected JButton botonPausa = new JButton("Pausa");
	protected JButton botonPaso = new JButton("Paso");
	protected JButton botonMapa = new JButton("Cargar Mapa");
	protected JButton botonObstaculo = new JButton("Obstáculo");

	protected JSlider deslizadorVelocidad;
	protected JLabel textoVelocidad = new JLabel();
	protected JLabel textoFilas = new JLabel();
	protected JLabel textoColumnas = new JLabel();
	protected JLabel textoObstaculos = new JLabel();
	protected JLabel textoVersion = new JLabel();
	
	// from 0 to 9, in 1.0 steps start value 5  SPINNER
	protected SpinnerNumberModel model1 = new SpinnerNumberModel(10.0, 10.0, 100.0, 1.0);  
	protected JSpinner spin1 = new JSpinner(model1);
	protected SpinnerNumberModel model2 = new SpinnerNumberModel(10.0, 10.0, 100.0, 1.0); 
	protected JSpinner spin2 = new JSpinner(model2);
	protected SpinnerNumberModel model3 = new SpinnerNumberModel(10.0, 10.0, 100.0, 1.0); 
	protected JSpinner spin3 = new JSpinner(model3);
	
	
	private JTextField jtfnumber = new JTextField();
	private JTextField jtfnumber2 = new JTextField();

	// ComboBox
	String[] patronesStrings = { "Block","Beehive","Toad","Blinker", "Glider", "Diehard" };
	private JComboBox patList = new JComboBox(patronesStrings);
	
	// Timer
	Timer timer, _timer;
	int delay = 100; // milliseconds

	// Variables no fijas
	private int obsta=10;
	
	/** CONSTRUCTOR */
	public void init() {

		// AREA DIBUJO
		areaDibujo.setBackground(Color.WHITE);
		// areaDibujo.addMouseListener(oyente);
		areaDibujo.setFocusable(true); // centro el foco en el area de dibujo
		add(areaDibujo, BorderLayout.CENTER);
		// areaDibujo.addMouseMotionListener(oyente);

		// AREA BOTONES
		areaBotones.setPreferredSize(new Dimension(TAMANO_PANEL_DIBUJO,
				TAMANO_PANEL));
		areaBotones.setLayout(null);
		areaBotones.setBorder(blackline);
		add(areaBotones, BorderLayout.WEST);
		inicializarDeslizadores(areaBotones);
		textoColumnas = new JLabel("Columnas: ");
		textoFilas = new JLabel("Filas: ");
		textoObstaculos = new JLabel("Obstáculos: ");
		inicializarBotones(areaBotones);

		// // BOTONES

		/** Introduce numero 1-100 */
		patList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = patList.getSelectedItem().toString();
				System.out.println(value);
				repaint();
			}
		});

//		/** Introduce numero 1-100 */
//		spin1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int number = Integer.parseInt(jtfnumber.getText());
//				
//				areaDibujo.setNumeroDeFilas(number);
//				areaDibujo.clear();
//				repaint();
//			}
//		});
		
//		spin1.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
////			lblResult.setText("Value Of : " + String.valueOf(spin1.getValue()));
//			int number = Integer.parseInt(spin1.getValue().toString());
//			 System.out.println("Value Of : " + String.valueOf(spin1.getValue()));
//			areaDibujo.setNumeroDeFilas(number);
//			areaDibujo.clear();
//			repaint();
//			}
//		});
		
		// Nos suscribimos a cambios en el JSpinner
				spin1.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						// Ponemos el valor del JSpinner en el JTextField
						int value = ((SpinnerNumberModel) spin1.getModel()).getNumber().intValue();
//						 System.out.println("Value Of : " + String.valueOf(spin1.getValue()));
//						areaDibujo.setNumeroDeFilas(value);
						areaDibujo.updateMGame(value, areaDibujo.getNumeroDeColumnas(), areaDibujo.getmGame());
						areaDibujo.clear();
						repaint();
					}
				
				});
				
				// Nos suscribimos a cambios en el JSpinner
				spin2.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						// Ponemos el valor del JSpinner en el JTextField
						int value = ((SpinnerNumberModel) spin2.getModel()).getNumber().intValue();
//						 System.out.println("Value Of : " + String.valueOf(spin1.getValue()));
//						areaDibujo.setNumeroDeColumnas(value);
						areaDibujo.updateMGame(areaDibujo.getNumeroDeFilas(), value, areaDibujo.getmGame());
						areaDibujo.clear();
						repaint();
					}
				
				});
				spin3.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						// Ponemos el valor del JSpinner en el JTextField
						int value = ((SpinnerNumberModel) spin3.getModel()).getNumber().intValue();
						obsta= value;
					}
				});
				
				
		/** Introduce numero 1-100 */
//		jtfnumber2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int number = Integer.parseInt(jtfnumber2.getText());
//				
////				areaDibujo.setNumeroDeColumnas(number);
//				areaDibujo.updateMGame(areaDibujo.getNumeroDeFilas(), number);
//				areaDibujo.clear();
//				repaint();
//			}
//		});

		/** Timer */
		timer = new Timer(delay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				areaDibujo.paso();
				repaint();
			}
		});

		/** Boton INICIAR */
		botonInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				timer.start();
				AlgoritmoAEstrella AEstrella = new AlgoritmoAEstrella();
				AEstrella.calcularCamino(areaDibujo.getmGame());
			}
		});

		/** Boton PAUSAR */
		botonPausa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
			}
		});

		/** Boton STEP */
		botonPaso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
//				areaDibujo.paso();
				repaint();
			}
		});

		/** Boton BORRAR */
		botonFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				areaDibujo.clear();
				repaint();
			}
		});
		
		botonObstaculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				areaDibujo.Random(obsta);
			}
		});
		
		/** Boton BORRAR */
		botonMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MatrizDeJuego mGGame = new MatrizDeJuego();
					mGGame = CargarMapa.CargarMapa("D:/Google drive/Workspace/JuegoMarte/Mapas/mapa1.txt");
					areaDibujo.updateMGame(mGGame.getTamanoX(), mGGame.getTamanoY(), mGGame);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				repaint();
			}
		});

		/** Sliders VELOCIDAD */
		deslizadorVelocidad.addChangeListener(new ChangeListener() {
			/** Handle scroll bar adjustment actions */
			public void stateChanged(ChangeEvent e) {
				int velocidad = deslizadorVelocidad.getValue();
				timer.setDelay(velocidad);
				textoVelocidad.setText("Velocidad: " + velocidad + " ms");
			}
		});

		areaDibujo.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getX()- areaDibujo.anchoX;
				int y = e.getY()- areaDibujo.anchoY;

				areaDibujo.ponerObjeto(x, y);
				repaint();
			}
		});
		
		areaDibujo.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX()- areaDibujo.anchoX;
				int y = e.getY()- areaDibujo.anchoY;
				System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
				areaDibujo.ponerObjeto(x, y);
				repaint();

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		// repaint();
	}

	public void inicializarBotones(JPanel panel) {
		panel.add(botonInicio);
		panel.add(botonFin);
		panel.add(botonPausa);
		panel.add(botonPaso);
		panel.add(botonMapa);
		panel.add(textoFilas);
		panel.add(textoColumnas);
		panel.add(spin1);
		panel.add(spin2);
		panel.add(spin3);
		panel.add(patList);
		panel.add(botonObstaculo);
		
		
		panel.add(textoObstaculos);
		panel.add(textoVersion);
		
		
		botonInicio.setBounds(new Rectangle(MARGEN, MARGEN, ANCHO_BOTON, ALTO_BOTON));
		botonPausa.setBounds(new Rectangle(MARGEN , MARGEN * 5, ANCHO_BOTON, ALTO_BOTON));
		botonPaso.setBounds(new Rectangle(MARGEN , MARGEN * 9, ANCHO_BOTON, ALTO_BOTON));
		botonFin.setBounds(new Rectangle(MARGEN , MARGEN * 13, ANCHO_BOTON, ALTO_BOTON));
		botonMapa.setBounds(new Rectangle(MARGEN , MARGEN * 44, ANCHO_BOTON, ALTO_BOTON));
		
		
		textoVelocidad.setBounds(new Rectangle(MARGEN , MARGEN * 17, ANCHO_BOTON + 80, ALTO_BOTON));
		deslizadorVelocidad.setBounds(new Rectangle( MARGEN  , MARGEN * 21 , ANCHO_BOTON , ALTO_BOTON));
		
		textoFilas.setBounds(new Rectangle(MARGEN , MARGEN * 27, ANCHO_BOTON , ALTO_BOTON));
		spin1.setBounds(new Rectangle(MARGEN +80, MARGEN * 27, ANCHO_CAMPO, ALTO_BOTON));
		textoColumnas.setBounds(new Rectangle(MARGEN , MARGEN * 31, ANCHO_BOTON , ALTO_BOTON));
		spin2.setBounds(new Rectangle(MARGEN +80, MARGEN * 31, ANCHO_CAMPO, ALTO_BOTON));
		patList.setBounds(new Rectangle(MARGEN * 101, MARGEN, ANCHO_BOTON, ALTO_BOTON));

		textoObstaculos.setBounds(new Rectangle(MARGEN , MARGEN * 35, ANCHO_BOTON , ALTO_BOTON));
		spin3.setBounds(new Rectangle(MARGEN +80, MARGEN * 35, ANCHO_CAMPO, ALTO_BOTON));
		botonObstaculo.setBounds(new Rectangle(MARGEN , MARGEN * 40, ANCHO_BOTON, ALTO_BOTON));
		
		textoVersion.setText("Versión 0.0.1");
		textoVersion.setBounds(new Rectangle(MARGEN +40 , MARGEN * 50, ANCHO_BOTON + 80, ALTO_BOTON));
	}

	public void inicializarDeslizadores(JPanel panel) {

		// DESLIZADOR
		deslizadorVelocidad = new JSlider();
		// Oyente oyente = new Oyente(this);

		// CARACTERISTICAS DE DESLIZADOR VELOCIDAD
		deslizadorVelocidad.setMinimum(20);
		deslizadorVelocidad.setMaximum(1000);
		deslizadorVelocidad.setValue(200);
		deslizadorVelocidad.setInverted(true);
		// deslizadorVelocidad.addChangeListener(oyente);

		// texto velocidad
		textoVelocidad = new JLabel("Velocidad: " + 200 + " ms");

		
		panel.add(textoVelocidad);
		panel.add(deslizadorVelocidad);
	}
}
