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

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import edu.davinci.ui.EditorSencillo;

public class AccionGuardarComo extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EditorSencillo editor;

	public AccionGuardarComo(EditorSencillo editor) {

		super("Guardar como...", getImage("images/ide/save-as.png"));
		this.editor = editor;
	}

	/**
	 * 
	 */
	public void actionPerformed(ActionEvent ev) {
		// Selector de archivo
		JFileChooser chooser = new JFileChooser();

		// verificamos si continua con el guardado
		boolean continuarConGuardado = !(chooser.showSaveDialog(editor) != JFileChooser.APPROVE_OPTION);

		if (continuarConGuardado) {

			// obtengo la referencia para el archivo que se quiere guardar
			File file = chooser.getSelectedFile();

			// verifico si se selecciono algo
			if ((file != null)) {

				// verificamos si el archivo existe
				if (file.exists()) {

					// existe, verificamos si el usuario quiere remplazarlo?
					int response = JOptionPane.showConfirmDialog(editor,
							"Seguro quiere sobreescribir el archivo?", "Confirmar",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					continuarConGuardado = (response == JOptionPane.YES_OPTION);

				}

				if (continuarConGuardado) {
					FileWriter writer = null;
					try {
						writer = new FileWriter(file);
						editor.getEntrada().write(writer);
						editor.setSelectedFile(file);
						System.out.println("El archivo se guardó en "
								+ editor.getSelectedFile().getAbsolutePath());

					} catch (IOException ex) {
						JOptionPane.showMessageDialog(editor, "El archivo no se pudo guardar",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					} finally {
						if (writer != null) {
							try {
								writer.close();
							} catch (IOException x) {
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param recurso
	 * @return
	 */
	private static ImageIcon getImage(String recurso) {
		return new ImageIcon(EditorSencillo.class.getResource(recurso));
	}

}
