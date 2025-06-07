import pygame
from config import max_window_width, max_window_height, cellSize, GORA, DOL, LEWO, PRAWO, DIRECTION_NAMES
from world import World

class GameGui:
    def __init__(self, width, height):
        pygame.init()
        self.width = width
        self.height = height
        self.window = pygame.display.set_mode((max_window_width, max_window_height))
        pygame.display.set_caption("Wirtualny Świat")
        self.font = pygame.font.SysFont("Arial", 16)
        self.clock = pygame.time.Clock()
        self.world = World(width, height)

        self.world.createWorld()

    def drawGrid(self):
        for y in range(self.height):
            for x in range(self.width):
                pygame.draw.rect(
                    self.window,
                    (0, 0, 0),
                    (x * cellSize, y * cellSize, cellSize, cellSize),
                    1  # grubość linii
                )

    def draw(self):
        self.window.fill((255, 255, 255))
        for organism in self.world.organisms:
            x, y = organism.getPosition()
            color = organism.getColor()
            self.drawGrid()
            pygame.draw.rect(self.window, color, (x * cellSize, y * cellSize, cellSize, cellSize))
            pygame.draw.rect(self.window, (0, 0, 0), (x * cellSize, y * cellSize, cellSize, cellSize), 1)

        margin = 10
        for i, event in enumerate(self.world.events):
            text = self.font.render(event, True, (0, 0, 0))
            self.window.blit(text, (self.width * cellSize + margin, i * cellSize + margin))

        if self.world.humanMove is not None:
            moveText = f"Ruch czlowieka: {DIRECTION_NAMES[self.world.humanMove]}"
        else:
            moveText = f"Ruch czlowieka: BRAK"

        textHuman = self.font.render(moveText, True, (0, 0, 0))
        self.window.blit(textHuman, (margin, self.height * cellSize + margin))

    def run(self):
        while not self.world.exitGame():
            self.clock.tick(30)
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    self.world.setEndGame(True)
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_RETURN:
                        self.world.events.clear()
                        self.world.makeRun()
                    if event.key == pygame.K_ESCAPE:
                        self.world.setEndGame(True)
                    if event.key == pygame.K_UP:
                        self.world.humanMove = GORA
                    if event.key == pygame.K_RIGHT:
                        self.world.humanMove = PRAWO
                    if event.key == pygame.K_DOWN:
                        self.world.humanMove = DOL
                    if event.key == pygame.K_LEFT:
                        self.world.humanMove = LEWO

            self.draw()
            pygame.display.flip()

        pygame.quit()