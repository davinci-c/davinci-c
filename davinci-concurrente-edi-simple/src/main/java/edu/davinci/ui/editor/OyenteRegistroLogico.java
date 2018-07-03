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
package edu.davinci.ui.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import edu.davinci.planificador.Secuencia;

public class OyenteRegistroLogico implements PropertyChangeListener {

	private List<Secuencia> secuencia;

	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// System.out.println("registrando secuencia de ejecución...");
		secuencia = (List<Secuencia>) evt.getNewValue();
	}

	public List<Secuencia> getSecuencia() {
		return secuencia;
	}

	public void limpiar() {
		secuencia = null;

	}

}
