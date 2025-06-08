import pygame

from Plants.BarszczSosnowskiego import BarszczSosnowskiego
from Plants.Berries import Berries
from Plants.Grass import Grass
from Plants.Milkweed import Milkweed
from Plants.Guarana import Guarana
from config import max_window_width, max_window_height, cellSize, GORA, DOL, LEWO, PRAWO, DIRECTION_NAMES
from point import Point
from world import World
from Animals.Wolf import Wolf
from Animals.Antelope import Antelope
from Animals.Turtle import Turtle
from Animals.Fox import Fox
from Animals.Sheep import Sheep
from Animals.CyberSheep import CyberSheep


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

    def drawLegend(self):
        unique_types = {}
        for organism in self.world.organisms:
            name = organism.getNazwa()
            color = organism.getColor()
            if name not in unique_types:
                unique_types[name] = color

        legend_x = self.width * cellSize + 10
        legend_y = 10
        spacing = 25

        legend_title = self.font.render("Legenda:", True, (0, 0, 0))
        self.window.blit(legend_title, (legend_x, legend_y))
        legend_y += spacing

        for name, color in unique_types.items():
            pygame.draw.rect(self.window, color, (legend_x, legend_y, 20, 20))
            pygame.draw.rect(self.window, (0, 0, 0), (legend_x, legend_y, 20, 20), 1)
            text_surface = self.font.render(name, True, (0, 0, 0))
            self.window.blit(text_surface, (legend_x + 30, legend_y))
            legend_y += spacing


    def draw(self):
        self.window.fill((255, 255, 255))
        self.drawGrid()
        self.drawLegend()
        for organism in self.world.organisms:
            if organism.getIsAlive():
                x, y = organism.getPosition()
                color = organism.getColor()
                pygame.draw.rect(self.window, color, (x * cellSize, y * cellSize, cellSize, cellSize))
                pygame.draw.rect(self.window, (0, 0, 0), (x * cellSize, y * cellSize, cellSize, cellSize), 1)

        margin = 10
        spacing = 25
        eventsTitle = self.font.render("Events:", True, (0, 0, 0))
        self.window.blit(eventsTitle, (self.width * cellSize + margin + 170, margin))
        tmp = margin + spacing
        for event in self.world.events:
            text = self.font.render(event, True, (0, 0, 0))
            self.window.blit(text, (self.width * cellSize + margin + 170, tmp))
            tmp += spacing

        if self.world.humanMove is not None:
            moveText = f"Ruch czlowieka: {DIRECTION_NAMES[self.world.humanMove]}"
        else:
            moveText = f"Ruch czlowieka: BRAK"

        textHuman = self.font.render(moveText, True, (0, 0, 0))
        self.window.blit(textHuman, (margin, self.height * cellSize + margin))

        if self.world.getHuman() is not None and self.world.getHuman().getImmortality():
            imoText = f"Niesmiertelnosc: Aktywna - {self.world.getHuman().getImmortalityRounds()} rund"
        else:
            imoText = "Niesmiertelnosc: Nieaktywna"

        imoSurface = self.font.render(imoText, True, (0, 0, 0))
        self.window.blit(imoSurface, (margin + 170, self.height * cellSize + margin))

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
                    if event.key == pygame.K_n:
                        human = self.world.getHuman()
                        human.setImmortality(True)
                        human.setImmortalityRounds(5)
                    if event.key == pygame.K_s:
                        self.world.save_to_file()
                    if event.key == pygame.K_l:
                        self.world.load_game_from_file()

                elif event.type == pygame.MOUSEBUTTONDOWN:
                    mouse_x, mouse_y = pygame.mouse.get_pos()
                    grid_x = mouse_x // cellSize
                    grid_y = mouse_y // cellSize

                    if grid_x < self.width and grid_y < self.height:
                        pos = Point(grid_x, grid_y)
                        if self.world.isEmptyPosition(pos):
                            self.showAddOrganismMenu(pos)

            if not self.world.isEndGame:
                self.draw()
            else:
                self.endGameInfo()

            pygame.display.flip()

        pygame.quit()

    def endGameInfo(self):
        self.window.fill((255, 255, 255))
        end_font = pygame.font.SysFont("Arial", 36, bold=True)
        text_surface = end_font.render("KONIEC GRY", True, (255, 0, 0))
        text_rect = text_surface.get_rect(center=(max_window_width // 2, max_window_height // 2))

        self.window.blit(text_surface, text_rect)
        pygame.display.flip()

        pygame.time.delay(3000)  # Poczekaj 3 sekundy (3000 ms)

    def showAddOrganismMenu(self, position):
        organism_types = {
            "1": ("Wilk", Wolf),
            "2": ("Owca", Sheep),
            "3": ("Lis", Fox),
            "4": ("Zolw", Turtle),
            "5": ("Trawa", Grass),
            "6": ("Mlecz", Milkweed),
            "7": ("Guarana", Guarana),
            "8": ("WilczeJagody", Berries),
            "9": ("BarszczSosnowskiego", BarszczSosnowskiego),
            "10": ("CyberOwca", CyberSheep),
            "11": ("Antylopa", Antelope)
        }

        print("Wybierz organizm do dodania:")
        for key, (name, _) in organism_types.items():
            print(f"{key}: {name}")

        choice = input("Wpisz numer organizmu: ")

        if choice in organism_types:
            _, organism_class = organism_types[choice]
            new_organism = organism_class(self.world, position)
            self.world.organisms.append(new_organism)
            self.world.addEvent(f"Dodano {new_organism.getNazwa()} na ({position.GetX()}, {position.GetY()})")
        else:
            print("Nieprawidłowy wybór.")