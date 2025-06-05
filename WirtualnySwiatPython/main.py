from gui import GameGui

def main():
    try:
        width = int(input("Podaj szerokość planszy: "))
        height = int(input("Podaj wysokość planszy: "))
    except ValueError:
        print("Podano niepoprawna wysokosc. Używam domyślnego rozmiaru 20x20.")
        width = 20
        height = 20

    game = GameGui(width, height)
    game.run()

if __name__ == '__main__':
    main()