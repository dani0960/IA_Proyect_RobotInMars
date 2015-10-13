package Objetos;



public class Robot {
	private int posX;
	private int posY;
	private int estado;
	private int estadoSiguiente;
	// color
	
	/** CONSTRUCTORES */
	public Robot(int posX, int posY, int estado) {
		this.posX = posX;
		this.posY = posY;
		this.estado = estado;
//		color = Color.BLACK;
	}
	
	public Robot() {
		this.estado = 0;
//		color = Color.BLACK;
	}

	/** GETTERS AND SETTERS */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public int getEstado() {
		return this.estado;
	}
	
	public void setEstadoSiguiente(int estadoSiguiente) {
		this.estadoSiguiente = estadoSiguiente;
	}
	
	public int getEstadoSiguiente() {
		return this.estadoSiguiente;
	}
}


