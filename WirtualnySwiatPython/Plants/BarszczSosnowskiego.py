from Animals.Human import Human
from plant import Plant
from config import RED, GORA, PRAWO, DOL, LEWO
from point import Point


class BarszczSosnowskiego(Plant):
    def __init__(self, world, position):
        super().__init__(world, position, power=10)
        self.color = RED

    def getColor(self):
        return self.color

    def getNazwa(self):
        return "BarszczSosnowskiego"

    def makeNewOrganism(self, position):
        return BarszczSosnowskiego(self.world, position)

    def Action(self):
        super().Action()

        current = self.getPosition()

        for i in range(4):
            x = current.GetX()
            y = current.GetY()

            if i == GORA:
                y -= 1
            elif i == PRAWO:
                x += 1
            elif i == DOL:
                y += 1
            elif i == LEWO:
                x -= 1

            target = Point(x, y)
            if (self.world.isCorrectPosition(target) and not self.world.isEmptyPosition(target)):
                from Animals.CyberSheep import CyberSheep
                other = self.world.getOrganismAtPosition(target)

                if isinstance(other, CyberSheep):
                    continue

                if isinstance(other, BarszczSosnowskiego):
                    continue

                if isinstance(other, Human):
                    self.world.killHuman()
                    continue

                other.setIsAlive(False)
                self.world.addEvent(f"Barszcz Sosnowskiego zabil {other.getNazwa()} na pozycji ({target.GetX()}, {target.GetY()})")