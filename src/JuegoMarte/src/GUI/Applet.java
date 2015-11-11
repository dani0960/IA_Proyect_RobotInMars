package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.Random;

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

import Heurística.Astars;
import Heurística.Node;
import Útiles.LoadMap;
import Útiles.Map;

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

	private Canvas areaDibujo;
	private JPanel areaBotones = new JPanel();

	// BOTONES
	protected JButton botonInicio = new JButton("Run");
	protected JButton botonFin = new JButton("Clear");
//	protected JButton botonPausa = new JButton("Pausa");
//	protected JButton botonPaso = new JButton("Paso");
	protected JButton botonMapa = new JButton("Load map");
	protected JButton botonSaveMapa = new JButton("Save map");
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
	protected Image img4 = Toolkit.getDefaultToolkit().getImage("Images/wars.png");
	
	private JTextField jtfnumber = new JTextField();
	private JTextField jtfnumber2 = new JTextField();

	// ComboBox
	String[] patronesStrings = { "Block","Beehive","Toad","Blinker", "Glider", "Diehard" };
	private JComboBox patList = new JComboBox(patronesStrings);
	
	private int cambio = 0;
	// Timer
	Timer timer, _timer;
	int delay = 100; // milliseconds

	// Variables no fijas
	private int obsta=10;
	
	/** CONSTRUCTOR */
	public void init() {
		areaDibujo = new Canvas(); 
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
		
		// Nos suscribimos a cambios en el JSpinner FILAS
		spin1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// Ponemos el valor del JSpinner en el JTextField
				int value = ((SpinnerNumberModel) spin1.getModel()).getNumber().intValue();
				
				
				Map mGame = new Map(areaDibujo.getmGame().getTamanoX(), value );
				areaDibujo.updateMap(mGame);
				repaint();
			}
		
		});
				
		// Nos suscribimos a cambios en el JSpinner COLUMNAS
		spin2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// Ponemos el valor del JSpinner en el JTextField
				int value = ((SpinnerNumberModel) spin2.getModel()).getNumber().intValue();

				Map mGame = new Map(value, areaDibujo.getmGame().getTamanoY());
				areaDibujo.updateMap(mGame);
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
				
				

//		/** Timer */
//		timer = new Timer(delay, new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
////				areaDibujo.paso();
//				repaint();
//			}
//		});

		/** Boton INICIAR */
		botonInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				timer.start();
				Node nodoInicial = null;
				Node nodoFinal=null;
				// put the starting node on the open list (you can leave its f at zero)
				for (int i = 0; i < areaDibujo.getmGame().getTamanoX(); i++) {
					for (int j = 0; j < areaDibujo.getmGame().getTamanoY(); j++) {
						if (areaDibujo.getmGame().map[i][j] == 2) {
							nodoInicial = new Node(i, j);
							nodoInicial.definirVecinos(areaDibujo.getmGame());
//							nodoRobot = new NodoAEstrella(i, j);
							//System.out.println("nodoInicial: ("+i+", "+j+").");
						}
						if (areaDibujo.getmGame().map[i][j] == 4) {
							nodoFinal = new Node(i, j);
							nodoFinal.definirVecinos(areaDibujo.getmGame());
//							nodoAgua = new NodoAEstrella(i, j);
							//System.out.println("nodoFinal: ("+i+", "+j+").");
						}
					}
				}
				Astars as = new Astars();
				areaDibujo.setSolution(as.AStars(nodoInicial, nodoFinal,areaDibujo.getmGame() ));
				repaint();
			}
		});

		/** Boton PAUSAR */
//		botonPausa.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				timer.stop();
//			}
//		});

		/** Boton STEP */
//		botonPaso.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				timer.stop();
////				areaDibujo.paso();
//				repaint();
//			}
//		});

		/** Boton BORRAR */
		botonFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				timer.stop();
				areaDibujo.init();
				repaint();
			}
		});
		
//		botonObstaculo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				areaDibujo.Random(obsta);
//			}
//		});
		
		/** Boton CARGAR MAPA */
		botonMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Map mGGame = new Map();
					mGGame = LoadMap.CargarMapa("D:/Google drive/Workspace/JuegoMarte/Mapas/mapa1.txt");
					areaDibujo.loadMap(mGGame);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				repaint();
			}
		});

		botonSaveMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Map mGGame = new Map();
					mGGame = LoadMap.CargarMapa("D:/Google drive/Workspace/JuegoMarte/Mapas/mapa1.txt");
					areaDibujo.loadMap(mGGame);
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
				int value = deslizadorVelocidad.getValue();
//				timer.setDelay(velocidad);
				textoVelocidad.setText("Obstáculos: " + value + " %");
				areaDibujo.getmGame().clear();
