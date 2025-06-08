from plant import Plant
from config import GREEN

class Grass(Plant):
    def __init__(self, world, position):
        super().__init__(world, position, power=0)
        self.color = GREEN

    def getColor(self):
        return self.color

    def getNazwa(self):
        return "Trawa"

    def makeNewOrganism(self, position):
        return Grass(self.world, position)
