package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Objetos.ReglasJuego;
import Objetos.Tablero;

// Area de dibujo
@SuppressWarnings("serial")
public class PanelMarteGUI extends JPanel {
	private int CELDAS = 50;
	private int nCeldasX;
	private int nCeldasY;
	private Tablero tablero;
	private ReglasJuego reglas = new ReglasJuego();
	private int hGap;
	boolean inicializado = false;


	public void clear() {
		for (int y = 0; y < tablero.getTamanoY(); y++) {
			for (int x = 0; x < tablero.getTamanoX(); x++) {
				tablero.robot[x][y].setEstado(0);
				tablero.robot[x][y].setEstadoSiguiente(0);
			}
		}
	}

	public void ponerCelula(int x, int y) {
		int posX = (int) (Math.floor(x / hGap));
		int posY = (int) (Math.floor(y / hGap));
		if (tablero.robot[posX][posY].getEstado() == 0) {
			tablero.robot[posX][posY].setEstado(1);
		} else {
			tablero.robot[posX][posY].setEstado(0);
		}
	}

	// Evolución del Juego
	public void paso() {
//		for (int y = 0; y < nCeldasY; y++) {
//			for (int x = 0; x < nCeldasX; x++) {
//				reglas.chequear(tablero, tablero.robot[x][y]);
//			}
//		}
//		for (int y = 0; y < tablero.getTamanoY(); y++) {
//			for (int x = 0; x < tablero.getTamanoX(); x++) {
//				tablero.robot[x][y].setEstado(tablero.robot[x][y]
//						.getEstadoSiguiente());
//			}
//		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		pintarFondo(g);

		g.setColor(Color.BLACK);
		for (int x = 0; x < tablero.getTamanoX(); x++) {
			for (int y = 0; y < tablero.getTamanoY(); y++) {
				if (tablero.robot[x][y].getEstado() == 1) {
					g.fillRect(x * hGap + 1, y * hGap + 1, hGap - 1, hGap - 1);
				}
			}
		}
	}

	public void pintarFondo(Graphics g) {

		int anchoTotal = this.getWidth();
		int altoTotal = this.getHeight();
		int numeroDeColumnas = 300;
		int numeroDeFilas = 300;

		int altoCasilla = this.getHeight() / numeroDeFilas;
		int anchoCasilla = this.getWidth() / numeroDeColumnas;

		g.setColor(Color.GRAY.brighter());

		for (int fila = 0; fila < anchoTotal; fila+= anchoCasilla) {
			g.drawLine(fila, 0, fila, altoTotal);			// Pintar líneas verticales
		}
		for (int columna = 0; columna < altoTotal; columna+= altoCasilla) {
			g.drawLine(0, columna, anchoTotal, columna );			// Pintar líneas verticales
		}
		if (!inicializado) {
			tablero = new Tablero(nCeldasX + 20, nCeldasY + 20);
//			tablero.predeterminado();
			inicializado = true;
		}
	}
}
