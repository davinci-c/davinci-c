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

import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

import edu.davinci.ui.EditorSencillo;
import edu.davinci.ui.OpcionesInterprete;

public class FilteredOutStream extends FilterOutputStream {
	JTextArea component;

	public FilteredOutStream(OutputStream aStream, JTextArea component) {
		super(aStream);
		this.component = component;
	}

	@Override
	public void write(byte b[]) throws IOException {
		String aString = new String(b);

		append(aString);

	}

	@Override
	public void write(byte b[], int off, int len) throws IOException {
		String aString = new String(b, off, len);
		append(aString);
		// ver de pasar el archivo de log
		if (EditorSencillo.LOG_TO_FILE) {
			FileWriter aWriter = new FileWriter("DavinciConcurrente.log", true);
			aWriter.write(aString);
			aWriter.close();
		}
	}

	private void append(final String aString) {

		Runnable runnable = new Runnable() {
			public void run() {

				// Agregamos el texto
				component.append(aString);

				if (component.getDocument().getLength() > OpcionesInterprete
						.getCantidadCaracteres()) {
					try {
						component.getDocument().remove(0, 1000);
					} catch (BadLocationException e) {
						System.out.println("no se pudo remover del log:" + e.getMessage());
					}
				}

				// actualizamos la posicion del cursor
				component.setCaretPosition(component.getDocument().getLength());

			}
		};
		SwingUtilities.invokeLater(runnable);
	}
}
