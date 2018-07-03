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
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.davinci.ciudad.Ciudad;
import edu.davinci.interprete.Estado;
import edu.davinci.interprete.Interprete;
import edu.davinci.lenguaje.ParseException;
import edu.davinci.planificador.Secuencia;
import edu.davinci.ui.EditorSencillo;
import edu.davinci.ui.OpcionesInterprete;

public class AccionEjecutarNuevamenteManual extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private EditorSencillo editor;

	public AccionEjecutarNuevamenteManual(EditorSencillo editor) {
		super("Re-ejecutar (manipulada)", getImage("images/ide/run-again-manual.png"));
		this.editor = editor;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {

			if (editor.getEjecutor() == null
					|| editor.getEjecutor().getEstado().equals(Estado.PARADO)) {

				// create a JTextArea
				JTextArea textArea = new JTextArea(20, 25);
				textArea.setEditable(true);

				// insertamos la secuencia
				List<Secuencia> secuencias = editor.getRegitroLogicoSecuencia().getSecuencia();
				for (Secuencia secuencia : secuencias) {
					textArea.append(String.format("%s | %s | %s\n", secuencia.getHilo(),
							secuencia.getIndice(), secuencia.getLinea()));
				}

				// wrap a scrollpane around it
				JScrollPane scrollPane = new JScrollPane(textArea);

				// display them in a message dialog
				int acepto = JOptionPane.showConfirmDialog(null, scrollPane,
						"Modificar la secuencia lógica. (hilo | indice | línea)",
						JOptionPane.YES_NO_OPTION);

				// verificamos si quiere continuar
				if (acepto == JOptionPane.YES_OPTION) {

					// armamos la nueva secuencia
					boolean secuenciaArmada = true;
					List<Secuencia> nuevaSecuencia = new ArrayList<Secuencia>();
					String linea = "";

					String[] lineas = textArea.getText().split("\\\n");

					// recorro las lieas
					for (int i = 0; i < lineas.length; i++) {
						try {

							linea = lineas[i];

							String nom = linea.split("\\|")[0];

							int ind = Integer.parseInt(linea.split("\\|")[1].trim());
							int lin = Integer.parseInt(linea.split("\\|")[2].trim());
							nuevaSecuencia.add(new Secuencia(ind, lin, nom));
						} catch (Exception e) {
							System.out.println("no se pudo agregar la linea " + i + ". Secuencia:"
									+ linea + "\n" + e.getMessage());
							secuenciaArmada = false;
						}

					}
					// verificamos si se armo bien la secuencia
					if (secuenciaArmada) {

						// limpiamos la consola de salida
						editor.getSalida().setText("");

						// instanciamos el interprete
						editor.setInterprete(new Interprete());

						// seteamos la ciudad al interprete
						Ciudad ciudadInicializada = editor.getCiudadInicializada();
						editor.getInterprete().setCiudad(ciudadInicializada);

						// seteamos el planificador
						editor.getInterprete().setPlanificador(
								editor.getPlanificadorRepetitivo(nuevaSecuencia));

						// seteamos el codigo fuente (flujo de entrada) al
						// inteprete
						editor.getInterprete().setCodigoFuente(editor.getTextoEntrada());

						// recuperamos el codigo intepretable
						editor.setEjecutor(editor.getInterprete().getEjecutor());

						// agergamos el oyente de la depuracion
						editor.getEjecutor().registerDebugChangeListener(editor.getDepurador());

						// agregamos el oyente de cambios de estado
						editor.getEjecutor().registerOyenteCambiosEstado(
								editor.getEstadoEjecucionEditor());

						// agregamos el oyente de finalizacion
						editor.getEjecutor().registerStopListener(
								editor.getRegitroLogicoSecuencia());

						// seteamos la velocidad de retardo
						editor.getEjecutor().setVelocidadRetardo(OpcionesInterprete.getRetardo());

						// iniciamos depuracion de tareas
						editor.getEjecutor().debug();

						// insertamos la ciudad en el editor
						editor.insertarCiudad(ciudadInicializada);

					}

				}

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

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());// e.printStackTrace();
		} catch (RuntimeException re) {
			System.out.println("Error durante la ejecución: " + re.getMessage());
		} catch (ParseException e) {
			System.out.println(e.getMessage());
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
