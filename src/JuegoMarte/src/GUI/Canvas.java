package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Heurística.Node;
import Útiles.Map;

// Area de dibujo
@SuppressWarnings("serial")
public class Canvas extends JPanel {
	// Matrix del juego
	private Map mGame;		// Matriz de Juego
	private List<Node> solution;
	
	// Datos del lienzo
	private int altoCasilla, anchoCasilla;
	public int anchoX, anchoY = 0;
	private int lado;
	
	/** CONSTRUCTOR */
	public Canvas() {	// Matrix default
		this.mGame = new Map(10,10);
		this.solution = new ArrayList<Node>();
		this.init();
	}
	
	public Canvas (int nFilas, int nColumnas ) { // Matrix x y
		this.mGame = new Map(nFilas, nColumnas);
		this.solution = new ArrayList<Node>();
		this.init();
	}
	

	/** GETTERS AND SETTERS */
	public Map getmGame() {
		return mGame;
	}

	public void setmGame(Map mGame) {
		this.mGame = mGame;
	}
	
	/** MÉTODOS */
	
	public void loadMap (Map mGame ) { // Matrix 
		this.mGame = mGame;
	}
	
	public void updateMap (Map mGame ) { // Matrix 
		this.mGame = new Map(mGame.getTamanoX(), mGame.getTamanoY());
		this.init();
	}
	
	public void init() {
		//system.out.println("tamaño mGamex: "+mGame.getTamanoX());
		//system.out.println("tamaño mGamey: "+mGame.getTamanoX());
		for (int x = 0; x < mGame.getTamanoX(); x++) {
			for (int y = 0; y < mGame.getTamanoY(); y++) {
				//system.out.println("x: "+x+", y:"+y);
				mGame.map[x][y]= 0;
			}
		}
		this.solution = new ArrayList<Node>();
	}

	
	
	
	
	public void ponerObjeto(int x, int y) {
		int posX = (int) (Math.floor((x/lado)));
		int posY = (int) (Math.floor((y/lado)));
		////system.out.println("posX: "+ posX);
		////system.out.println("posY: "+ posY);
		switch (mGame.map[posX][posY]) {
	        case 0:  mGame.map[posX][posY] = 1;
	                 break;
	        case 1:  mGame.map[posX][posY] = 2;
	                 break;
	        case 2:  mGame.map[posX][posY] = 3;
	        		 break;
	        case 3:  mGame.map[posX][posY] = 4;
   		 			 break;
	        case 4:  mGame.map[posX][posY] = 0;
		 			 break;
	        default: mGame.map[posX][posY] = 1;
	        		 break;
		}
	
	}

	public List<Node> getSolution() {
		return solution;
	}

	public void setSolution(List<Node> list) {
		this.solution = list;
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

		// Calcular lado casilla
		lado(g);
		
		// Dibujar cuadricula
		cuadricula(g);

		// Dibujar solución
		drawSolution(g);
		
		// Dibujar objetos
		drawObject(g);
		
	
	}

	public void lado(Graphics g){
		this.altoCasilla = this.getHeight() / mGame.getTamanoY();
		this.anchoCasilla = this.getWidth() / mGame.getTamanoX();

		if (this.altoCasilla > this.anchoCasilla) {
			this.lado = this.anchoCasilla;
		} else {
			this.lado = this.altoCasilla;
		} 
	}
	
	
	public void cuadricula(Graphics g) {
		anchoX = (this.getWidth() - (lado * mGame.getTamanoX()))/2;
		anchoY = (this.getHeight() - (lado * mGame.getTamanoY()))/2;

		g.setColor(Color.GRAY.brighter());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.WHITE.brighter());
		int x,y;
		 for (int i = 0; i < mGame.getTamanoX(); i++){
	            for (int j = 0; j < mGame.getTamanoY(); j++){
	                x = (i * this.lado )+1;
	                y = (j * this.lado )+1;
	                g.fillRect(x+ anchoX, y + anchoY, (this.lado-1), (this.lado-1));
	            }
        }
	}
	
	public void drawObject(Graphics g) {
		
//	         
		 
		int x,y;
		Image img1 = Toolkit.getDefaultToolkit().getImage("Images/r2d2.JPG");
		Image img2 = Toolkit.getDefaultToolkit().getImage("Images/c3po.JPG");
		Image img3 = Toolkit.getDefaultToolkit().getImage("Images/rocks.jpg");
		Image img4 = Toolkit.getDefaultToolkit().getImage("Images/agua.png");
//		//system.out.println("aqui1");
		 for (int i = 0; i < mGame.getTamanoX(); i++){
	            for (int j = 0; j < mGame.getTamanoY(); j++){
				if (mGame.map[i][j] == 1) { // casilla pintada
//					g.setColor(Color.BLACK);
					x = (i * this.lado)+1;
					y = (j * this.lado)+1;
					g.drawImage(img3,x+ anchoX, y + anchoY, (this.lado-1), (this.lado-1),this);
				}
				if (mGame.map[i][j] == 2) { // casilla pintada
//					g.setColor(Color.BLUE);
					x = (i * this.lado)+1;
					y = (j * this.lado)+1;
//					g.fillRect(x, y, (this.altoCasilla-1), (this.altoCasilla-1));
					g.drawImage(img1, x+ anchoX, y + anchoY, (this.lado-1), (this.lado-1),this);
				}
//				if (mGame.mPersonaje[i][j].getEstado() == 3) { // casilla pintada
////					g.setColor(Color.BLUE);
//					x = (i * this.lado)+1;
//					y = (j * this.lado)+1;
////					g.fillRect(x, y, (this.altoCasilla-1), (this.altoCasilla-1));
//					g.drawImage(img2, x+ anchoX, y + anchoY, (this.lado-1), (this.lado-1),this);
//				}
				if (mGame.map[i][j] == 4) { // casilla pintada
					g.setColor(Color.BLUE);
					x = (i * this.lado)+1;
					y = (j * this.lado)+1;
//					g.fillRect(x, y, (this.altoCasilla-1), (this.altoCasilla-1));
					g.drawImage(img4,x+ anchoX, y + anchoY, (this.lado-1), (this.lado-1),this);
				}
			}
		}
	}
	
	public void drawSolution(Graphics g){
		g.setColor(Color.BLUE.brighter());
		int x,y;
		
		for (int v = 0; v < solution.size(); v++) {
			//system.out.println("pintando solucion:"+solution.size());
		       x = (solution.get(v).getX() * this.lado )+1;
               y = (solution.get(v).getY() * this.lado )+1;
               g.fillRect(x+ anchoX, y + anchoY, (this.lado-1), (this.lado-1));
		}
	}
	

}
