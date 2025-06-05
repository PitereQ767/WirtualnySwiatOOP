import pygame.draw

from world import World
from config import cellSize


class SquareWorld(World):
    def draw(self, screen, font):
        screen.fill((255, 255, 255))
        for organism in self.organisms:
            position = organism.getPosition()
            x = position.GetX()
            y = position.GetY()
            color = (100, 100, 100)
            pygame.draw.rect(screen, color, (x * cellSize, y * cellSize, cellSize, cellSize))
            pygame.draw.rect(screen, (0, 0, 0), (x * cellSize, y * cellSize, cellSize, cellSize), 1)