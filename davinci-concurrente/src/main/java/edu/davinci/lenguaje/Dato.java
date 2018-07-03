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

public interface Dato {

	/**
	 * Retorna el valor
	 * 
	 * @return Object
	 */
	public Object getValor();

	/**
	 * Almacena el valor
	 * 
	 * @param o
	 *            Object
	 */
	public void setValor(Object o);

	/**
	 * Inicializa con el valor por defecto
	 */
	public void setValorPorDefecto();
}
