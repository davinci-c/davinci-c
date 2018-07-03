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

import edu.davinci.interprete.Estado;
import edu.davinci.ui.EditorSencillo;

public class OyenteEstadoEjecucion implements PropertyChangeListener {

	private EditorSencillo editor;

	public OyenteEstadoEjecucion(EditorSencillo editor) {
		this.editor = editor;

		editor.getRunAction().setEnabled(true);
		editor.getPauseAction().setEnabled(false);
		editor.getStopAction().setEnabled(false);
		editor.getDebugAction().setEnabled(true);
		editor.getNextAction().setEnabled(false);

		editor.getRunAgainAction().setEnabled(false);
		editor.getRunManualAgainAction().setEnabled(false);

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		try {
			Estado estado = (Estado) evt.getNewValue();
			if (estado.equals(Estado.PARADO)) {

				editor.getRunAction().setEnabled(true);
				editor.getPauseAction().setEnabled(false);
				editor.getStopAction().setEnabled(false);
				editor.getDebugAction().setEnabled(true);
				editor.getNextAction().setEnabled(false);

				// verificar si hay secuencia
				if (haySecuenciaGuardada()) {
					editor.getRunAgainAction().setEnabled(true);
					editor.getRunManualAgainAction().setEnabled(true);
				} else {
					editor.getRunAgainAction().setEnabled(false);
					editor.getRunManualAgainAction().setEnabled(false);
				}

			} else if (estado.equals(Estado.DEBUG)) {
				editor.getRunAction().setEnabled(true);
				editor.getPauseAction().setEnabled(false);
				editor.getStopAction().setEnabled(true);
				editor.getDebugAction().setEnabled(false);
				editor.getNextAction().setEnabled(true);

				//
				editor.getRunAgainAction().setEnabled(false);
				editor.getRunManualAgainAction().setEnabled(false);

			} else if (estado.equals(Estado.CORRIENDO)) {
				editor.getRunAction().setEnabled(false);
				editor.getPauseAction().setEnabled(true);
				editor.getStopAction().setEnabled(true);
				editor.getDebugAction().setEnabled(false);
				editor.getNextAction().setEnabled(false);
				//
				editor.getRunAgainAction().setEnabled(false);
				editor.getRunManualAgainAction().setEnabled(false);

			} else if (estado.equals(Estado.TERMINADO)) {
				editor.getRunAction().setEnabled(true);
				editor.getPauseAction().setEnabled(false);
				editor.getStopAction().setEnabled(false);
				editor.getDebugAction().setEnabled(true);
				editor.getNextAction().setEnabled(false);
				// verificar si hay secuencia
				if (haySecuenciaGuardada()) {
					editor.getRunAgainAction().setEnabled(true);
					editor.getRunManualAgainAction().setEnabled(true);
				} else {
					editor.getRunAgainAction().setEnabled(false);
					editor.getRunManualAgainAction().setEnabled(false);
				}

			} else if (estado.equals(Estado.PAUSADO)) {
				editor.getRunAction().setEnabled(true);
				editor.getPauseAction().setEnabled(false);
				editor.getStopAction().setEnabled(true);
				editor.getDebugAction().setEnabled(false);
				editor.getNextAction().setEnabled(false);
				//
				editor.getRunAgainAction().setEnabled(false);
				editor.getRunManualAgainAction().setEnabled(false);

			} else {
				System.out.println("SE REGISTRO UN EVENTO QUE NO SE PUEDE TRATAR");
			}
		} catch (Exception e) {
			System.out.println("SE REGISTRO UN EVENTO QUE NO SE PUEDe TRATAR:" + evt.getNewValue());
		}
	}

	private boolean haySecuenciaGuardada() {
		return (editor.getRegitroLogicoSecuencia().getSecuencia() != null && editor
				.getRegitroLogicoSecuencia().getSecuencia().size() > 0);
	}
}
