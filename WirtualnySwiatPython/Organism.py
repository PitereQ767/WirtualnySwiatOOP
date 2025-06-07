from abc import ABC, abstractmethod


class Organism(ABC):
    def __init__(self, world, position, power, initiative):
        self.world = world
        self.position = position
        self.power = power
        self.initiative = initiative
        self.isAlive = True
        self.age = 0

    def getPosition(self):
        return self.position

    def setPosition(self, pos):
        self.position = pos

    def getIsAlive(self):
        return self.isAlive

    def getPower(self):
        return self.power

    @abstractmethod
    def Action(self):
        pass
    @abstractmethod
    def getColor(self):
        pass
    @abstractmethod
    def getNazwa(self):
        pass

    def increaseAge(self):
        self.age += 1