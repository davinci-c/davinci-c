/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davinci.ui.ciudadfisica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonatan
 */
public class ClienteSocket {

    private String HOST;
    private int PUERTO;

    private static Socket sc;

    private DataOutputStream mensajeEnviado;

    /**
     *
     */
    public ClienteSocket() {
    }

    public ClienteSocket(String h, int p) {
        this.HOST = h;
        this.PUERTO = p;

        try {
            sc = new Socket(Inet4Address.getByName(HOST), PUERTO);

        } catch (Exception ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Cliente
    /*ejecuta este metodo para correr el cliente */
    public String enviarPrograma(String programa) {

        String respuesta = "";

        try {
            //creamos el flujo de datos por el que se enviara un mensaje
            mensajeEnviado = new DataOutputStream(sc.getOutputStream());

            //enviamos el mensaje
            mensajeEnviado.write(programa.getBytes(StandardCharsets.UTF_8));

            mensajeEnviado.flush();

            respuesta = obtenerRespuesta(sc.getInputStream());
            //System.out.println("respuesta: " + respuesta);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return respuesta;
    }

    /**
     *
     * @param is
     * @return
     */
    public String obtenerRespuesta(InputStream is) {
        String resultado = "";

        try {
            byte[] data = new byte[16 * 1024];
            int bytesRead = is.read(data);

            //leemos el resultado
            resultado = byteToString(data);

        } catch (IOException ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado.trim();
    }

    /**
     *
     * @param _bytes
     * @return
     */
    public String byteToString(byte[] _bytes) {
        String file_string = "";

        for (int i = 0; i < _bytes.length; i++) {
            file_string += (char) _bytes[i];
        }

        return file_string;
    }

    public static Socket getSc() {
        return sc;
    }

    public static void setSc(Socket sc) {
        ClienteSocket.sc = sc;
    }

}
