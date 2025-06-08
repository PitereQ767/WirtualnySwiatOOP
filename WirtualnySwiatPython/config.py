import pygame

cellSize = 20
numberOfOrganisms = 10
numberOfTypeOrganims = 11
max_window_width = 1000
max_window_height = 500

GORA = 0
PRAWO = 1
DOL = 2
LEWO = 3

DIRECTION_NAMES = {
    GORA: "GÓRA",
    PRAWO: "PRAWO",
    DOL: "DÓŁ",
    LEWO: "LEWO"
}

SPREAD_CHANCE = 5

GRAY = pygame.Color("darkgray")
BROWN = pygame.Color("brown")
BLUE = pygame.Color("blue")
RED = pygame.Color("red")
ORANGE = pygame.Color("orange")
LIGHT_GRAY = pygame.Color("lightgray")
LIME = pygame.Color("lime")
PINK = pygame.Color("pink")
PURPLE = pygame.Color("purple")
GREEN = pygame.Color("green")
CYAN = pygame.Color("cyan")
YELLOW = pygame.Color("yellow")