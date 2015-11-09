package Heurística;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Útiles.MatrizDeJuego;

public class AlgoritmoAEstrella  {
	/** Nodo de partida que indica una posición (x,y) en la matriz de celdas. */
	/** Nodo final que indica una posición (x,y) en la matriz de celdas. */
	NodoAEstrella nodoInicial = null;
	NodoAEstrella nodoFinal = null;
	
	NodoAEstrella nodoRobot = null;
	NodoAEstrella nodoAgua = null;
	
	// A*
	public ArrayList<NodoAEstrella> calcularCamino(MatrizDeJuego mGame) {
		// initialize the open list
		ArrayList<NodoAEstrella> listaAbierta = new ArrayList<NodoAEstrella>();
		// initialize the closed list
		ArrayList<NodoAEstrella> listaCerrada = new ArrayList<NodoAEstrella>();
		
		// put the starting node on the open list (you can leave its f at zero)
		for (int i = 0; i < mGame.getTamanoX(); i++) {
			for (int j = 0; j < mGame.getTamanoY(); j++) {
				if (mGame.mPersonaje[i][j].getEstado() == 2) {
					nodoInicial = new NodoAEstrella(i, j, 0, 0, 0);
//					nodoRobot = new NodoAEstrella(i, j);
					System.out.println("nodoInicial: ("+i+", "+j+").");
				}
				if (mGame.mPersonaje[i][j].getEstado() == 4) {
					nodoFinal = new NodoAEstrella(i, j, 0, 0, 0);
//					nodoAgua = new NodoAEstrella(i, j);
					System.out.println("nodoFinal: ("+i+", "+j+").");
				}
			}
		}
		nodoInicial.setCoste( DistanciaManhattan.manhattan( nodoInicial,nodoFinal));
		listaAbierta.add(nodoInicial);
		
		boolean fine = false;
		// while the open list is not empty
		while (!listaAbierta.isEmpty() && fine == false) {
			
			// chivato
			for (int j = 0; j < listaAbierta.size(); j++) {
				System.out.println("ListaAbierta: (x: "+listaAbierta.get(j).getX()+", y: "
				+listaAbierta.get(j).getY()+", F: "+listaAbierta.get(j).getF()+", G: "+listaAbierta.get(j).getG()
				+", H: "+listaAbierta.get(j).getH()+")");
			}
			
			// chivato
			for (int j = 0; j < listaCerrada.size(); j++) {
				System.out.println("listaCerrada: (x: "+listaCerrada.get(j).getX()+", y: "
				+listaCerrada.get(j).getY()+", F: "+listaCerrada.get(j).getF()+", G: "+listaCerrada.get(j).getG()
				+", H: "+listaCerrada.get(j).getH()+")");
			}
//			Scanner in = new Scanner(System.in);
//			String first = in.next();
			
		    // find the node with the least f on the open list, call it "q"
			Collections.sort(listaAbierta);
			NodoAEstrella q = listaAbierta.get(0);
			
		    // pop q off the open list
			listaAbierta.remove(0);
			
		    // generate q's 8 successors and set their parents to q
			ArrayList<NodoAEstrella> noVisitado = adyacentes(q, mGame);
			
		    // for each successor
			for (int i = 0; i < noVisitado.size(); i++) {
				
		    	// if successor is the goal, stop the search
				if(noVisitado.get(i).equal(nodoFinal)) {
					fine = true; break;
				}
				
		        // successor.g = q.g + distance between successor and q
				NodoAEstrella aux = new NodoAEstrella(noVisitado.get(i).getX(), noVisitado.get(i).getY(),
						noVisitado.get(i).getF(), noVisitado.get(i).getH(), noVisitado.get(i).getG());
				
				aux.setG(q.getG()+DistanciaManhattan.manhattan(aux,q));
				
		        // successor.h = distance from goal to successor
				aux.setH(DistanciaManhattan.manhattan(nodoFinal, aux));
				
		        // successor.f = successor.g + successor.h
				aux.setF(aux.getG() + aux.getH());
				
		        // if a node with the same position as successor is in the OPEN list \
		        //   which has a lower f than successor, skip this successor
				boolean encontrado;
				encontrado = true;
				for (int k = 0; k < listaAbierta.size(); k++) {
					if(aux.getX() == listaAbierta.get(k).getX() &&
							aux.getY() == listaAbierta.get(k).getY() &&
									aux.getF() > listaAbierta.get(k).getF()){
						encontrado = false;
					}
				}
				
//		        if a node with the same position as successor is in the CLOSED list \ 
//		            which has a lower f than successor, skip this successor
				for (int k = 0; k < listaCerrada.size(); k++) {
					if(aux.getX() == listaCerrada.get(k).getX() &&
							aux.getY() == listaCerrada.get(k).getY() &&
									aux.getF() > listaCerrada.get(k).getF()){
						encontrado = false;
					}
				}
		        // otherwise, add the node to the open list
				if (encontrado == true) {
					listaAbierta.add(aux);
				}


					
				
		    }
//		    push q on the closed list
			listaCerrada.add(q); }
//		end 
		return listaCerrada;
		}
			
			
			
//			// Nodo Robot Y Nodo Agua
//			for (int i = 0; i < mGame.getTamanoX(); i++) {
//				for (int j = 0; j < mGame.getTamanoY(); j++) {
//					if (mGame.mPersonaje[i][j].getEstado() == 2) {
//						nodoInicial = new NodoAEstrella(i, j);
//						
//						System.out.println("nodoInicial: ("+i+", "+j+").");
//					}
//					if (mGame.mPersonaje[i][j].getEstado() == 4) {
//						nodoFinal = new NodoAEstrella(i, j);
//						System.out.println("nodoFinal: ("+i+", "+j+").");
//					}
//				}
//			}
//			
//			// Agregamos el coste a cada nodo
//			
//			// Agregamos la solución en la lista cerrada
//			listaCerrada.add(nodoInicial);
//			// AGREGAMOS NODOS ADYACENTES:
//			// Eliminamos de la lista abierta la lista cerrada
////			ArrayList<NodoAEstrella> noVisitado = adyacentes(nodoInicial, mGame);
//			for (int i = 0; i < listaCerrada.size()	; i++) {
//				for (int j = 0; j < noVisitado.size(); j++) {
//					if (listaCerrada.get(i).getX() == noVisitado.get(j).getX() &&
//							listaCerrada.get(i).getY() == noVisitado.get(j).getY() ) {
//							noVisitado.remove(j);
////							listaAbierta.remove(i);
//							
//					}
//				}
//				
//			}
//			listaAbierta.addAll(noVisitado);
//			
//			
//		
//			
//			// ORDENAMOS LA LISTA
////			for (int i = 0; i < listaAbierta.size(); i++) {
////				System.out.println("1NodoLista: ("+listaAbierta.get(i).getX()+", "+listaAbierta.get(i).getY()+", "+listaAbierta.get(i).getCoste()+")");
////			}
//			
//			
//			
//			for (int i = 0; i < listaAbierta.size(); i++) {
//				System.out.println("2NodoLista: ("+listaAbierta.get(i).getX()+", "+listaAbierta.get(i).getY()+", "+listaAbierta.get(i).getCoste()+")");
//			}
//		
//		// Posición final
//		//listaCerrada.add(listaAbierta.get(0));
//		// Movemos robot a la nueva posición
//		System.out.println("ME MUEVOOOOOOOO");
//			if (nodoInicial.equal(nodoFinal) != true)
//			{
//				mGame.mPersonaje[listaAbierta.get(0).getX()][listaAbierta.get(0).getY()].setEstado(2);
//			}
//			// Borramos robot de la posicion actual
//			mGame.mPersonaje[nodoInicial.getX()][nodoInicial.getY()].setEstado(0);
//			// Lo eliminamos de la lista abierta
//			listaAbierta.remove(0);
//			}
//		
//		
//			System.out.println("listaCERRADAAA");
//			for (int i = 0; i < listaCerrada.size(); i++) {
//				System.out.println("Solucion: ("+listaCerrada.get(i).getX()+", "+listaCerrada.get(i).getY()+", "+listaCerrada.get(i).getCoste()+")");
//			}
//		
//		//	listaCerrada.add(0, nodoRobot);
//			mGame.mPersonaje[nodoRobot.getX()][nodoRobot.getY()].setEstado(2);
//			mGame.mPersonaje[nodoAgua.getX()][nodoAgua.getY()].setEstado(4);
//	//		listaCerrada.remove(0);
//			listaCerrada.remove(listaCerrada.size()-1);
////			for (int i = 0; i < listaCerrada.size()-1; i++) {
////				if()
////				
////			}
//			return listaCerrada;
//	}
	
