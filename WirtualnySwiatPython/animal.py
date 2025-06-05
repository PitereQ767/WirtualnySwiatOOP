from organism import Organism


class Animal(Organism):
   def __init__(self, world, position, power, initiative):
       super().__init__(world, position, power, initiative)