//				if deslizadorVelocidad.
//					
//				if (value > cambio)
////				areaDibujo.getmGame()
				value = (areaDibujo.getmGame().getTamanoY()*areaDibujo.getmGame().getTamanoX())* value/100 ;
				System.out.println("tamañoX:"+ areaDibujo.getmGame().getTamanoX());
				System.out.println("tamañoY:"+ areaDibujo.getmGame().getTamanoY());
				Random rn = new Random();
				for (int i=0; i < value;i++){
					int random = rn.nextInt(areaDibujo.getmGame().getTamanoY()*areaDibujo.getmGame().getTamanoX());
					int nfila = random/areaDibujo.getmGame().getTamanoX(); //Se divide el aleatorio entre las celdas de ancho para obtener la fila
					int ncol = random%areaDibujo.getmGame().getTamanoX(); //Se usa el resto de la division para calcular la columna
					if (areaDibujo.getmGame().map[ncol][nfila] != 1){
						areaDibujo.getmGame().map[ncol][nfila] = 1;					
					}
				}
				repaint();
				
				
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
//				System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");
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
//		panel.add(botonPausa);
//		panel.add(botonPaso);
		panel.add(botonMapa);
		panel.add(botonSaveMapa);
		panel.add(textoFilas);
		panel.add(textoColumnas);
		panel.add(spin1);
		panel.add(spin2);
		panel.add(spin3);
		panel.add(patList);
		panel.add(botonObstaculo);
	
		
		panel.add(textoObstaculos);
		panel.add(textoVersion);
		
		
		
		botonMapa.setBounds(new Rectangle(MARGEN , MARGEN , ANCHO_BOTON, ALTO_BOTON));
		botonSaveMapa.setBounds(new Rectangle(MARGEN , MARGEN * 5, ANCHO_BOTON, ALTO_BOTON));
		botonInicio.setBounds(new Rectangle(MARGEN, MARGEN * 13, ANCHO_BOTON, ALTO_BOTON));
//		botonPausa.setBounds(new Rectangle(MARGEN , MARGEN * 5, ANCHO_BOTON, ALTO_BOTON));
//		botonPaso.setBounds(new Rectangle(MARGEN , MARGEN * 9, ANCHO_BOTON, ALTO_BOTON));
		botonFin.setBounds(new Rectangle(MARGEN , MARGEN * 17, ANCHO_BOTON, ALTO_BOTON));
		
		
		
		textoVelocidad.setBounds(new Rectangle(MARGEN +35 , MARGEN * 29, ANCHO_BOTON + 80, ALTO_BOTON));
		deslizadorVelocidad.setBounds(new Rectangle( MARGEN  , MARGEN * 31 , ANCHO_BOTON , ALTO_BOTON));
		
		textoFilas.setBounds(new Rectangle(MARGEN , MARGEN * 21, ANCHO_BOTON , ALTO_BOTON));
		spin1.setBounds(new Rectangle(MARGEN +80, MARGEN * 21, ANCHO_CAMPO, ALTO_BOTON));
		textoColumnas.setBounds(new Rectangle(MARGEN , MARGEN * 25, ANCHO_BOTON , ALTO_BOTON));
		spin2.setBounds(new Rectangle(MARGEN +80, MARGEN * 25, ANCHO_CAMPO, ALTO_BOTON));
//		patList.setBounds(new Rectangle(MARGEN * 101, MARGEN, ANCHO_BOTON, ALTO_BOTON));

//		textoObstaculos.setBounds(new Rectangle(MARGEN , MARGEN * 35, ANCHO_BOTON , ALTO_BOTON));
//		spin3.setBounds(new Rectangle(MARGEN +80, MARGEN * 35, ANCHO_CAMPO, ALTO_BOTON));
//		botonObstaculo.setBounds(new Rectangle(MARGEN , MARGEN * 40, ANCHO_BOTON, ALTO_BOTON));
		
		textoVersion.setText("Version 0.0.2");
		textoVersion.setBounds(new Rectangle(MARGEN +40 , MARGEN * 50, ANCHO_BOTON + 80, ALTO_BOTON));
	}

	public void inicializarDeslizadores(JPanel panel) {

		// DESLIZADOR
		deslizadorVelocidad = new JSlider();
		// Oyente oyente = new Oyente(this);

		// CARACTERISTICAS DE DESLIZADOR VELOCIDAD
		deslizadorVelocidad.setMinimum(0);
		deslizadorVelocidad.setMaximum(90);
		deslizadorVelocidad.setValue(0);
//		deslizadorVelocidad.setInverted(true);
		// deslizadorVelocidad.addChangeListener(oyente);

		// texto velocidad
		textoVelocidad = new JLabel("Obstáctulos: " + 0 + "%");

		
		panel.add(textoVelocidad);
		panel.add(deslizadorVelocidad);
	}
}
