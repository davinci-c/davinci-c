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
import java.io.UnsupportedEncodingException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import edu.davinci.ciudad.Ciudad;
import edu.davinci.interprete.Estado;
import edu.davinci.interprete.Interprete;
import edu.davinci.lenguaje.ParseException;
import edu.davinci.ui.EditorSencillo;
import edu.davinci.ui.OpcionesInterprete;

public class AccionEjecutarNuevamente extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private EditorSencillo editor;

	public AccionEjecutarNuevamente(EditorSencillo editor) {
		super("Re-ejecutar", getImage("images/ide/run-again.png"));
		this.editor = editor;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {

			if (editor.getEjecutor() == null
					|| editor.getEjecutor().getEstado().equals(Estado.PARADO)) {

				// limpiamos la consola de salida
				editor.getSalida().setText("");

				// instanciamos el interprete
				editor.setInterprete(new Interprete());

				// seteamos la ciudad al interprete
				Ciudad ciudadInicializada = editor.getCiudadInicializada();
                                
				editor.getInterprete().setCiudad(ciudadInicializada);

				// seteamos el planificador
				editor.getInterprete().setPlanificador(
						editor.getPlanificadorRepetitivo(editor.getRegitroLogicoSecuencia()
								.getSecuencia()));

				// seteamos el codigo fuente (flujo de entrada) al inteprete
				editor.getInterprete().setCodigoFuente(editor.getTextoEntrada());

				// recuperamos el codigo intepretable
				editor.setEjecutor(editor.getInterprete().getEjecutor());

				// agregamos el oyente de finalizacion
				// editor.getEjecutor().registerStopListener(editor.getRegitroLogicoSecuencia());

				// agergamos el oyente de la depuracion
				editor.getEjecutor().registerDebugChangeListener(editor.getDepurador());

				// agregamos el oyente de cambios de estado
				editor.getEjecutor().registerOyenteCambiosEstado(editor.getEstadoEjecucionEditor());

				// agregamos el oyente de finalizacion
				editor.getEjecutor().registerStopListener(editor.getRegitroLogicoSecuencia());

				// seteamos la velocidad de retardo
				editor.getEjecutor().setVelocidadRetardo(OpcionesInterprete.getRetardo());

				// iniciamos depuracion de tareas
				editor.getEjecutor().debug();

				// insertamos la ciudad en el editor
				editor.insertarCiudad(ciudadInicializada);

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

		} catch (    UnsupportedEncodingException | ParseException e) {
			System.out.println(e.getMessage());
		} catch (RuntimeException re) {
			System.out.println("Error durante la ejecución: " + re.getMessage());
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
