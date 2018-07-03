/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class implements java socket client
 *
 * @author pankaj
 *
 */
public class SocketClientExample {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream mensajeEnviado = null;
        ObjectInputStream mensajeRecibido = null;
        
        for (int i = 0; i < 5; i++) {
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);
            //write to socket using ObjectOutputStream
            mensajeEnviado = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Envianto mensaje al servidor");
            
            if (i == 4) {
                mensajeEnviado.writeObject("exit");
            } else {
                mensajeEnviado.writeObject("joni " + i);
            }
            
            System.out.println("available = "+socket.getInputStream().available());
            //read the server response message
            mensajeRecibido = new ObjectInputStream(socket.getInputStream());
            
            String message = (String) mensajeRecibido.readObject();
            System.out.println("Cliente - Mensaje recibido: " + message);
            //close resources
            mensajeRecibido.close();
            mensajeEnviado.close();
            Thread.sleep(100);
        }
    }
}
