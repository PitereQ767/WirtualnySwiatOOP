import random

from organism import Organism
from point import Point


class Animal(Organism):
    def __init__(self, world, position, power, initiative):
       super().__init__(world, position, power, initiative)

    def Action(self):
        self.increaseAge()
        old_position = self.getPosition()
        direction = random.randint(0, 3)

        new_x = old_position.GetX()
        new_y = old_position.GetY()

        if direction == 0:
            new_y -= 1
        elif direction == 1:
            new_x += 1
        elif direction == 2:
            new_y += 1
        elif direction == 3:
            new_x -= 1

        new_position = Point(new_x, new_y)
        self.world.tryToMoveOrganism(self, new_position)
