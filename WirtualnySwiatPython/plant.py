import random

from organism import Organism
from config import SPREAD_CHANCE

class Plant(Organism):
    def __init__(self, world, position, power):
        super().__init__(world, position, power, 0)

    def trySpread(self):
        ranNum = random.randint(0, 99)
        return ranNum < SPREAD_CHANCE

    def Action(self):
        self.increaseAge()
        if (self.trySpread()):
            newPos = self.findPosition()
            if newPos is not None:
                newOrganism = self.makeNewOrganism(newPos)
                self.world.organisms.append(newOrganism)
                self.world.addEvent(f"{newOrganism.getNazwa()} rozmnozyl sie na ({newPos.GetX()}, {newPos.GetY()})")

    def Collision(self, attacker):
        self.collisionHelper(attacker, self.getPosition())