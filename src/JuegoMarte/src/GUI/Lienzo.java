package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import Heurística.NodoAEstrella;
import Útiles.MatrizDeJuego;

// Area de dibujo
@SuppressWarnings("serial")
public class Lienzo extends JPanel {
	// Matrix del juego
	private MatrizDeJuego mGame;		// Matriz de Juego
	private ArrayList<NodoAEstrella> solution;
//	boolean inicializado = false;
	
	public ArrayList getSolution() {
		return solution;
	}

	public void setSolution(ArrayList solution) {
		this.solution = solution;
	}

	// Datos del lienzo
	private int nFilas, nColumnas;
	private int altoCasilla, anchoCasilla;
	public int anchoX, anchoY = 0;
	
	
	
	/** CONSTRUCTOR */
	public Lienzo() {
		this.mGame = new MatrizDeJuego(10,10);
		this.solution = new ArrayList<NodoAEstrella>();
		this.nFilas = 10;
		this.nColumnas = 10;
	}
	
	/** GETTERS AND SETTERS */
	public MatrizDeJuego getmGame() {
		return mGame;
	}

	public void setmGame(MatrizDeJuego mGame) {
		this.mGame = mGame;
	}
	
	public int getNumeroDeColumnas() {
		return nColumnas;
	}

	public void setNumeroDeColumnas(int numeroDeColumnas) {
		this.nColumnas = numeroDeColumnas;
	}

	public int getNumeroDeFilas() {
		return nFilas;
	}

	public void setNumeroDeFilas(int numeroDeFilas) {
		this.nFilas = numeroDeFilas;
	}
	
	
	/** MÉTODOS */
	public void clear() {
		for (int y = 0; y < mGame.getTamanoY(); y++) {
			for (int x = 0; x < mGame.getTamanoX(); x++) {
				mGame.mPersonaje[x][y].setEstado(0);
			}
		}
		this.solution = new ArrayList<NodoAEstrella>();
	}

	public void updateMGame (int nFilas, int nColumnas, MatrizDeJuego mGame) {
		this.mGame = new MatrizDeJuego(nFilas, nColumnas);
		this.nFilas = nFilas;
		this.nColumnas = nColumnas;
		this.mGame = mGame;
	}
	
	
	
	public void ponerObjeto(int x, int y) {
		int posX = (int) (Math.floor((y/anchoCasilla)));
		int posY = (int) (Math.floor((x/altoCasilla)));
		System.out.println("posX: "+ posX);
		System.out.println("posY: "+ posY);
		switch (mGame.mPersonaje[posX][posY].getEstado()) {
	        case 0:  mGame.mPersonaje[posX][posY].setEstado(1);
	                 break;
	        case 1:  mGame.mPersonaje[posX][posY].setEstado(2);
	                 break;
	        case 2:  mGame.mPersonaje[posX][posY].setEstado(3);
	        		 break;
	        case 3:  mGame.mPersonaje[posX][posY].setEstado(4);
   		 			 break;
	        case 4:  mGame.mPersonaje[posX][posY].setEstado(0);
		 			 break;
	        default: mGame.mPersonaje[posX][posY].setEstado(1);
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
	protected void Random(int NUM){
		
		for(int i=0;i<NUM;i++){
			int x = mGame.aleatorioX();
			int y = mGame.aleatorioY();
			if( (mGame.mPersonaje[x][y].getEstado()!=1) && (mGame.mPersonaje[x][y].getEstado()!=2) && (mGame.mPersonaje[x][y].getEstado()!=3) && (mGame.mPersonaje[x][y].getEstado()!=4)){
				mGame.mPersonaje[x][y].setEstado(1);
			}
		}
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		pintarFondo(g);

		g.setColor(Color.BLUE.brighter());
		int x,y;
		for (int v = 0; v < solution.size(); v++) {
		       x = (solution.get(v).getX() * this.altoCasilla )+1;
               y = (solution.get(v).getY() * this.altoCasilla )+1;
               g.fillRect(y + anchoX, x + anchoY, (this.altoCasilla-1), (this.altoCasilla-1));
		}
//	         
		 
		
		Image img1 = Toolkit.getDefaultToolkit().getImage("Images/r2d2.JPG");
		Image img2 = Toolkit.getDefaultToolkit().getImage("Images/c3po.JPG");
		Image img3 = Toolkit.getDefaultToolkit().getImage("Images/roca.JPG");
		Image img4 = Toolkit.getDefaultToolkit().getImage("Images/agua.png");
		System.out.println("aqui1");
		for (int i = 0; i < mGame.getTamanoX(); i++) {
			System.out.println("aqui2");
			for (int j = 0; j < mGame.getTamanoY(); j++) {
				if (mGame.mPersonaje[i][j].getEstado() == 1) { // casilla pintada
//					g.setColor(Color.BLACK);
					x = (i * this.altoCasilla)+1;
					y = (j * this.altoCasilla)+1;
					g.drawImage(img3, y + anchoX, x + anchoY, (this.altoCasilla-1), (this.altoCasilla-1),this);
				}
				if (mGame.mPersonaje[i][j].getEstado() == 2) { // casilla pintada
//					g.setColor(Color.BLUE);
					x = (i * this.altoCasilla)+1;
					y = (j * this.altoCasilla)+1;
//					g.fillRect(x, y, (this.altoCasilla-1), (this.altoCasilla-1));
					g.drawImage(img1, y + anchoX, x + anchoY, (this.altoCasilla-1), (this.altoCasilla-1),this);
				}
				if (mGame.mPersonaje[i][j].getEstado() == 3) { // casilla pintada
//					g.setColor(Color.BLUE);
					x = (i * this.altoCasilla)+1;
					y = (j * this.altoCasilla)+1;
//					g.fillRect(x, y, (this.altoCasilla-1), (this.altoCasilla-1));
					g.drawImage(img2, y + anchoX, x + anchoY, (this.altoCasilla-1), (this.altoCasilla-1),this);
				}
				if (mGame.mPersonaje[i][j].getEstado() == 4) { // casilla pintada
					g.setColor(Color.BLUE);
					x = (i * this.altoCasilla)+1;
					y = (j * this.altoCasilla)+1;
//					g.fillRect(x, y, (this.altoCasilla-1), (this.altoCasilla-1));
					g.drawImage(img4, y + anchoX, x + anchoY, (this.altoCasilla-1), (this.altoCasilla-1),this);
				}
			}
		}
	}

	public void pintarFondo(Graphics g) {
		// INICIALIZAMOS TABLERO
//		if (!inicializado) {
//			mGame = new MatrizDeJuego(10, 10);
//			mGame.predeterminado();
//			inicializado = true;
//		}
		
		this.altoCasilla = this.getHeight() / nFilas;
		this.anchoCasilla = this.getWidth() / nColumnas;

		// Limites
		if (this.altoCasilla > this.anchoCasilla) {
			this.altoCasilla = this.anchoCasilla;
		} else {
			this.anchoCasilla = this.altoCasilla;
		} 
		
		anchoY = (this.getHeight() - (altoCasilla * nFilas))/2;
		anchoX = (this.getWidth() - (anchoCasilla * nColumnas))/2;
		
		g.setColor(Color.GRAY.brighter());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.WHITE.brighter());
		int x,y;
		 for (int i = 0; i < nFilas; i++){
	            for (int j = 0; j < nColumnas; j++){
	                x = (i * this.altoCasilla )+1;
	                y = (j * this.altoCasilla )+1;
	                g.fillRect(y + anchoX, x + anchoY, (this.altoCasilla-1), (this.altoCasilla-1));
	            }
        }
	}
}
