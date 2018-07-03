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
package edu.davinci.lenguaje;

/**
 * The Class Parameter.
 */
public class Parameter {

	/** identificador. */
	private String identificador;

	/** tipo. */
	private int tipo;

	/** es de salida. */
	private boolean esSalida;

	/**
	 * Instantiates a new parameter.
	 */
	public Parameter() {
		this(null, 0, false);
	}

	/**
	 * Instantiates a new parameter.
	 * 
	 * @param id
	 * @param type
	 * @param isOutPut
	 */
	public Parameter(String id, int type, boolean isOutPut) {
		identificador = id;
		tipo = type;
		esSalida = isOutPut;
	}

	/**
	 * Es salida.
	 * 
	 * @return true, if successful
	 */
	public boolean esSalida() {
		return esSalida;
	}

	/**
	 * retorno el identificador.
	 * 
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * retorna el tipo.
	 * 
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * retorna si es de salida.
	 * 
	 * @param isOutPut
	 * 
	 */
	public void setEsSalida(boolean isOutPut) {
		esSalida = isOutPut;
	}

	/**
	 * Setea el identificador.
	 * 
	 * @param id
	 * 
	 */
	public void setIdentificador(String id) {
		identificador = id;
	}

	/**
	 * Setea el tipo.
	 * 
	 * @param type
	 * 
	 */
	public void setTipo(int type) {
		tipo = type;
	}
}
