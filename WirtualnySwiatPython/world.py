import random
from abc import ABC, abstractmethod

from Animals.Antelope import Antelope
from Animals.CyberSheep import CyberSheep
from Animals.Fox import Fox
from Animals.Human import Human
from Animals.Sheep import Sheep
from Animals.Turtle import Turtle
from Animals.Wolf import Wolf
from Plants.BarszczSosnowskiego import BarszczSosnowskiego
from point import Point
from organism import Organism
from config import numberOfTypeOrganims, numberOfOrganisms


class World(ABC):
    def __init__(self, width, height):
        self.width = width
        self.height = height
        self.organisms = []
        self.events = []
        self.isEndGame = False
        self.humanMove = None

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

    def addEvent(self, event):
        self.events.append(event)

    def createRandomOrganism(self, position):
        randomNum = random.randint(0, numberOfTypeOrganims -1)

        if randomNum == 0:
            return Wolf(self, position)
        elif randomNum == 1:
            return Antelope(self, position)
        elif randomNum == 2:
            return BarszczSosnowskiego(self, position)
        elif randomNum == 3:
            return CyberSheep(self, position)
        elif randomNum == 4:
            return Fox(self, position)
        elif randomNum == 5:
            return Sheep(self, position)
        elif randomNum == 6:
            return Turtle(self, position)

        return None


    def createWorld(self):
        humanPosition = self.getEmptyAndCorrectPosition()
        self.organisms.append(Human(self, humanPosition))
        for i in range(numberOfOrganisms):
            position = self.getEmptyAndCorrectPosition()
            organism = self.createRandomOrganism(position)
            self.organisms.append(organism)

    def moveOrganism(self, org, position):
        org.setPosition(position)

    def tryToMoveOrganism(self, org, position):
        if not self.isCorrectPosition(position):
            self.addEvent(org.getNazwa() + " probowal wyjsc poza mape")
        else:
            if self.isEmptyPosition(position):
                self.moveOrganism(org, position)
                self.addEvent(f"{org.getNazwa()} przesunal sie na ({position.GetX()}, {position.GetY()})")

    def makeRun(self):
        for organism in self.organisms:
            organism.Action()