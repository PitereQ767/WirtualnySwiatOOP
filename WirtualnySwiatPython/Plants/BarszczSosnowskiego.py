from plant import Plant
from config import RED

class BarszczSosnowskiego(Plant):
    def __init__(self, world, position):
        super().__init__(world, position, power=10)
        self.color = RED

    def getColor(self):
        return self.color

    def getNazwa(self):
        return "BarszczSosnowskiego"

    def Action(self):
        pass