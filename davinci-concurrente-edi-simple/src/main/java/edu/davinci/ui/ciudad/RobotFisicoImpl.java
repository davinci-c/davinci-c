/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davinci.ui.ciudad;

import edu.davinci.ciudad.Ciudad;
import edu.davinci.ui.OpcionesInterprete;
import edu.davinci.ui.ciudadfisica.ClienteSocket;
import edu.davinci.ui.ciudadfisica.ProtocoloRobotFisico;

/**
 *
 * @author jonatan
 */
public class RobotFisicoImpl extends RobotSimpleGuiImpl {

    private ClienteSocket cliente;

    public RobotFisicoImpl(String nombre, Ciudad ciudad) {
        super(nombre, ciudad);
    }

    @Override
    public void derecha() {
        super.derecha(); //To change body of generated methods, choose Tools | Templates.

        if (OpcionesInterprete.getHabilitarRobotFisico()) {

            //System.out.println("MI DERECHA");
            String respuesta = cliente.enviarPrograma(ProtocoloRobotFisico.DERECHA);

            if (!respuesta.equals(ProtocoloRobotFisico.RESP_OK)) {
                if (respuesta.equals(ProtocoloRobotFisico.RESP_TTPRES)) {
                    System.out.println(String.format("No se pudo ejecutar %s - El boton de pánico esta siendo presionado", ProtocoloRobotFisico.DERECHA));
                } else {
                    System.out.println("Ocurrio al mandar la peticion al robot - " + respuesta);
                }
            }
            //
            esperar(750);
        }
    }

    @Override
    public void mover() {
        super.mover(); //To change body of generated methods, choose Tools | Templates.

        if (OpcionesInterprete.getHabilitarRobotFisico()) {

            String respuesta = cliente.enviarPrograma(ProtocoloRobotFisico.MOVER);

            if (!respuesta.equals(ProtocoloRobotFisico.RESP_OK)) {
                if (respuesta.equals(ProtocoloRobotFisico.RESP_TTPRES)) {
                    System.out.println(String.format("No se pudo ejecutar %s - El boton de pánico esta siendo presionado", ProtocoloRobotFisico.MOVER));
                } else {
                    System.out.println("Ocurrio al mandar la peticion al robot - " + respuesta);
                }
            }

            //
            esperar(750);
        }
    }

    @Override
    public boolean hayObstaculo() {

        boolean hayObstaculo = false;
        String respuesta = "";

        if (OpcionesInterprete.getHabilitarRobotFisico()) {

            respuesta = cliente.enviarPrograma(ProtocoloRobotFisico.HAYOBSTACULO);

            if (respuesta.equals(ProtocoloRobotFisico.RESP_TTPRES)) {
                System.out.println(String.format("No se pudo ejecutar %s - El boton de pánico esta siendo presionado", ProtocoloRobotFisico.HAYOBSTACULO));
                hayObstaculo = true;
            } else {
                //verificamos que no hay obstaculo en la ciudad fisica o en la ciudad EDIS
                hayObstaculo = (respuesta.equals(ProtocoloRobotFisico.RESP_V) || super.hayObstaculo());
            }

            //
            esperar(750);
        } else {
            hayObstaculo = super.hayObstaculo();
        }

        return hayObstaculo;
    }

    public ClienteSocket getCliente() {
        return cliente;
    }

    public void setCliente(ClienteSocket cliente) {
        this.cliente = cliente;
    }

    /**
     *
     * @param tiempo
     */
    private void esperar(long tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (Exception ignored) {
        }
    }

}
