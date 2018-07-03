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
package edu.davinci.planificador.tarea;

import java.util.Hashtable;

import edu.davinci.lenguaje.CustomNode;
import edu.davinci.lenguaje.DatoLogico;
import edu.davinci.lenguaje.SimpleNode;

/**
 * Clase encargada de representar la "seleccion" Una tarea de este tipo tiene la
 * responsabilidad de identificar el camino logico a seguir en funcion de la
 * condicion.
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class TareaCompuestaSeleccion extends TareaCompuesta {

	/**
	 * Instantiates a new tarea compuesta seleccion.
	 * 
	 * @param nodo
	 * @param contexto
	 */
	public TareaCompuestaSeleccion(SimpleNode nodo,
			Hashtable<String, Object> contexto) {
		// inicializamos la tarea
		super(nodo, contexto);

		// intepretamos la condicion de seleccion
		interpretar((SimpleNode) getNodos().getNodo());

		// traemos el valor de la condicion
		Boolean condicion = ((DatoLogico) SimpleNode.getUltimaOperacion())
				.getValor();

		// apuntamos al proximo nodo
		getNodos().incrementarIndice();

		// verificamos que camino tomar (dependiendo la condicion)
		if (condicion) {
			// seteamos los nodos
			setNodos(getNodos().getNodo());
		} else {
			// apuntamos al proximo nodo
			getNodos().incrementarIndice();

			// verificamos si hay camino por una codicion falsa (camino sino)
			if (getNodos().hayOtroNodo()) {

				// seteamos los nodos
				setNodos(getNodos().getNodo());
			} else {
				// marcamos para finalizar la tarea porq no hay camino por el
				// sino
				finalizar();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.tarea.Tarea#ejecutar()
	 */
	@Override
	public void ejecutar() {
		// verificamos si hay nodos que interpretar
		if (tieneNodosParaInterpretar()) {

			CustomNode.setTablaDeSimbolos(getContexto());

			// interpretamos
			super.ejecutar();

			if (!tieneNodosParaInterpretar())
				finalizar();
		} else {
			// marcamos para finalizar la tarea
			finalizar();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.tarea.TareaCompuesta#getLinea()
	 */
	@Override
	public int getLinea() {
		return ((SimpleNode) getNodos().getNodo()).jjtGetFirstToken().beginLine;
	}

}
