import random
from abc import ABC, abstractmethod

from Animals.Wolf import Wolf
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

    def isEmptyPosition(self, position):
        for organism in self.organisms:
            if position == organism.getPosition():
                return False
        return True

    def isCorrectPosition(self, position):
        x, y = position
        if x < 0 or y < 0 or x >= self.width or y >= self.height:
            return False
        return True

    def getEmptyAndCorrectPosition(self):
        while True:
            x = random.randint(0, self.width - 1)
            y = random.randint(0, self.height - 1)
            pos = Point(x, y)
            if (self.isEmptyPosition(pos) and self.isCorrectPosition(pos)): return pos


    def createWorld(self):
        for i in range(numberOfOrganisms):
            position = self.getEmptyAndCorrectPosition()
            self.organisms.append(Wolf(self, position))
