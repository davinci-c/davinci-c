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

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import edu.davinci.ui.EditorSencillo;
import edu.davinci.ui.OpcionesInterprete;
import edu.davinci.ui.ciudadfisica.ClienteSocket;
import edu.davinci.ui.ciudadfisica.ProtocoloRobotFisico;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class AccionSalir extends AbstractAction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private EditorSencillo editor;

    public AccionSalir(EditorSencillo editor) {
        super("Exit", getImage("images/ide/exit.png"));
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {

        boolean puedeSalir = false;
        JFileChooser chooser = new JFileChooser();

        String nombreArchivo = (editor.getSelectedFile() == null) ? "" : "en " + editor.getSelectedFile().getAbsolutePath();
        String preguntaArchivo = String.format("¿ Desea guardar los cambios %s ?", nombreArchivo);
        int pregunta = JOptionPane.showConfirmDialog(editor, preguntaArchivo, "Guardar ?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (pregunta == JOptionPane.OK_OPTION) {

            // verifico si tengo archivo para ofrecer guardado
            if (editor.getSelectedFile() == null) {

                // verificamos si continua con el guardado
                if ((chooser.showSaveDialog(editor) == JFileChooser.APPROVE_OPTION)) {

                    // obtengo la referencia para el archivo que se quiere guardar
                    File file = chooser.getSelectedFile();

                    // verifico si se selecciono algo
                    if ((file != null)) {

                        boolean continuarConGuardado = true;

                        // verificamos si el archivo existe
                        if (file.exists()) {

                            // existe, verificamos si el usuario quiere remplazarlo?
                            int respuesta = JOptionPane.showConfirmDialog(editor,
                                    "Seguro quiere sobreescribir el archivo?", "Confirmar",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                            continuarConGuardado = (respuesta == JOptionPane.YES_OPTION);

                        }

                        // verifico si se continua con la intencion de guardar
                        if (continuarConGuardado) {
                            // guardo la referencia del archivo en el editor
                            editor.setSelectedFile(file);

                            //guardar
                            puedeSalir = guardar(editor.getSelectedFile());

                        }

                    }
                }

            } else {
                // quiere guardar y tenemos el nombre del archivo
                puedeSalir = guardar(editor.getSelectedFile());
            }

            //no quiere guardar, solo quiere salir    
        } else if (pregunta == JOptionPane.NO_OPTION) {
            puedeSalir = true;
        }

        //verifico si puedo continuar con la salida
        if (puedeSalir) {

            if (OpcionesInterprete.getHabilitarRobotFisico()) {
                ClienteSocket cliente = new ClienteSocket();
                cliente.setSc(ClienteSocket.getSc());

                String respuesta = cliente.enviarPrograma(ProtocoloRobotFisico.TERMINAR);

                if (!respuesta.equals(ProtocoloRobotFisico.RESP_OK)) {
                    System.out.println("Ocurrio al mandar la peticion al robot - " + respuesta);
                }
            }

            System.exit(0);
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

    private boolean guardar(File selectedFile) {

        boolean sinError = false;

        FileWriter writer = null;
        try {
            writer = new FileWriter(editor.getSelectedFile());
            editor.getEntrada().write(writer);
            System.out.println("El archivo se guardó en "
                    + editor.getSelectedFile().getAbsolutePath());
            sinError = true;

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(editor, "El archivo no se pudo guardar", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException x) {
                }
            }
        }
        return sinError;
    }
}
