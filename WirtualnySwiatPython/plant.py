from organism import Organism

class Plant(Organism):
    def __init__(self, world, position, power):
        super().__init__(world, position, power, 0)