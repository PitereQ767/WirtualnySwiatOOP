import random

from animal import Animal
from config import ORANGE
from point import Point


class Fox(Animal):
    def __init__(self, world, position):
        super().__init__(world, position, power=3, initiative=7)
        self.color = ORANGE
        self.attempsToMove = 10

    def getColor(self):
        return self.color

    def getNazwa(self):
        return "Lis"

    def makeNewOrganism(self, position):
        return Fox(self.world, position)

    def Action(self):
        self.increaseAge()
        position = self.getPosition()

        for i in range(self.attempsToMove):
            self.increaseAge()
            direction = random.randint(0, 3)

            new_x = position.GetX()
            new_y = position.GetY()

            if direction == 0:
                new_y -= 1
            elif direction == 1:
                new_x += 1
            elif direction == 2:
                new_y += 1
            elif direction == 3:
                new_x -= 1

            newPosition = Point(new_x, new_y)
            if self.shouldMove(newPosition):
                self.world.tryToMoveOrganism(self, newPosition)
                break

    def shouldMove(self, position):
        x = position.GetX()
        y = position.GetY()
        for organism in self.world.organisms:
            if x == organism.getPosition().GetX() and y == organism.getPosition().GetY() and organism.getPower() > self.getPower():
                return False

        return True