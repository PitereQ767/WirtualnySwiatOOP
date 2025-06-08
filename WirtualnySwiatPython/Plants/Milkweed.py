from plant import Plant
from config import YELLOW

class Milkweed(Plant):
    def __init__(self, world, position):
        super().__init__(world, position, power=0)
        self.color = YELLOW

    def getColor(self):
        return self.color

    def getNazwa(self):
        return "Mlecz"

    def makeNewOrganism(self, position):
        return Milkweed(self.world, position)

    def Action(self):
        self.increaseAge()

        for i in range(3):
            if (self.trySpread()):
                newPos = self.findPosition()
                if newPos is not None:
                    newOrganism = self.makeNewOrganism(newPos)
                    self.world.organisms.append(newOrganism)
                    self.world.addEvent(f"{self.getNazwa()} rozmnozyl sie na pozysji ({newPos.GetX()}, {newPos.GetY()})")
                    break