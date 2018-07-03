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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import edu.davinci.interprete.Depurador;

public class RegistroCambioDepurador implements PropertyChangeListener {

	private JTextArea entrada;
	private JTextArea debug;

	public RegistroCambioDepurador(JTextComponent entrada, JTextComponent debug) {
		this.entrada = (JTextArea) entrada;
		this.debug = (JTextArea) debug;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evento) {

		// Obtenemos la propiedad del evento
		String propiedad = evento.getPropertyName();

		// Verificamos si es la linea
		if (propiedad.equals(Depurador.PROPIEDAD_LINEA)) {

			// Obtenemos la linea
			int linea = (Integer) evento.getNewValue();

			// Agregamos el texto al debug
			// debug.append("Linea:" + linea + "\n");

			try {
				// posicionamos al cursor
				entrada.setCaretPosition(entrada.getLineStartOffset(linea - 1));
				// actualizamos la entrada
				entrada.getCaret().paint(entrada.getGraphics());
				// tln.caretUpdate(null);

			} catch (Exception e) {
				System.out.println(e.getMessage());// e.printStackTrace();
			}

		} else if (propiedad.equals(Depurador.PROPIEDAD_HILO)) {

			debug.append("Hilo:" + (String) evento.getNewValue());

		} else if (propiedad.equals(Depurador.PROPIEDAD_VARIABLES)) {

			// Verificamos si son las variables
			debug.setText("");
			/**
			 * Cambios introducidos el 20110817. Ver
			 * edu.davinci.interprete.Depurador.java
			 */

			if (null != evento) {
				LinkedHashMap<String, Object> variables = (LinkedHashMap<String, Object>) evento
						.getNewValue();
				Set<String> keys = variables.keySet();
				Iterator<String> iterator = keys.iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					if (key != "Ciudad")
						debug.append(key + ":" + variables.get(key) + "\n");
				}
				// numeration<String> keys = variables.keys();
				// while (keys.hasMoreElements()) {
				// String key = keys.nextElement();
				// if (key != "Ciudad")
				// debug.append(key + ":" + variables.get(key) + "\n");
				// }
			}
		} else {
			System.out.println("Evento no registrado");
		}
	}
}
