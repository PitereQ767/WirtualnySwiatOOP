import random
from abc import ABC, abstractmethod

from point import Point


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

    def setIsAlive(self, tmp):
        self.isAlive = tmp

    def getPower(self):
        return self.power

    def setPower(self, tmp):
        self.power = tmp

    def getInitiative(self):
        return self.initiative

    def setInitiative(self, tmp):
        self.initiative = tmp

    def setAge(self, tmp):
        self.age = tmp

    def getAge(self):
        return self.age

    @abstractmethod
    def Action(self):
        pass
    @abstractmethod
    def getColor(self):
        pass
    @abstractmethod
    def getNazwa(self):
        pass

    @abstractmethod
    def makeNewOrganism(self, position):
        pass

    def increaseAge(self):
        self.age += 1

    def findPosition(self):
        positions = []
        current = self.getPosition()

        x = current.GetX()
        y = current.GetY()

        directions = [
            Point(x, y -1),
            Point(x + 1, y),
            Point(x, y + 1),
            Point(x - 1, y)
        ]

        for dir in directions:
            if self.world.isCorrectPosition(dir) and self.world.isEmptyPosition(dir):
                positions.append(dir)

        if not positions:
            return None

        return random.choice(positions)

    def collisionHelper(self, attacker, position):
        if attacker.getPower() >= self.getPower():
            self.setIsAlive(False)
            self.world.addEvent(
                f"{attacker.getNazwa()} zabił {self.getNazwa()} na pozycji ({position.GetX()}, {position.GetY()})")

            if attacker.getIsAlive():
                self.world.moveOrganism(attacker, position)

        else:
            from Animals.Human import Human
            if isinstance(attacker, Human):
                self.world.killHuman()
                return

            attacker.setIsAlive(False)
            self.world.addEvent(
                f"{self.getNazwa()} zabił {attacker.getNazwa()} na pozycji ({position.GetX()}, {position.GetY()})")

            if self.getIsAlive():
                self.world.moveOrganism(self, position)

