package Útiles;

import Objetos.Robot;

public class MatrizDeJuego {
	public Robot[][] robot;
	private int tamanoX;
	private int tamanoY;

	/** CONSTRUCTOR */
	public MatrizDeJuego() {
		robot = new Robot[10][10];

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				robot[x][y] = new Robot(x, y, 0);
			}
		}
	}
	
	/** CONSTRUCTOR */
	public MatrizDeJuego(int tamanoX, int tamanoY) {
		robot = new Robot[tamanoX][tamanoY];
		this.tamanoX = tamanoX;
		this.tamanoY = tamanoY;

		for (int x = 0; x < tamanoX; x++) {
			for (int y = 0; y < tamanoY; y++) {
				robot[x][y] = new Robot(x, y, 0);
			}
		}
	}

	/** GETTERS AND SETTERS */
	public int getTamanoX() {
		return tamanoX;
	}

	public void setTamanoX(int tamanoX) {
		this.tamanoX = tamanoX;
	}

	public int getTamanoY() {
		return tamanoY;
	}

	public void setTamanoY(int tamanoY) {
		this.tamanoY = tamanoY;
	}

	public void predeterminado() {
		this.robot[2][2].setEstado(1);
		this.robot[3][3].setEstado(1);
		this.robot[91][48].setEstado(1);
		this.robot[92][48].setEstado(1);
		this.robot[87][49].setEstado(1);
		robot[88][49].setEstado(1);
		robot[89][49].setEstado(1);
		robot[90][49].setEstado(1);
		robot[91][49].setEstado(1);
		robot[92][49].setEstado(1);
		robot[93][49].setEstado(1);
		robot[88][50].setEstado(1);
		robot[89][50].setEstado(1);
		robot[91][50].setEstado(1);
		robot[92][50].setEstado(1);
		robot[90][50].setEstado(1);
		robot[89][51].setEstado(1);
		robot[90][51].setEstado(1);
		robot[91][51].setEstado(1);
		robot[90][52].setEstado(1);
	}
}
