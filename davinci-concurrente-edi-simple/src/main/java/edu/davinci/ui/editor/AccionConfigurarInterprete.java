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

import edu.davinci.interprete.Estado;
import edu.davinci.ui.EditorSencillo;
import edu.davinci.ui.editor.configurador.EditorVisualInterprete;
import edu.davinci.ui.editor.configurador.Persistidor;
import edu.davinci.ui.editor.configurador.PersistidorPropiedadesInterprete;
import java.io.File;

public class AccionConfigurarInterprete extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private EditorSencillo editor;

	public AccionConfigurarInterprete(EditorSencillo editor) {
		super("Configurar Interprete", getImage("images/ide/config.png"));
		this.editor = editor;

		// cargar propiedades por defecto

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {

			if (editor.getEjecutor() == null
					|| editor.getEjecutor().getEstado().equals(Estado.PARADO)) {

				
                            String archivoConfiguracion = System.getProperty("user.dir") + System.getProperty("file.separator") + "configuracion.properties";
                            File archivo = new File(archivoConfiguracion);
                            Persistidor persistidor = new PersistidorPropiedadesInterprete(archivo);
                            EditorVisualInterprete evi = new EditorVisualInterprete(persistidor,editor);
                            evi.setVisible(true);
                            
			} else if (editor.getEjecutor().getEstado().equals(Estado.PAUSADO)) {

				// continuamos la ejecucion
				System.out.println("el interprete se encuentra pausado");

			} else if (editor.getEjecutor().getEstado().equals(Estado.CORRIENDO)) {

				// Ya se encuentra corriendo
				System.out.println("el interprete se encuentra corriendo");

			} else if (editor.getEjecutor().getEstado().equals(Estado.DEBUG)) {

				System.out.println("el interprete se encuentra listo para depurar");

			} else {

				// Otro estado
				System.out.println("Se encuentra en estado " + editor.getEjecutor().getEstado());
			}

		} catch (RuntimeException re) {
			System.out.println("Error durante la configuración: " + re.getMessage());
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
