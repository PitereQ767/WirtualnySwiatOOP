class Organism:
    def __init__(self, swiat, position):
        self.swiat = swiat
        self.position = position
        self.isAlive = True
    def getPosition(self):
        return self.position
    def getIsAlive(self):
        return self.isAlive
    def Action(self):
        pass