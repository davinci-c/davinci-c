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
 * Clase que lleva el sentido de orientacion de un Robot.
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class Direccion {

	/** Constante NORTE. */
	public final static int NORTE = 0;

	/** Constante ESTE. */
	public final static int ESTE = 1;

	/** Constante SUR. */
	public final static int SUR = 2;

	/** Constante OESTE. */
	public final static int OESTE = 3;

	/** sentido. */
	private int sentido;

	/**
	 * Instantiates a new direccion.
	 */
	public Direccion() {
		sentido = NORTE;
	}

	/**
	 * rota hacia la derecha.
	 */
	public void derecha() {
		sentido = (sentido + 1) % 4;
	}

	/**
	 * retorna el sentido.
	 * 
	 * @return sentido
	 */
	public int getSentido() {
		return sentido;
	}

	@Override
	public String toString() {
		return (sentido == NORTE ? "Norte" : (sentido == SUR ? "Sur" : (sentido == ESTE ? "Este"
				: "Oeste")));
	}
}
