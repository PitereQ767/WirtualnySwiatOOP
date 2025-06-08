import random

from animal import Animal
from config import BROWN
from point import Point


class Antelope(Animal):
    def __init__(self, world, position):
        super().__init__(world, position, power=4, initiative=4)
        self.color = BROWN

    def getNazwa(self):
        return "Antylopa"

    def getColor(self):
        return self.color

    def Action(self):
        self.increaseAge()
        old_position = self.getPosition()
        direction = random.randint(0, 3)

        new_x = old_position.GetX()
        new_y = old_position.GetY()

        if direction == 0:
            new_y -= 2
        elif direction == 1:
            new_x += 2
        elif direction == 2:
            new_y += 2
        elif direction == 3:
            new_x -= 2

        new_position = Point(new_x, new_y)
        self.world.tryToMoveOrganism(self, new_position)

    def makeNewOrganism(self, position):
        return Antelope(self.world, position)

    def Collision(self, attacker):
        if self.getNazwa() == attacker.getNazwa():
            self.Multiplication(attacker)
            return

        ranNum = random.randint(0, 99)

        if ranNum < 50:
            newPos = self.findPosition()
            if newPos is not None:
                self.world.moveOrganism(attacker, self.getPosition())
                self.world.moveOrganism(self, newPos)
                self.world.addEvent(f"{self.getNazwa()} uciekla przed {attacker.getNazwa()} na pozycje ( {newPos.GetX()}, {newPos.GetY()})")
                return

        self.collisionHelper(attacker, self.getPosition())