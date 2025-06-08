from Animals.Human import Human
from plant import Plant
from config import PURPLE

class Berries(Plant):
    def __init__(self, world, position):
        super().__init__(world, position, power=99)
        self.color = PURPLE

    def getNazwa(self):
        return "WilczeJagody"

    def getColor(self):
        return self.color

    def makeNewOrganism(self, position):
        return Berries(self.world, position)

    def Collision(self, attacker):
        if isinstance(attacker, Human):
            self.world.killHuman()
            return

        attacker.setIsAlive(False)
        self.world.addEvent(f"{attacker.getNazwa()} zjadl wilcze jagody i zginal")