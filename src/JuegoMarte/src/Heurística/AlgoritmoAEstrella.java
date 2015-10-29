package Heurística;

import java.util.ArrayList;
import java.util.Collections;

import Útiles.MatrizDeJuego;

public class AlgoritmoAEstrella  {
	/** Nodo de partida que indica una posición (x,y) en la matriz de celdas. */
	/** Nodo final que indica una posición (x,y) en la matriz de celdas. */
	NodoAEstrella nodoInicial = null;
	NodoAEstrella nodoFinal = null;
	
	public ArrayList calcularCamino(MatrizDeJuego mGame) {
		ArrayList<NodoAEstrella> listaCerrada = new ArrayList<NodoAEstrella>();
		ArrayList<NodoAEstrella> listaAbierta = new ArrayList<NodoAEstrella>();
		
		
		// Posición inicial
		
		for (int i = 0; i < mGame.getTamanoX(); i++) {
			for (int j = 0; j < mGame.getTamanoY(); j++) {
				if (mGame.mPersonaje[i][j].getEstado() == 2) {
					nodoInicial = new NodoAEstrella(i, j);
					
					System.out.println("nodoInicial: ("+i+", "+j+").");
				}
				if (mGame.mPersonaje[i][j].getEstado() == 4) {
					nodoFinal = new NodoAEstrella(i, j);
					System.out.println("nodoFinal: ("+i+", "+j+").");
				}
			}
		}
		nodoInicial.setCoste( DistanciaManhattan.manhattan( nodoInicial,nodoFinal));
		listaCerrada.add(nodoInicial);
		
		listaAbierta.addAll(adyacentes(nodoInicial, mGame));
		
		
		for (int i = 0; i < listaAbierta.size(); i++) {
			System.out.println("1NodoLista: ("+listaAbierta.get(i).getX()+", "+listaAbierta.get(i).getY()+", "+listaAbierta.get(i).getCoste()+")");
		}
		
		Collections.sort(listaAbierta);
		
		for (int i = 0; i < listaAbierta.size(); i++) {
			System.out.println("2NodoLista: ("+listaAbierta.get(i).getX()+", "+listaAbierta.get(i).getY()+", "+listaAbierta.get(i).getCoste()+")");
		}
		
		// Posición final
		
		
		
		return listaAbierta;
	}
	
