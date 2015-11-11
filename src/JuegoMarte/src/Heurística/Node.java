package Heurística;

import java.util.ArrayList;
import java.util.List;

import Útiles.Map;

/**
 * Esta clase representa un nodo del mapa de celdas del algoritmo del A*.
 * Indica cuál es su posición (x,y) en ese mapa, así como su valor F, G, H.
 */
public class Node implements Comparable<Node> {
	List<Node> neighbors = new ArrayList<Node>();
    Node parent;
    int f;
    int g;
    int h;
    int x;
    int y;
    int cost;

	/** CONSTRUCTOR */
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.parent = null;
	}
	/** GETTERS AND SETTERS */
	public Node getParent() {
		return parent;
	}
	public void setParent(Node padre) {
		this.parent = padre;
	}
	public int getG() {
		return g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

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
	
	public boolean equal(Node nodo) {
            return ((this.x == nodo.x) && ( this.y  == nodo.y));
	}
	
	@Override
    public int compareTo(Node nodo) {
       if (this.f < nodo.f) {
               return -1;
       } else if (this.f > nodo.f) {
               return 1;
       } else {
               return 0;
       }
    }

	public void setG(int result) {
		this.g = result;
		
	}

	public void definirVecinos(Map mGame) {
		this.neighbors.clear();
		if (this.x>0) { // has north
	    	if (mGame.map[this.x-1][this.y] != 1) {
	    		this.neighbors.add(new Node(this.x-1,this.y));
	    	}
	    }
		
	    if (this.x < mGame.getTamanoX()-1) {     // has south
	    	if (mGame.map[this.x+1][this.y] != 1) {
	    			this.neighbors.add(new Node(this.x+1,this.y));
	    	}
	    }
	    if (this.y>0) {     // has oeste
	    	if (mGame.map[this.x][this.y-1] != 1) {
	    		this.neighbors.add(new Node(this.x,this.y-1));
	      }
	    }
	    
	    if (this.y < mGame.getTamanoY()-1){ // has sur
	    	if (mGame.map[this.x][this.y+1] != 1) {
	    		this.neighbors.add(new Node(this.x,this.y+1));
	    	}
	    }
			    
			    
	}
}
