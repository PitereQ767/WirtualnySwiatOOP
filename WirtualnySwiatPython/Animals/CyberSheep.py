import math

from Plants.BarszczSosnowskiego import BarszczSosnowskiego
from animal import Animal
from config import BLUE
from point import Point


class CyberSheep(Animal):
    def __init__(self, world, position):
        super().__init__(world, position, power=11, initiative=4)
        self.color = BLUE

    def getColor(self):
        return self.color

    def getNazwa(self):
        return "CyberOwca"

    def makeNewOrganism(self, position):
        return CyberSheep(self.world, position)

    def Action(self):
        position = self.getPosition()
        barszcze = []
        minDist = 100000000000000000000
        closest = None

        for organism in self.world.organisms:
            if isinstance(organism, BarszczSosnowskiego):
                barszcze.append(organism.getPosition())

        for barszcz in barszcze:
            dist = self.distance(position, barszcz)
            if (dist < minDist):
                minDist = dist
                closest = Point(barszcz.GetX(), barszcz.GetY())

        if closest is not None:
            self.increaseAge()
            dx = (closest.GetX() > position.GetX()) - (closest.GetX() < position.GetX())
            dy = (closest.GetY() > position.GetY()) - (closest.GetY() < position.GetY())

            new_pos = Point(position.GetX() + dx, position.GetY() + dy)

            self.world.tryToMoveOrganism(self, new_pos)
        else:
            super().Action()

    def distance(self, A, B):
        dx = A.GetX() - B.GetX()
        dy = A.GetY() - B.GetY()
        return math.sqrt(dx*dx + dy*dy)