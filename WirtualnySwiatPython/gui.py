import pygame

from square import SquareWorld
from config import cellSize

class GameGui:
    def __init__(self, width, height, worldType):
        pygame.init()
        self.width = width
        self.height = height
        self.window = pygame.display.set_mode((width * cellSize, height * cellSize))
        pygame.display.set_caption("Wirtualny Świat")
        self.font = pygame.font.SysFont("Arial", 16)
        self.clock = pygame.time.Clock()

        # if worldType == 'hex':
        # else:

        self.world = SquareWorld(width, height)

        self.world.createWorld()

    def run(self):
        while not self.world.exitGame():
            self.clock.tick(30)
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    self.world.setEndGame(True)

            self.world.draw(self.window, self.font)
            pygame.display.flip()

        pygame.quit()