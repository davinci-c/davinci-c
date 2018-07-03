
import ev3dev.ev3 as ev3

import respuestas

from robot import Robot

class RobotLocal(Robot):

    # constructor
    def __init__(self):
        self.motord = None
        self.motori = None
        self.motores = []
        self.sensorc = None
        self.sensori = None
        self.sensort = None
        self.sonido = None

    # reset de motores
    def __reset_motores(self):
        for motor in self.motores:
            motor.reset()
            motor.position = 0

    # detencion de motores
    def __detener_motores(self):
        for motor in self.motores:
            motor.stop()

    # inicializacion de motores
    def __iniciar_motores(self):
        self.motord = ev3.LargeMotor(ev3.OUTPUT_B)
        self.motori = ev3.LargeMotor(ev3.OUTPUT_C)
        self.motores = [self.motord, self.motori]
        assert all([motor.connected for motor in self.motores]), "Motores no conectados!!"
        self.__reset_motores()

    # inicializacion de sensor de color
    def __iniciar_sensor_color(self):
        self.sensorc = ev3.ColorSensor()
        self.sensorc.mode = ev3.ColorSensor.MODE_COL_REFLECT
        assert self.sensorc.connected, 'Sensor de color no detectado!!'

    # inicializacion de sensor infrarojo
    def __iniciar_sensor_infrarojo(self):
        self.sensori = ev3.InfraredSensor()
        self.sensori.mode = ev3.InfraredSensor.MODE_IR_PROX
        assert self.sensori.connected, 'Sensor de infrarojo no detectado!!'

    # inicializacion de sensor tactil
    def __iniciar_sensor_tactil(self):
        self.sensort = ev3.TouchSensor()
        assert self.sensort.connected, 'Sensor tactil no detectado!!'

    # inicializacion de sonido
    def __iniciar_sonidos(self):
        self.sonido = ev3.Sound
    

    # iniciar
    def iniciar(self):
        self.__iniciar_motores()
        self.__iniciar_sensor_color()
        self.__iniciar_sensor_infrarojo()
        self.__iniciar_sensor_tactil()
        self.__iniciar_sonidos()

        self.sonido.play('sounds/iniciar.wav').wait()
        self.sonido.beep()

        return respuestas.OK

    # mover
    def mover(self):
        self.__reset_motores()

        #Constantes
        kp = 0.5                #Constante de proporcionalidad

        minv = 10                      #Punto minimo del rango de valores (blanco es 0 )
        maxv = 80                      #Punto maximo del rango de valores (Negro es 100)
        esperado = (maxv + minv) / 2   #Punto medio del rango y valor esperado 0..100 -> 50
        potencia  = 50                 #Potencia objetivo de los motores


        self.motori.run_direct(duty_cycle_sp=50)
        self.motord.run_direct(duty_cycle_sp=50)

        #Bucle principal, se detiene al presionar el boton tactil
        while (not(self.sensort.value()) and (self.motori.position < 430 and self.motord.position < 430)): 
          luz         = self.sensorc.value()          #Leemos el sensor de luz
          error       = luz - esperado         #Calcula el error
          correccion  = kp * error             #Calcula el valor de correcion dice que tanto queremos que efectuar la correccion.
          correcciond = potencia - correccion  #Calcula la potencia a aplicar en el motor derecho
          correccioni = potencia + correccion  #Calcula la potencia a aplicar en el motor izquierdo
          #print('Luz %d, motord %d motori %i' % (luz, correcciond, correccioni))
          #print('Posicion MI %d - Posicion MD %d' % (self.motori.position, self.motord.position))
          self.motord.run_direct(duty_cycle_sp=correcciond)  #Aplicamos la correccion al motor derecho
          self.motori.run_direct(duty_cycle_sp=correccioni)  #Aplicamos la correccion al motor izquierdo

        self.motord.stop(stop_action="brake")
        self.motori.stop(stop_action="brake")
        self.__detener_motores()

        if not(self.sensort.value()):
          salida = respuestas.OK
        else:
          salida = respuestas.TTPRES
          self.sonido.play('sounds/boton-en-contacto.wav').wait()

        return salida

    # derecha
    def derecha(self):

        self.sonido.play('sounds/derecha.wav').wait()

        self.__reset_motores()

        if not(self.sensort.value()):
          
          #ADELANTAMOS AL ROBOT PARA CORREGIR LA POSICION
          self.motori.run_to_rel_pos(position_sp=90, speed_sp=150, stop_action="hold")
          self.motord.run_to_rel_pos(position_sp=90, speed_sp=150, stop_action="hold")
          self.motori.wait_while('running')
          self.motord.wait_while('running')
          
          #GIRAMOS AL ROBOT
          self.motori.run_to_rel_pos(position_sp=310, speed_sp=300, stop_action="hold")
          self.motord.run_to_rel_pos(position_sp=-310, speed_sp=300, stop_action="hold") 
          self.motori.wait_while('running')
          self.motord.wait_while('running')
          
          
          #ATRASAMOS AL ROBOT PARA CORREGIR LA POSICION
          self.motori.run_to_rel_pos(position_sp=-90, speed_sp=150, stop_action="hold")
          self.motord.run_to_rel_pos(position_sp=-90, speed_sp=150, stop_action="hold")
          self.motori.wait_while('running')
          self.motord.wait_while('running')
          
          salida = respuestas.OK

        else:
          salida = respuestas.TTPRES     
          self.sonido.play('sounds/boton-en-contacto.wav').wait()

        self.__detener_motores()

        return salida

    # hayObstaculo
    def hayObstaculo(self):

        if not(self.sensort.value()):
          salida = (respuestas.VERDADERO if (self.sensori.value() <= 70) else respuestas.FALSO)
        else:
          salida = respuestas.TTPRES
          self.sonido.play('sounds/boton-en-contacto.wav').wait()

        if (salida == respuestas.VERDADERO):
           self.sonido.play('sounds/hay-un-obstaculo.wav').wait()
           self.sonido.tone([(500, 200, 400)] * 2).wait()

        return salida

    #Terminar
    def terminar(self):
        self.__detener_motores()

        self.sonido.play('sounds/conexion-finalizada.wav').wait()
        self.sonido.tone([(500, 100, 400)] * 3).wait()

        return respuestas.OK
