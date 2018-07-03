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
package edu.davinci.ciudad;

/**
 * Un elemento es cualquier cosa que se quiera posicionar dentro de la ciudad
 * 
 * @author dany
 * 
 */
public class Elemento {
	protected int avenida;
	protected int calle;

	public Elemento() {
		this(1, 1);
	}

	public Elemento(int av, int ca) {
		avenida = av;
		calle = ca;
	}

	public void setCalle(int calle) {
		this.calle = calle;
	}

	public int getCalle() {
		return calle;
	}

	public void setAvenida(int avenida) {
		this.avenida = avenida;
	}

	public int getAvenida() {
		return avenida;
	}

}
