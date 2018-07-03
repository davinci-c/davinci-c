
import socket

import configuracion

import comandos

from robot import Robot

class RobotRemoto(Robot):

    def __init__(self):
        #Nos conectamos al servidor
        if(configuracion.TIPO_EXTRA == None):
            self.canal = socket.socket(configuracion.TIPO_CANAL, configuracion.TIPO_TRANSPORTE)
        else:
            self.canal = socket.socket(configuracion.TIPO_CANAL, configuracion.TIPO_TRANSPORTE, configuracion.TIPO_EXTRA)

        self.canal.connect((configuracion.DIRECCION, configuracion.PUERTO))

    def __ejecutar(self, comando):
        self.canal.send(comando.encode('UTF-8'))
        respuesta = self.canal.recv(configuracion.TAMANO)
        print(comando, ':', respuesta.decode('UTF-8'))

    def iniciar(self):
        self.__ejecutar(comandos.INICIAR)

    def mover(self):
        self.__ejecutar(comandos.MOVER)

    def derecha(self):
        self.__ejecutar(comandos.DERECHA)

    def hayObstaculo(self):
        self.__ejecutar(comandos.HAYOBSTACULO)

    def terminar(self):
        self.__ejecutar(comandos.TERMINAR)