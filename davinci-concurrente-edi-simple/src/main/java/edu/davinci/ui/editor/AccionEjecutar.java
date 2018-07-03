/**
 * *****************************************************************************
 * Copyright (c) 2011, 2013 - Daniel, Aguil Mallea. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the GNU Public License v3.0 which accompanies this distribution, and is
 * available at http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Daniel, Aguil Mallea - initial API and implementation
 * ****************************************************************************
 */
package edu.davinci.ui.editor;

import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import edu.davinci.ciudad.Ciudad;
import edu.davinci.interprete.Estado;
import edu.davinci.interprete.Interprete;
import edu.davinci.lenguaje.ParseException;
import edu.davinci.lenguaje.TokenMgrError;
import edu.davinci.ui.EditorSencillo;
import edu.davinci.ui.OpcionesInterprete;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.KeyStroke;

public class AccionEjecutar extends AbstractAction {

    private static final long serialVersionUID = 1L;
    private EditorSencillo editor;

    public AccionEjecutar(EditorSencillo editor) {
        super("Ejecutar / Continuar", getImage("images/ide/run.png"));
        this.editor = editor;

        //agregamos la tecla aceleradora
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        try {

            if (editor.getEjecutor() == null
                    || editor.getEjecutor().getEstado().equals(Estado.PARADO)) {

                // limpiamos la consola de salida
                editor.getSalida().setText("");

                // limpiamos la consola de debug
                editor.getDebug().setText("");

                // instanciamos el interprete
                editor.setInterprete(new Interprete());

                //
                editor.getInterprete().setASTModoDebug(OpcionesInterprete.getDepurarAst());

                //
                editor.getInterprete().setLexerModoDebug(OpcionesInterprete.getDepurarLexer());

                // seteamos la ciudad al interprete
                Ciudad ciudadInicializada = editor.getCiudadInicializada();

                editor.getInterprete().setCiudad(ciudadInicializada);

                // seteamos el planificador
                editor.getInterprete().setPlanificador(
                        OpcionesInterprete.getInstanciaPlanificador());

                // seteamos el codigo fuente (flujo de entrada) al inteprete
                editor.getInterprete().setCodigoFuente(editor.getTextoEntrada());

                // recuperamos el codigo intepretable
                editor.setEjecutor(editor.getInterprete().getEjecutor());

                // agergamos el oyente de la depuracion
                // editor.getEjecutor().registerDebugChangeListener(editor.getDepurador(),
                // Depurador.SIN_LINEA);

                // agregamos el oyente de cambios de estado
                editor.getEjecutor().registerOyenteCambiosEstado(editor.getEstadoEjecucionEditor());

                // agregamos el oyente de finalizacion
                editor.getEjecutor().registerStopListener(editor.getRegitroLogicoSecuencia());

                // seteamos la velocidad de retardo
                editor.getEjecutor().setVelocidadRetardo(OpcionesInterprete.getRetardo());

                // agergamos el oyente de la depuracion
                // ejecutor.registerDebug(depurador);
                // insertamos la ciudad en el editor
                editor.insertarCiudad(ciudadInicializada);

                // arancamos la ejecucion
                editor.getEjecutor().start();

            } else if (editor.getEjecutor().getEstado().equals(Estado.PAUSADO)) {

                // continuamos la ejecucion
                editor.getEjecutor().resume();

            } else if (editor.getEjecutor().getEstado().equals(Estado.CORRIENDO)) {

                // Ya se encuentra corriendo
                System.out.println("Ya se encuentra corriendo");

            } else if (editor.getEjecutor().getEstado().equals(Estado.DEBUG)) {

                // agergamos el oyente de la depuracion
                editor.getEjecutor().removerDebugChangeListener(editor.getDepurador());

                // limpiamos la consola de debug
                editor.getDebug().setText("");

                // corremos lo que queda de ejecucion
                editor.getEjecutor().start();

            } else {

                // Otro estado
                System.out.println("Se encuentra en estado " + editor.getEjecutor().getEstado());
            }

        } catch (UnsupportedEncodingException e) {
			System.out.println("Encoding> Error durante la ejecución, codificación no soportada");
        } catch (RuntimeException re) {
			System.out.println("Ejecucion> Error durante la ejecución: " + re.getMessage());
        } catch (ParseException e) {
			System.out.println("Sintáctico> " + e.getMessage());
        } catch (TokenMgrError error) {
			System.out.println("Léxico> " + error.getMessage());
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
