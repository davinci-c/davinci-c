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
import edu.davinci.lenguaje.DatoEntero;
import edu.davinci.lenguaje.SimpleNode;

/**
 * Clase encargada de representar a la "iteracion incondicional"
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class TareaCompuestaIteracionIncond extends TareaCompuesta {

	/** The nodo iteracion. */
	private SimpleNode nodoIteracion;

	/** The iteraciones. */
	private Integer iteraciones;

	/**
	 * Instantiates a new tarea compuesta iteracion incond.
	 * 
	 * @param nodo
	 * @param contexto
	 */
	public TareaCompuestaIteracionIncond(SimpleNode nodo,
			Hashtable<String, Object> contexto) {
		super(nodo, contexto);
		// Seteamos las iteraciones
		nodoIteracion = (SimpleNode) getNodos().getNodo();
		interpretar(nodoIteracion);
		iteraciones = ((DatoEntero) SimpleNode.getUltimaOperacion()).getValor();

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
				iteraciones--;
				getNodos().reestablecerIndice();
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
	private boolean continuaInterpretando() {
		return iteraciones > 0;
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
			return nodoIteracion.jjtGetFirstToken().beginLine;
	}

}
