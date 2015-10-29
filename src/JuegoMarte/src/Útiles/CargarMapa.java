package Útiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CargarMapa {

	/** MÉTODOS */
	public static MatrizDeJuego CargarMapa(String archivo) throws FileNotFoundException, IOException {
		
        String cadena;
        FileReader fileReader = new FileReader(archivo);		
        @SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(fileReader);
        
    	// SALTAMOS COMENTARIOS INICIALES
    	cadena = bufferedReader.readLine();
    	while (cadena.charAt(0)=='#'){	// Si la cadena[0] contiene '#':
    		cadena = bufferedReader.readLine();		// Salto de linea
    	}
    	System.out.println("==================================");
    	// LINEA 1: Filas y columnas
    	String[] partirCadena = cadena.split(" ");		// Partimos cadena
	    partirCadena[0]= partirCadena[0].trim();		// Eliminamos espacios de delante y de atras.
	    partirCadena[1]= partirCadena[1].trim();
	    MatrizDeJuego mGame = new MatrizDeJuego(Integer.parseInt(partirCadena[0]), Integer.parseInt(partirCadena[1]));
	    
    	// LINEA 2: Almacenamos el mapa
	    int i = 0;
	    cadena = bufferedReader.readLine();
	    while (cadena != null){
	    	
	    	for (int j = 0; j < cadena.length(); j++) {
	    		System.out.println("x: "+ mGame.getTamanoX() + " y: "+ mGame.getTamanoY());
	    		mGame.insertarPersonaje(i, j, Integer.parseInt(""+cadena.charAt(j))); 
	    		System.out.println("i: "+i+" j: "+j+" char: "+Integer.parseInt(""+cadena.charAt(j)) );
	    	}
	    	i++;
	    	cadena = bufferedReader.readLine();
	    }
		return mGame;
    	
    
		    
	    }
}
