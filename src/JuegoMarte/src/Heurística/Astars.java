package Heurística;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Útiles.Map;

public class Astars  {
	List<Node> nodes = new ArrayList<Node>();
	Node a;
	public Astars() {
	}
	
	
	public List<Node> AStars(Node start, Node goal, Map mGame) {
	    ArrayList<Node> open = new ArrayList<Node>();
	    ArrayList<Node> closed = new ArrayList<Node>();

	    start.g = 0;
	    start.h = Manhattan.manhattan(start, goal);
	    start.f = start.g + start.h;

	    open.clear();
	    closed.clear();
	    
	    open.add(start);

    while (true) {
    	
    	 Node current = null;
    	 // Cogemos entre todos los nodos de la lista abierta el de menor f
//        for (Node node : open) {
//            if (current == null || node.f < current.f) {
//                current = node;
//            }
//        }
        
        Collections.sort(open);
        current = open.get(0);
        
        if (current.equal(goal)) {
        	return path(current);
        }

        if (open.size() == 0) {
            throw new RuntimeException("no route");
        }

       

       

        open.remove(current);
        closed.add(current);
        
        current.definirVecinos(mGame);
        for (Node neighbor : current.neighbors) {
//        	System.out.println("size:"+current.neighbors.size());
//        	System.out.print("neighbors: ");
//          for (Node element : current.neighbors) {
//          	System.out.print("("+element.x+", "+element.y+", "+element.f+") - ");
//          }
//          System.out.println();
        	
            if (closed.contains(neighbor)) {
                continue;
            }

//            System.out.println("adyacente:"+ neighbor.getX()+", "+ neighbor.getY());
            
            int nextG = current.g + Manhattan.manhattan(neighbor, current );

//            if (nextG < neighbor.g) {
//                open.remove(neighbor);
//                closed.remove(neighbor);
//            }

            if (!open.contains(neighbor) /*&& !closed.contains(neighbor)*/) {
            	open.add(neighbor);
            	Collections.sort(open);
            }else if (nextG >= neighbor.g) {
            	continue;
            }
            
            neighbor.g = nextG;
            neighbor.h = Manhattan.manhattan(neighbor, goal);
            neighbor.f = neighbor.g + neighbor.h;
            neighbor.setParent(current);
//            System.out.print("ListaAbierta: ");
//            for (Node element : open) {
//                System.out.print("("+element.x+", "+element.y+", "+element.f+") - ");
//            }
//            System.out.println();
//            
//            System.out.print("ListaCerrada: ");
//            for (Node element : closed) {
//            	System.out.print("("+element.x+", "+element.y+", "+element.f+") - ");
//            }
//            System.out.println();
            
//            if(neighbor.equal(goal)) {
//                return path(neighbor);
//            }
            
        }
    }
}

	public List<Node> path (Node nodo) {
	    Node current = nodo;
	    while (current.getParent() != null) {
	    	////System.out.println("Pcurrent:"+ current.getParent().getX()+", "+ current.getParent().getY());
	        nodes.add(current);
	        current = current.getParent();
	    }
//	    nodes.add(start);

	    return nodes;
	}
	
	
	
public int estimateDistance(Node node1, Node node2) {
    return Math.abs(node1.x - node2.x) + Math.abs(node1.y - node2.y);
}

}