from animal import Animal
from config import PINK, GORA, DOL, PRAWO, LEWO
from point import Point


class Human(Animal):
    def __init__(self, world, position):
        super().__init__(world, position, power=5, initiative=4)
        self.color = PINK
        self.immoratality = False
        self.immoratalityRounds = 0

    def getColor(self):
        return self.color

    def getNazwa(self):
        return "Czlowiek"

    def setImmortality(self, imo):
        self.immoratality = imo

    def getImmortality(self):
        return self.immoratality

    def setImmortalityRounds(self, tmp):
        self.immoratalityRounds = tmp

    def getImmortalityRounds(self):
        return self.immoratalityRounds

    def makeNewOrganism(self, position):
        return Human(self.world, position)

    def immortalityFunction(self):
        if self.immoratality:
            self.immoratalityRounds -= 1
            if self.immoratalityRounds < 0:
                self.immoratality = False

    def Action(self):
        self.increaseAge()

        if self.world.humanMove is not None:
            position = self.getPosition()
            direction = self.world.humanMove

            new_x = position.GetX()
            new_y = position.GetY()

            if direction == GORA:
                new_y -= 1
            elif direction == PRAWO:
                new_x += 1
            elif direction == DOL:
                new_y += 1
            elif direction == LEWO:
                new_x -= 1

            newPosition = Point(new_x, new_y)
            self.world.tryToMoveOrganism(self, newPosition)
            self.world.humanMove = None

        self.immortalityFunction()