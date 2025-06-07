from animal import Animal
from config import GRAY

class Wolf(Animal):
    def __init__(self, world, position):
        super().__init__(world, position, power=9, initiative=5)
        self.color = GRAY

    def getColor(self):
        return self.color
    def getNazwa(self):
        return "Wilk"