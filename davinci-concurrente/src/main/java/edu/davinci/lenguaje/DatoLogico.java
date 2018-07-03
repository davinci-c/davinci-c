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

public class DatoLogico implements Dato {
	private Boolean valor;

	/**
	 * 
	 */
	public DatoLogico() {
		setValorPorDefecto();
	}

	/**
	 * 
	 * @param b
	 */
	public DatoLogico(boolean b) {
		valor = new Boolean(b);
	}

	/**
	 * 
	 */
	@Override
	public Boolean getValor() {
		return valor;
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public Boolean multiplica(DatoLogico d) {
		// valor &= d.getValor();
		return valor & d.getValor();
	}

	/**
	 * 
	 * @return
	 */
	public Boolean niega() {
		// valor = !valor;
		return !valor;
	}

	/**
	 * 
	 */
	@Override
	public void setValor(Object o) {
		valor = (Boolean) o;

	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public Boolean suma(DatoLogico d) {
		// valor |= d.getValor();
		return valor | d.getValor();
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		// return valor.toString();
		return (valor ? "verdadero" : "falso");
	}

	@Override
	public void setValorPorDefecto() {
		valor = new Boolean(false);
	}
}
