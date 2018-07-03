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

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import edu.davinci.ui.EditorSencillo;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public class AccionNuevo extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EditorSencillo editor;

	public AccionNuevo(EditorSencillo editor) {

		super("nuevo", getImage("images/ide/new.png"));
		this.editor = editor;
                
                //agregamos la tecla aceleradora
                this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK));
                
	}

	public void actionPerformed(ActionEvent ev) {

		editor.setSelectedFile(null);

		editor.getEntrada().setText("");

		editor.getRegitroLogicoSecuencia().limpiar();

		if (editor.getEjecutor() != null)
			editor.getEjecutor().stop();

		editor.getSalida().setText("nuevo archivo...");
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
