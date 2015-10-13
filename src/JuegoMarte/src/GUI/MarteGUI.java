package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class MarteGUI extends JApplet {
	public static final int TAMANO_PANEL = 50;
	public static final int TAMANO_PANEL_DIBUJO = 500;
	public static final int MARGEN = 10;
	public static final int ANCHO_BOTON = 90;
	public static final int ALTO_BOTON = 30;

	Border etched = BorderFactory.createEtchedBorder();
	Border blackline = BorderFactory.createLineBorder(Color.black);

	private PanelMarteGUI areaDibujo = new PanelMarteGUI();
	private JPanel areaBotones = new JPanel();

	protected JButton botonInicio = new JButton("Inicio");
	protected JButton botonFin = new JButton("Fin");
	protected JButton botonPausa = new JButton("Pausa");
	protected JButton botonPaso = new JButton("Paso");

	protected JSlider deslizadorVelocidad;
	protected JLabel textoVelocidad = new JLabel();

	private JTextField jtfnumber = new JTextField();

	// ComboBox
	String[] patronesStrings = { "Block","Beehive","Toad","Blinker", "Glider", "Diehard" };
	private JComboBox patList = new JComboBox(patronesStrings);
	
	// Figuras
	boolean Block = true;
	boolean Beehive = false;
	boolean Toad = false;
	boolean Blinker = false;
	boolean Glider = false;
	boolean Diehard = false;
	
	// Timer
	Timer timer, _timer;
	int delay = 100; // milliseconds

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
		add(areaBotones, BorderLayout.SOUTH);
		inicializarDeslizadores(areaBotones);
		inicializarBotones(areaBotones);

		// // BOTONES

		/** Introduce numero 1-100 */
		patList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = patList.getSelectedItem().toString();
				System.out.println(value);
				if (value == "Block") {
//					areaDibujo.setBlock();
					Block = true;
					Blinker = false;
					Diehard = false;
					Glider = false;
					Beehive = false;
					Toad =false;
				}
				
				
				if (value == "Beehive") {
//					areaDibujo.setBeehive();
					Beehive = true;
					Block = false;
					Blinker = false;
					Diehard = false;
					Glider = false;
					Toad =false;
				}
				
				if (value == "Toad") {
//					areaDibujo.setToad();
					Toad = true;
					Beehive = false;
					Block = false;
					Blinker = false;
					Diehard = false;
					Glider = false;
					Toad =false;
				}
				
				if (value == "Blinker") {
//					areaDibujo.setBlinker();
					Block = false;
					Blinker = true;
					Diehard = false;
					Glider = false;
					Beehive = false;
					Toad =false;
				}
				if (value == "Glider") {
//					areaDibujo.setGlider();
					Block = false;
					Glider = true;
					Diehard = false;
					Blinker = false;
					Beehive = false;
					Toad =false;
				}
				if (value == "Diehard") {
//					areaDibujo.setDiehard();
					Block = false;
					Diehard = true;
					Glider = false;
					Blinker = false;
					Beehive = false;
					Toad =false;
				}
				repaint();
			}
		});

		/** Introduce numero 1-100 */
		jtfnumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int number = Integer.parseInt(jtfnumber.getText());
				if (number > 100 || number < 1) {
					JOptionPane.showMessageDialog(null,
							"Introduzca un valor numérico entre 1 y 100");
				} else {
//					areaDibujo.clear();
//					repaint();
					if (Block) {
						for (int i = 0; i < number; i++) {
//							areaDibujo.setBlock();
						}
					}
					if (Beehive) {
						for (int i = 0; i < number; i++) {
//							areaDibujo.setBeehive();
						}
					}
					if (Toad) {
						for (int i = 0; i < number; i++) {
//							areaDibujo.setToad();
							
						}
					}
					if (Blinker) {
						for (int i = 0; i < number; i++) {
//							areaDibujo.setBlinker();
						}
					}
					if (Diehard) {
						for (int i = 0; i < number; i++) {
//							areaDibujo.setDiehard();
						}

					}
					if (Glider) {
						for (int i = 0; i < number; i++) {
//							areaDibujo.setGlider();
						}
					}
				}
				repaint();
			}
		});

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
				timer.start();
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

		/** Sliders VELOCIDAD */
		deslizadorVelocidad.addChangeListener(new ChangeListener() {
			/** Handle scroll bar adjustment actions */
			public void stateChanged(ChangeEvent e) {
				int velocidad = deslizadorVelocidad.getValue();
				timer.setDelay(velocidad);
				textoVelocidad.setText("Velocidad: " + velocidad + " ms");
			}
		});

		areaDibujo.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				areaDibujo.ponerCelula(x, y);
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
		panel.add(jtfnumber);
		panel.add(patList);

		botonInicio.setBounds(new Rectangle(MARGEN, MARGEN, ANCHO_BOTON,
				ALTO_BOTON));
		botonPausa.setBounds(new Rectangle(MARGEN * 11, MARGEN, ANCHO_BOTON,
				ALTO_BOTON));
		botonPaso.setBounds(new Rectangle(MARGEN * 21, MARGEN, ANCHO_BOTON,
				ALTO_BOTON));
		botonFin.setBounds(new Rectangle(MARGEN * 31, MARGEN, ANCHO_BOTON,
				ALTO_BOTON));
		jtfnumber.setBounds(new Rectangle(MARGEN * 71, MARGEN, ANCHO_BOTON,
				ALTO_BOTON));
//		patList.setBounds(new Rectangle(MARGEN * 101, MARGEN, ANCHO_BOTON,
//				ALTO_BOTON));

		deslizadorVelocidad.setBounds(new Rectangle(MARGEN * 41,
				MARGEN * 2 - 2, ANCHO_BOTON + 80, ALTO_BOTON));
		textoVelocidad.setBounds(new Rectangle(MARGEN * 43, -2,
				ANCHO_BOTON + 80, ALTO_BOTON));
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
