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
 * Clase encargada de representar a la "iteracion condicional"
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class TareaCompuestaIteracionCond extends TareaCompuesta {

	/** condicion. */
	private Boolean condicion;

	/** nodo condicion. */
	private SimpleNode nodoCondicion;

	/**
	 * Instantiates a new tarea compuesta iteracion cond.
	 * 
	 * @param nodo
	 * @param contexto
	 */
	public TareaCompuestaIteracionCond(SimpleNode nodo,
			Hashtable<String, Object> contexto) {
		super(nodo, contexto);
		// Seteamos la condicion

		nodoCondicion = (SimpleNode) getNodos().getNodo();
		interpretar(nodoCondicion);
		condicion = ((DatoLogico) SimpleNode.getUltimaOperacion()).getValor();

		// verificamos si se continua
		if (continuaInterpretando()) {
			// apuntamos al proximo nodo
			getNodos().incrementarIndice();
			// seteamos los nodos restantes
			setNodos(getNodos().getNodo());
		} else {
			finalizar();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.tarea.Tarea#ejecutar()
	 */
	@Override
	public void ejecutar() {
		if (continuaInterpretando()) {

			CustomNode.setTablaDeSimbolos(getContexto());

			if (tieneNodosParaInterpretar()) {
				super.ejecutar();
			} else {
				interpretar(nodoCondicion);
				condicion = ((DatoLogico) SimpleNode.getUltimaOperacion())
						.getValor();
				getNodos().reestablecerIndice();
				// verificar si no hay que terminar
				if (!continuaInterpretando())
					finalizar();
			}

		} else
			finalizar();
	}

	/**
	 * Continua interpretando.
	 * 
	 * @return true, if successful
	 */
	protected boolean continuaInterpretando() {
		return condicion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.tarea.TareaCompuesta#getLinea()
	 */
	@Override
	public int getLinea() {
		if (tieneNodosParaInterpretar())
			return ((SimpleNode) getNodos().getNodo()).jjtGetFirstToken().beginLine;
		else
			return nodoCondicion.jjtGetFirstToken().beginLine;
	}

}
