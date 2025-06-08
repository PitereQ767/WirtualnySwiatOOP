from plant import Plant
from config import CYAN

class Guarana(Plant):
    def __init__(self, world, position):
        super().__init__(world, position, power=0)
        self.color = CYAN

    def getNazwa(self):
        return "Guarana"

    def getColor(self):
        return self.color

    def makeNewOrganism(self, position):
        return Guarana(self.world, position)

    def Collision(self, attacker):
        attacker.setPower(attacker.getPower() + 3)

        self.collisionHelper(attacker, self.getPosition())