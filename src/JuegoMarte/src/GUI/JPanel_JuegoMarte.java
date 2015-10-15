package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Objetos.Robot;
import Reglas.ReglasJuego;

// Area de dibujo
@SuppressWarnings("serial")
public class JPanel_JuegoMarte extends JPanel {
	private Tablero tablero;
	private ReglasJuego reglas = new ReglasJuego();
//	private int hGap;
	boolean inicializado = false;
	
	private int numeroDeColumnas;
	private int numeroDeFilas;
	private int altoCasilla;
	private int anchoCasilla;
	
	
	
	
	public JPanel_JuegoMarte() {
		this.numeroDeFilas=10;
		this.numeroDeColumnas= 10;

	}
	
	/* GETTERS AND SETTERS */
	public int getNumeroDeColumnas() {
		return numeroDeColumnas;
	}

	public void setNumeroDeColumnas(int numeroDeColumnas) {
		this.numeroDeColumnas = numeroDeColumnas;
	}

	public int getNumeroDeFilas() {
		return numeroDeFilas;
	}

	public void setNumeroDeFilas(int numeroDeFilas) {
		this.numeroDeFilas = numeroDeFilas;
	}
	
	/* MÉTODOS */
	
	
	public void clear() {
		for (int y = 0; y < tablero.getTamanoY(); y++) {
			for (int x = 0; x < tablero.getTamanoX(); x++) {
				tablero.robot[x][y].setEstado(0);
				tablero.robot[x][y].setEstadoSiguiente(0);
			}
		}
	}

	public void ponerObjeto(int x, int y) {
		int posX = (int) (Math.floor(x / anchoCasilla));
		int posY = (int) (Math.floor(y / altoCasilla));
		switch (tablero.robot[posX][posY].getEstado()) {
	        case 0:  tablero.robot[posX][posY].setEstado(1);
	                 break;
	        case 1:  tablero.robot[posX][posY].setEstado(2);
	                 break;
	        case 2:  tablero.robot[posX][posY].setEstado(0);
	        		 break;
	        default: tablero.robot[posX][posY].setEstado(1);
	        break;
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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		pintarFondo(g);

		
		int x,y;
		for (int i = 0; i < tablero.getTamanoX(); i++) {
			for (int j = 0; j < tablero.getTamanoY(); j++) {
				if (tablero.robot[i][j].getEstado() == 1) { // casilla pintada
					g.setColor(Color.BLACK);
					x = (i * this.altoCasilla)+1;
					y = (j * this.altoCasilla)+1;
					g.fillRect(x, y, (this.altoCasilla-1), (this.altoCasilla-1));
				}
				if (tablero.robot[i][j].getEstado() == 2) { // casilla pintada
					g.setColor(Color.BLUE);
					x = (i * this.altoCasilla)+1;
					y = (j * this.altoCasilla)+1;
					g.fillRect(x, y, (this.altoCasilla-1), (this.altoCasilla-1));
				}
			}
		}
	}

	public void pintarFondo(Graphics g) {

		// INICIALIZAMOS TABLERO
		if (!inicializado) {
			tablero = new Tablero(100, 100);
			tablero.predeterminado();
			inicializado = true;
		}
		


		this.altoCasilla = this.getHeight() / numeroDeFilas;
		this.anchoCasilla = this.getWidth() / numeroDeColumnas;

		// Limites
		if (this.altoCasilla > this.anchoCasilla) {
			this.altoCasilla = this.anchoCasilla;
		} 
		if (this.anchoCasilla > this.altoCasilla) {
			this.anchoCasilla = this.altoCasilla;
		} 
		g.setColor(Color.GRAY.brighter());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.WHITE.brighter());
		int x,y;
		 for (int i = 0; i < numeroDeFilas; i++){
	            for (int j = 0; j < numeroDeColumnas; j++){
	                x = (i * this.altoCasilla)+1;
	                y = (j * this.altoCasilla)+1;
	                g.fillRect(y, x, (this.altoCasilla-1), (this.altoCasilla-1));
	            }
	        }
	}
}
