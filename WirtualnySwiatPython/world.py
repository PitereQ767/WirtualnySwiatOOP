import random
from abc import ABC, abstractmethod
from point import Point
from organism import Organism
from config import numberOfOrganisms


class World(ABC):
    def __init__(self, width, height):
        self.width = width
        self.height = height
        self.organisms = []
        self.events = []
        self.isEndGame = False

    def exitGame(self):
        return self.isEndGame

    def setEndGame(self, endGame):
        self.isEndGame = endGame

    @abstractmethod
    def draw(self, screen, font):
        pass

    def createWorld(self):
        for i in range(numberOfOrganisms):
            x = random.randint(0, self.width - 1)
            y = random.randint(0, self.height - 1)
            position = Point(x, y)
            self.organisms.append(Organism(self, position))
