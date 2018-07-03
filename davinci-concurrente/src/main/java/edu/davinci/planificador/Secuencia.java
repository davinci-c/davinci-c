/*******************************************************************************
 * Copyright (c) 2011, 2013  - Daniel, Aguil Mallea.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Daniel, Aguil Mallea - initial API and implementation
 ******************************************************************************/
package edu.davinci.planificador;

import edu.davinci.lenguaje.ParserMyConstants;
import java.util.ArrayList;
import java.util.List;

public class Secuencia {

        //
	private int indice;
	
        /**
         * se modifica la manera de guardar el nombre del hilo para 
         * bajar el consumo de memoria
         * //TODO este arreglo no es eficaz debido a que cada ejecución 
         * guarda su secuencia y esta es finita
         * @daniel.aguil 20140210 - 01:44hs
         */
        //private String hilo;
	
        //
        private int hilo;
        
        //
        private static List<String> nombreHilo = new ArrayList();
        
        //
        private int linea;

        
	public Secuencia() {
		this(0, 0, ParserMyConstants.ROBOT_POR_DEFECTO);
	}

	public Secuencia(int ind, int lin, String nom) {
		indice = ind;
		linea = lin;
		//hilo = nom;
                
                //verificamos si existe el nombre del hilo
                if(!nombreHilo.contains(nom)){
                    //
                    nombreHilo.add(nom);
                }
                
                //almacenamos la posicion del nombre
                hilo = nombreHilo.indexOf(nom);
                
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getIndice() {
		return indice;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public int getLinea() {
		return linea;
	}

	/*public void setHilo(String hilo) {
		this.hilo = hilo;
	}*/

	public String getHilo() {
                //return hilo;
		return nombreHilo.get(hilo);
	}

}
