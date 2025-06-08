import random

from animal import Animal
from config import LIME

class Turtle(Animal):
    def __init__(self, world, position):
        super().__init__(world, position, power=2, initiative=1)
        self.color = LIME
    
    def getNazwa(self):
        return "Zolw"
    
    def getColor(self):
        return self.color

    def makeNewOrganism(self, position):
        return Turtle(self.world, position)
    
    def Action(self):
        ranNum = random.randint(0, 99)
        if ranNum < 75:
            self.increaseAge()
            self.world.addEvent(f"Zolw zostaje w miejscu na pozycji ({self.getPosition().GetX()}, {self.getPosition().GetY()})")
        else:
            super().Action()

    def Collision(self, attacker):
        if self.getNazwa() == attacker.getNazwa():
            self.Multiplication(attacker)
            return

        if attacker.getPower() < 5:
            self.world.addEvent(f"Zolw odparl atak {attacker.getNazwa()} na poztcji ({self.getPosition().GetX()}, {self.getPosition().GetY()})")
            return

        self.collisionHelper(attacker, self.position)