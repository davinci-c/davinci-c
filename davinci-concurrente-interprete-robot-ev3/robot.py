
from abc import abstractmethod

class Robot:

    @abstractmethod
    def iniciar(self):
        pass

    @abstractmethod
    def mover(self):
        pass

    @abstractmethod
    def derecha(self):
        pass

    @abstractmethod
    def hayObstaculo(self):
        pass