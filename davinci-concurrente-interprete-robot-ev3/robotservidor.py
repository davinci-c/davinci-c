
import socket

import configuracion

import comandos

from robotlocal import RobotLocal

class RobotServidor:

    def __init__(self):
        # Nos ponemos a aceptar conexiones por el canal
        if(configuracion.TIPO_EXTRA == None):
            self.canal = socket.socket(configuracion.TIPO_CANAL, configuracion.TIPO_TRANSPORTE)
        else:
            self.canal = socket.socket(configuracion.TIPO_CANAL, configuracion.TIPO_TRANSPORTE, configuracion.TIPO_EXTRA)

        self.canal.bind((configuracion.DIRECCION, configuracion.PUERTO))
        self.canal.listen(1)

        print('EV3 listo para ejecutar ordenes')


    def iniciar(self):
        try:

            cliente, direccion = self.canal.accept()

            robot = RobotLocal()

            comando = None

            while (comando != comandos.TERMINAR):

                comando = cliente.recv(configuracion.TAMANO)
                comando = comando.decode('UTF-8')

                if (comando == comandos.INICIAR):
                    resultado = robot.iniciar()
                    print(comandos.INICIAR, ':', resultado)
                    cliente.send(resultado.encode('UTF-8'))

                elif (comando == comandos.MOVER):
                    resultado = robot.mover()
                    print(comandos.MOVER, ':', resultado)
                    cliente.send(resultado.encode('UTF-8'))

                elif (comando == comandos.DERECHA):
                    resultado = robot.derecha()
                    print(comandos.DERECHA, ':', resultado)
                    cliente.send(resultado.encode('UTF-8'))

                elif (comando == comandos.HAYOBSTACULO):
                    resultado = robot.hayObstaculo()
                    print(comandos.HAYOBSTACULO, ':', resultado)
                    cliente.send(resultado.encode('UTF-8'))

                elif (comando == comandos.TERMINAR):
                    resultado = robot.terminar()
                    print(comandos.TERMINAR, ':', resultado)
                    cliente.send(resultado.encode('UTF-8'))

                    robot = None
                    cliente.close()
                    self.canal.close()

                else:
                    print('comando no reconocido', ':', comando)
                    comando = comandos.TERMINAR
        
        except Exception as e:
            print('ERROR - robotservidor.py - ', e)
            cliente.close()
            self.canal.close()