	public ArrayList<NodoAEstrella> adyacentes(NodoAEstrella nodoN, MatrizDeJuego mGame){
		ArrayList<NodoAEstrella> listaAbiertaTemporal = new ArrayList<NodoAEstrella>();
		
		if ((nodoN.getX()!=(mGame.getTamanoX()-1)) && (mGame.mPersonaje[nodoN.getX()+1][nodoN.getY()].getEstado() != 1)) {
			NodoAEstrella nodoTemporal = new NodoAEstrella(nodoN.getX()+1,nodoN.getY(), nodoN.getF(), nodoN.getH(), nodoN.getG());
			nodoTemporal.setCoste( DistanciaManhattan.manhattan( nodoTemporal,nodoFinal));
			listaAbiertaTemporal.add(nodoTemporal);
			System.out.println("nodoN: ("+nodoTemporal.getX()+", "+nodoTemporal.getY()+").");
		}
		if ((nodoN.getX()!=0) && (mGame.mPersonaje[nodoN.getX()-1][nodoN.getY()].getEstado() != 1)) {
			NodoAEstrella nodoTemporal = new NodoAEstrella(nodoN.getX()-1, nodoN.getY(), nodoN.getF(), nodoN.getH(), nodoN.getG());
			nodoTemporal.setCoste( DistanciaManhattan.manhattan( nodoTemporal,nodoFinal));
			listaAbiertaTemporal.add(nodoTemporal);
			System.out.println("nodoN: ("+nodoTemporal.getX()+", "+nodoTemporal.getY()+").");
		}
		if ((nodoN.getY()!=(mGame.getTamanoY()-1)) && (mGame.mPersonaje[nodoN.getX()][nodoN.getY()+1].getEstado() != 1)) {
			NodoAEstrella nodoTemporal = new NodoAEstrella(nodoN.getX(), nodoN.getY()+1, nodoN.getF(), nodoN.getH(), nodoN.getG());
			nodoTemporal.setCoste( DistanciaManhattan.manhattan( nodoTemporal,nodoFinal));
			listaAbiertaTemporal.add(nodoTemporal);
			System.out.println("nodoN: ("+nodoTemporal.getX()+", "+nodoTemporal.getY()+").");
		}
		if ((nodoN.getY()!=0) && (mGame.mPersonaje[nodoN.getX()][nodoN.getY()-1].getEstado() != 1)) {
			NodoAEstrella nodoTemporal = new NodoAEstrella(nodoN.getX(), nodoN.getY()-1, nodoN.getF(), nodoN.getH(), nodoN.getG());
			nodoTemporal.setCoste( DistanciaManhattan.manhattan( nodoTemporal,nodoFinal));
			listaAbiertaTemporal.add(nodoTemporal);
			System.out.println("nodoN: ("+nodoTemporal.getX()+", "+nodoTemporal.getY()+").");
		}
		return listaAbiertaTemporal;
	}

	

}
