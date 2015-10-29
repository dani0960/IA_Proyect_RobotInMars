package Heurística;

/**
 * Esta clase representa un nodo del mapa de celdas del algoritmo del A*.
 * Indica cuál es su posición (x,y) en ese mapa, así como su valor F, G, H.
 */
public class NodoAEstrella implements Comparable<NodoAEstrella> {
	/** Componente x (columna) de la posición del nodo en el mapa. */
	private int x;
	
	/** Componente y (fila) de la posición del nodo en el mapa. */
	private int y;
	
	/** Distancia segun algoritmo */
	private int coste;

	public NodoAEstrella(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	/** GETTERS AND SETTERS */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
    public int compareTo(NodoAEstrella nodoT) {
		
		return this.getCoste()-nodoT.getCoste();
		
        //int compareage=((NodoAEstrella)nodoT).getCoste();
        /* For Ascending order*/
//		if ( >= nodoT.getCoste()){
//			return this.getCoste();
//			
//		} else {
//			return nodoT.getCoste();
//		}
		

        /* For Descending order do like this */
        //return compareage-this.studentage;
    }

	

}
