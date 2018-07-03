
import java.net.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 *
 *
 * @author Jorge V
 *
 */
public class test {

    final static String HOST = "192.168.0.107";

    final static int PUERTO = 2048;

    static Socket sc;

    static DataOutputStream mensaje;

    static DataInputStream entrada;

    public static void main(String[] args) {
        initClient();
    }

//Cliente
    public static void initClient() /*ejecuta este metodo para correr el cliente */ {

        try {

            ObjectInputStream mensajeRecibido = null;
            sc = new Socket(HOST, PUERTO); /*conectar a un servidor en localhost con puerto 5000*/

            //creamos el flujo de datos por el que se enviara un mensaje

            mensaje = new DataOutputStream(sc.getOutputStream());
            //enviamos el mensaje
            String programa = "INICIAR";
            mensaje.write(programa.getBytes(StandardCharsets.UTF_8));

            mensaje.flush();
            //read the server response message
            System.out.println("lectura: " + obtenerResultado(sc.getInputStream()));

//            //cerramos la conexión
            Thread.sleep(5000);
//
            mensaje = new DataOutputStream(sc.getOutputStream());
            //enviamos el mensaje
            programa = "MOVER";
            mensaje.write(programa.getBytes(StandardCharsets.UTF_8));
            System.out.println("lectura: " + obtenerResultado(sc.getInputStream()));
//
//            //read the server response message
//            ois = new ObjectInputStream(sc.getInputStream());
//
//            message = (String) ois.readObject();
//            System.out.println("Cliente - Mensaje recibido: " + message);

            mensaje.flush();
            sc.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        }

    }

    public static String obtenerResultado(InputStream is) {
        String resultado = "";

        try {
            byte[] data = new byte[16 * 1024];
            int bytesRead = is.read(data);
            System.out.println("==" + bytesRead);

            //
            resultado = byteToString(data);

        } catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public static String byteToString(byte[] _bytes) {
        String file_string = "";

        for (int i = 0; i < _bytes.length; i++) {
            file_string += (char) _bytes[i];
        }

        return file_string;
    }
}
