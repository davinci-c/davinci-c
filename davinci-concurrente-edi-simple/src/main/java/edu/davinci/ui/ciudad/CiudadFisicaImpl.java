/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davinci.ui.ciudad;

import edu.davinci.ui.OpcionesInterprete;
import edu.davinci.ui.ciudadfisica.ClienteSocket;
import edu.davinci.ui.ciudadfisica.ProtocoloRobotFisico;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonatan
 */
public class CiudadFisicaImpl extends CiudadSimpleGuiImpl {

    ClienteSocket cliente = null;
    /*
     * (non-Javadoc)
     * 
     * @see edu.davinci.ciudad.Ciudad#agregarRobot(java.lang.String)
     */

    @Override
    public void agregarRobot(String nombre) {

        RobotFisicoImpl robotf = new RobotFisicoImpl(nombre, this);

        try {

//            System.out.println("habilitar robotfisico: " + OpcionesInterprete.getHabilitarRobotFisico());
            if (OpcionesInterprete.getHabilitarRobotFisico()) {
                System.out.println("IP robot: " + OpcionesInterprete.getIpRobotFisico());
            }

            if (OpcionesInterprete.getHabilitarRobotFisico()) {

                String host = OpcionesInterprete.getIpRobotFisico();
                int puerto = OpcionesInterprete.getPuertoRobotFisico();

                if ((ClienteSocket.getSc() == null) || (!ClienteSocket.getSc().isConnected())) {
                    //el socket es null o no esta conectado
                    cliente = new ClienteSocket(host, puerto);
                } else {
                    //el socket no es null y esta conectado
                    //System.out.println("creamos una nueva instacia del socket, pero le seteamos la conexion activa");
                    cliente = new ClienteSocket();
                    cliente.setSc(ClienteSocket.getSc());
                }

                System.out.println("----inicializamos los componentes del robot -----");
                robotf.setCliente(cliente);

                String respuesta = cliente.enviarPrograma(ProtocoloRobotFisico.INICIAR);

                if (!respuesta.equals(ProtocoloRobotFisico.RESP_OK)) {
                    System.out.println("Ocurrio al mandar la peticion al robot - " + respuesta);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(CiudadFisicaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        // insertamos el robot
        agregarRobot(robotf);
    }

}
