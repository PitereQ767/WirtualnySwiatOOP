from animal import Animal
from config import LIGHT_GRAY

class Sheep(Animal):
    def __init__(self, world, position):
        super().__init__(world, position, power=4, initiative=4)
        self.color = LIGHT_GRAY

    def getColor(self):
        return self.color

    def getNazwa(self):
        return "Owca"

    def makeNewOrganism(self, position):
        return Sheep(self.world, position)