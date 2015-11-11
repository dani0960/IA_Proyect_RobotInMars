package Útiles;

import java.util.Random;
import Objetos.Personaje;

public class Map {
	public int map[][];	// Mapa para los Personajes.
	private int sizeX;				// Nº columnas en el mapa.
	private int sizeY;				// Nº filas en el mapa.

	/** CONSTRUCTORES */
	public Map() {
		map = new int[10][10];
		this.sizeX = 10;							
		this.sizeY = 10;
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				map[x][y] = 0;
			}
		}
	}
	
	public Map(int tamanoX, int tamanoY) {
		map = new int[tamanoX][tamanoY];  	
		this.sizeX = tamanoX;							
		this.sizeY = tamanoY;							

		for (int x = 0; x < tamanoX; x++) {
			for (int y = 0; y < tamanoY; y++) {
				map[x][y] = 0;
			}
		}
	}

	/** GETTERS AND SETTERS */
	public int getTamanoX() {
		return sizeX;
	}

	public void setTamanoX(int tamanoX) {
		this.sizeX = tamanoX;
	}

	public int getTamanoY() {
		return sizeY;
	}

	public void setTamanoY(int tamanoY) {
		this.sizeY = tamanoY;
	}

	/** MÉTODOS */
	public void clear() {
		for (int x = 0; x < this.getTamanoX(); x++) {
			for (int y = 0; y < this.getTamanoY(); y++) {
				map[x][y] = 0;
			}
		}
	}
	/** MÉTODO que inserta un personaje en el mapa. */
	public void insertarPersonaje (int x, int y, int object) {
		this.map[x][y]= object ;
	}
	
	public int aleatorioX(){
		Random semilla = new Random();
		int posX=1;
		posX = semilla.nextInt(sizeX);
		return posX;
	}
	
	public int aleatorioY(){
		Random semilla = new Random();
		int posY=2;
		posY = semilla.nextInt(sizeY);
		return posY;
	}
}
