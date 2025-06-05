from abc import ABC, abstractmethod


class Organism(ABC):
    def __init__(self, world, position, power, initiative):
        self.world = world
        self.position = position
        self.power = power
        self.initiative = initiative
        self.isAlive = True

    def getPosition(self):
        return self.position
    def getIsAlive(self):
        return self.isAlive

    # @abstractmethod
    # def Action(self):
    #     pass
    @abstractmethod
    def getColor(self):
        pass