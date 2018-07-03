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

public class DatoTexto implements Dato {
	private String valor;

	/**
	 * Contructor publico
	 */
	public DatoTexto() {
		setValorPorDefecto();
	}

	/**
	 * Constructor publico
	 * 
	 * @param valor
	 */
	public DatoTexto(String valor) {
		this.valor = new String(valor);
	}

	/**
	 * 
	 */
	@Override
	public String getValor() {
		return valor;
	}

	/**
	 * 
	 */
	@Override
	public void setValor(Object o) {
		valor = (String) o;
	}

	/**
	 * Concatena los valores y retorna el resultado
	 * 
	 * @param d
	 * @return DatoTexto
	 */
	public String suma(DatoTexto d) {
		// valor += d.getValor();
		return valor + d.getValor();
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return valor;
	}

	@Override
	public void setValorPorDefecto() {
		valor = new String();

	}
}
