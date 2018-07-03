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

import edu.davinci.utils.Resource;

public class DatoEntero implements Dato {
	private Integer valor;

	/**
	 * 
	 */
	public DatoEntero() {
		setValorPorDefecto();
	}

	/**
	 * 
	 * @param valor
	 */
	public DatoEntero(int valor) {
		this.valor = new Integer(valor);
	}

	/**
	 * 
	 * @return
	 */
	public Integer getValor() {
		return valor;
	}

	public Integer divide(DatoEntero d) {
		// division por cero
		if (d.getValor() == 0)
			throw new RuntimeException(Resource.getString("error.division.cero"));
		return valor / d.getValor();
	}

	public Integer multiplica(DatoEntero d) {
		// valor *= d.getValor();
		return valor * d.getValor();

	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public Integer resta(DatoEntero d) {
		// valor -= d.getValor();
		return valor - d.getValor();
	}

	@Override
	public void setValor(Object o) {
		valor = (Integer) o;

	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public Integer suma(DatoEntero d) {
		// valor += d.getValor();
		return valor + d.getValor();
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return valor.toString();
	}

	@Override
	public void setValorPorDefecto() {
		valor = new Integer(0);

	}

	/**
	 * Retorna el modulo de la operacion (valor del objeto % d)
	 * 
	 * @param d
	 * @return
	 */
	public Integer modulo(DatoEntero d) {
		// division por cero
		if (d.getValor() == 0)
			throw new RuntimeException(Resource.getString("error.modulo.cero"));
		return valor % d.getValor();
	}
}
