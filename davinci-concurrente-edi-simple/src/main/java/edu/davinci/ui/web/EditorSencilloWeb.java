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
package edu.davinci.ui.web;

/*
 Java Swing, 2nd Edition
 By Marc Loy, Robert Eckstein, Dave Wood, James Elliott, Brian Cole
 ISBN: 0-596-00408-7
 Publisher: O'Reilly 
 */

// SimpleEditor.java
//An example showing several DefaultEditorKit features. This class is designed
//to be easily extended for additional functionality.
//

import java.io.IOException;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.davinci.ui.EditorSencillo;

public class EditorSencilloWeb extends JApplet implements ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 326097328364372772L;

	// Called when this applet is loaded into the browser.
	@Override
	public void init() {
		// Execute a job on the event-dispatching thread; creating this applet's
		// GUI.
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					try {
						createGUI();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			System.err.println("createGUI didn't complete successfully");
		}
	}

	private void createGUI() throws IOException {
		// Create and set up the content pane.
		EditorSencillo editor = new EditorSencillo();
		editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// editor.setVisible(true);

		setContentPane(editor.getLayeredPane());
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

}
