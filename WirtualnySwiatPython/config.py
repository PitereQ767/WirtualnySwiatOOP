import pygame

cellSize = 20
numberOfOrganisms = 10
numberOfTypeOrganims = 7
max_window_width = 800
max_window_height = 600

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


GRAY = pygame.Color("darkgray")
BROWN = pygame.Color("brown")
BLUE = pygame.Color("blue")
RED = pygame.Color("red")
ORANGE = pygame.Color("orange")
LIGHT_GRAY = pygame.Color("lightgray")
LIME = pygame.Color("lime")
PINK = pygame.Color("pink")