	public ArrayList<NodoAEstrella> adyacentes(NodoAEstrella nodoN, MatrizDeJuego mGame){
		ArrayList listaAbiertaTemporal = new ArrayList<NodoAEstrella>();
		
		if ((nodoN.getX()!=(mGame.getTamanoX()-1)) && (mGame.mPersonaje[nodoN.getX()+1][nodoN.getY()].getEstado() != 1)) {
			NodoAEstrella nodoTemporal = new NodoAEstrella(nodoN.getX()+1,nodoN.getY());
			nodoTemporal.setCoste( DistanciaManhattan.manhattan( nodoTemporal,nodoFinal));
			listaAbiertaTemporal.add(nodoTemporal);
			System.out.println("nodoN: ("+nodoTemporal.getX()+", "+nodoTemporal.getY()+").");
		}
		if ((nodoN.getX()!=0) && (mGame.mPersonaje[nodoN.getX()-1][nodoN.getY()].getEstado() != 1)) {
			NodoAEstrella nodoTemporal = new NodoAEstrella(nodoN.getX()-1, nodoN.getY());
			nodoTemporal.setCoste( DistanciaManhattan.manhattan( nodoTemporal,nodoFinal));
			listaAbiertaTemporal.add(nodoTemporal);
			System.out.println("nodoN: ("+nodoTemporal.getX()+", "+nodoTemporal.getY()+").");
		}
		if ((nodoN.getY()!=(mGame.getTamanoY()-1)) && (mGame.mPersonaje[nodoN.getX()][nodoN.getY()+1].getEstado() != 1)) {
			NodoAEstrella nodoTemporal = new NodoAEstrella(nodoN.getX(), nodoN.getY()+1);
			nodoTemporal.setCoste( DistanciaManhattan.manhattan( nodoTemporal,nodoFinal));
			listaAbiertaTemporal.add(nodoTemporal);
			System.out.println("nodoN: ("+nodoTemporal.getX()+", "+nodoTemporal.getY()+").");
		}
		if ((nodoN.getY()!=0) && (mGame.mPersonaje[nodoN.getX()][nodoN.getY()-1].getEstado() != 1)) {
			NodoAEstrella nodoTemporal = new NodoAEstrella(nodoN.getX(), nodoN.getY()-1);
			nodoTemporal.setCoste( DistanciaManhattan.manhattan( nodoTemporal,nodoFinal));
			listaAbiertaTemporal.add(nodoTemporal);
			System.out.println("nodoN: ("+nodoTemporal.getX()+", "+nodoTemporal.getY()+").");
		}
		return listaAbiertaTemporal;
	}

	
	 /**
     * Ejecuta el algoritmo de A*, calculando el camino, si existe, desde el
     * punto de inicio hasta el punto final.
     * @return Devuelve una lista (no vacía) de nodos si existe el camino. Si no existe, devuelve null.
     */
//    public ArrayList calcularCamino()
//    {
//            Deap listaAbierta = new Deap();
//            ArrayList listaCerrada = new ArrayList<NodoAstar>();
//            NodoAstar nodoActual = null;
//            boolean caminoEncontrado = false;
//            
//            int filas = matriz.length;
//            int columnas = 0;
//            if (filas > 0)
//                    columnas = matriz[0].length;
//            
//            // Añadimos el cuadro inicial a la lista abierta.
//            listaAbierta.push(nodoInicial);
//
//            int iteraciones = 0;
//            // Buscamos el camino mientras queden nodos candidatos y no lo hayamos encontrado.
//            while (!listaAbierta.isEmpty() && !caminoEncontrado)
//            {
//                    iteraciones++;
//                    // Extraemos el nodo de menor F desde la lista abierta hacia la lista cerrada.
//                    nodoActual = (NodoAstar) listaAbierta.popBottom();
//                    listaCerrada.add(nodoActual);
//
//                    // Extraemos los nodos adyacentes al nodo actual.
//                    ArrayList nodosAdyacentes = new ArrayList<NodoAstar>();
//
//                    boolean derecha = false, izquierda = false, arriba = false, abajo = false;
//                    if (0 <= nodoActual.getX()+1 && nodoActual.getX()+1 < columnas && 0 <= nodoActual.getY() && nodoActual.getY() < filas)
//                    {
//                            if (matriz[nodoActual.getY()][nodoActual.getX()+1].getTransitable())
//                            {
//                                    nodosAdyacentes.add(matriz[nodoActual.getY()][nodoActual.getX()+1]);
//                                    derecha = true;
//                            }
//                    }
//                    if (0 <= nodoActual.getX()-1 && nodoActual.getX()-1 < columnas && 0 <= nodoActual.getY() && nodoActual.getY() < filas)
//                    {
//                            if (matriz[nodoActual.getY()][nodoActual.getX()-1].getTransitable())
//                            {
//                                    nodosAdyacentes.add(matriz[nodoActual.getY()][nodoActual.getX()-1]);
//                                    izquierda = true;
//                            }
//                    }
//                    if (0 <= nodoActual.getX() && nodoActual.getX() < columnas && 0 <= nodoActual.getY()-1 && nodoActual.getY()-1 < filas)
//                    {
//                            if (matriz[nodoActual.getY()-1][nodoActual.getX()].getTransitable())
//                            {
//                                    nodosAdyacentes.add(matriz[nodoActual.getY()-1][nodoActual.getX()]);
//                                    arriba = true;
//                            }
//                    }
//                    if (0 <= nodoActual.getX() && nodoActual.getX() < columnas && 0 <= nodoActual.getY()+1 && nodoActual.getY()+1 < filas)
//                    {
//                            if (matriz[nodoActual.getY()+1][nodoActual.getX()].getTransitable())
//                            {
//                                    nodosAdyacentes.add(matriz[nodoActual.getY()+1][nodoActual.getX()]);
//                                    abajo = true;
//                            }
//                    }
//
//                    // Sólo incluidos las diagonales si las ortogonales se han incluido previamente ya que para ser 8-conectado primero debe ser 4-conectado.
//                    if (0 <= nodoActual.getX()+1 && nodoActual.getX()+1 < columnas && 0 <= nodoActual.getY()-1 && nodoActual.getY()-1 < filas && (forzar8conectado || (arriba && derecha)))
//                            if (matriz[nodoActual.getY()-1][nodoActual.getX()+1].getTransitable())
//                                    nodosAdyacentes.add(matriz[nodoActual.getY()-1][nodoActual.getX()+1]);
//
//                    if (0 <= nodoActual.getX()-1 && nodoActual.getX()-1 < columnas && 0 <= nodoActual.getY()-1 && nodoActual.getY()-1 < filas && (forzar8conectado || (arriba && izquierda)))
//                            if (matriz[nodoActual.getY()-1][nodoActual.getX()-1].getTransitable())
//                                    nodosAdyacentes.add(matriz[nodoActual.getY()-1][nodoActual.getX()-1]);
//
//                    if (0 <= nodoActual.getX()+1 && nodoActual.getX()+1 < columnas && 0 <= nodoActual.getY()+1 && nodoActual.getY()+1 < filas && (forzar8conectado || (abajo && derecha)))
//                            if (matriz[nodoActual.getY()+1][nodoActual.getX()+1].getTransitable())
//                                    nodosAdyacentes.add(matriz[nodoActual.getY()+1][nodoActual.getX()+1]);
//                    
//                    if (0 <= nodoActual.getX()-1 && nodoActual.getX()-1 < columnas && 0 <= nodoActual.getY()+1 && nodoActual.getY()+1 < filas && (forzar8conectado || (abajo && izquierda)))
//                            if (matriz[nodoActual.getY()+1][nodoActual.getX()-1].getTransitable())
//                                    nodosAdyacentes.add(matriz[nodoActual.getY()+1][nodoActual.getX()-1]);
//
//                    // Para cada nodo encontrado, comprobamos si hemos llegado al punto de destino.
//                    while (!nodosAdyacentes.isEmpty() && !caminoEncontrado)
//                    {
//                            NodoAstar nodoAdyacente = (NodoAstar) nodosAdyacentes.remove(0);
//                            if (!listaCerrada.contains(nodoAdyacente))
//                            {
//                                    if (!listaAbierta.contains(nodoAdyacente))
//                                    {
//                                            nodoAdyacente.setNodoPadre(nodoActual);
//                                            listaAbierta.push(nodoAdyacente);
//
//                                            if (nodoFinal == nodoAdyacente)
//                                            {
//                                                    caminoEncontrado = true;
//                                            }
//                                    }
//                                    else
//                                    {
//                                            int nuevoG = nodoActual.getG();
//                                            if (nodoAdyacente.getX()==nodoActual.getX() || nodoAdyacente.getY()==nodoActual.getY())
//                                                    nuevoG += 10;
//                                            else
//                                                    nuevoG += 14;
//
//                                            if (nuevoG < nodoAdyacente.getG())
//                                            {
//                                                    nodoAdyacente.setNodoPadre(nodoActual);
//                                                    listaAbierta.reordenar();
//                                            }
//                                    }
//                            }
//                    }
//            }
//
//
//            // Si hemos llegado al nodo final, volvemos hacia atrás desde ese nodo extrayendo el camino hasta el nodo inicial.
//            if (caminoEncontrado)
//            {
//                    ArrayList camino = new ArrayList<NodoAstar>();
//                    NodoAstar nodoAuxiliar = nodoFinal;
//                    while (nodoAuxiliar != null)
//                    {
//                            camino.add(0, nodoAuxiliar);
//                            nodoAuxiliar = nodoAuxiliar.getNodoPadre();
//                    }
//                    return camino;
//            }
//            else
//            {
//                    return null;
//            }
//    }
}
