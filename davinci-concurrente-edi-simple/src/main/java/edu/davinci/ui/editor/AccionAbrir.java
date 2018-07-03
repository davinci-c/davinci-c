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
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import edu.davinci.ui.EditorSencillo;
import edu.davinci.ui.OpcionesInterprete;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class AccionAbrir extends AbstractAction {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private EditorSencillo editor;

	public AccionAbrir(EditorSencillo editor) {

		super("Abrir", getImage("images/ide/open.png"));
		this.editor = editor;
                
                //agregamos la tecla aceleradora
                this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_DOWN_MASK));
                
	}

	public void actionPerformed(ActionEvent ev) {

		String currentPath = (editor.getSelectedFile() == null) ? OpcionesInterprete
				.getDirectorioTrabajo() : editor.getSelectedFile().getPath();

		JFileChooser chooser = new JFileChooser(currentPath);

		if (chooser.showOpenDialog(editor) != JFileChooser.APPROVE_OPTION)
			return;

		editor.setSelectedFile(chooser.getSelectedFile());

		if (editor.getSelectedFile() == null)
			return;

		FileReader reader = null;
		try {
			reader = new FileReader(editor.getSelectedFile());

			editor.getEntrada().read(reader, null);

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(editor, "File Not Found", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} finally {

			((JTextArea) editor.getEntrada()).setTabSize(2);

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException x) {
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
