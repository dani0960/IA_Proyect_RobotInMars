package Objetos;

import java.awt.Color;

public class Personaje {
	private int estado;
//	private Color color = color.BLUE;
	
	/** CONSTRUCTORES */
	public Personaje(int posX, int posY, int estado) {
		this.estado = estado;
	}
	
	public Personaje() {
		this.estado = 0;
	}

	/** GETTERS AND SETTERS */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public int getEstado() {
		return this.estado;
	}
	
}


