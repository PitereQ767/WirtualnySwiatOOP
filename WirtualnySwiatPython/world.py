import random
from abc import ABC, abstractmethod

from Animals.Antelope import Antelope
from Animals.CyberSheep import CyberSheep
from Animals.Fox import Fox
from Animals.Human import Human
from Animals.Sheep import Sheep
from Animals.Turtle import Turtle
from Animals.Wolf import Wolf
from Plants.BarszczSosnowskiego import BarszczSosnowskiego
from Plants.Berries import Berries
from Plants.Grass import Grass
from Plants.Guarana import Guarana
from Plants.Milkweed import Milkweed
from point import Point
from organism import Organism
from config import numberOfTypeOrganims, numberOfOrganisms


class World(ABC):
    def __init__(self, width, height):
        self.width = width
        self.height = height
        self.organisms = []
        self.events = []
        self.isEndGame = False
        self.humanMove = None

    def exitGame(self):
        return self.isEndGame

    def setEndGame(self, endGame):
        self.isEndGame = endGame

    def isEmptyPosition(self, position):
        for organism in self.organisms:
            if position == organism.getPosition():
                return False
        return True

    def isCorrectPosition(self, position):
        x, y = position
        if x < 0 or y < 0 or x >= self.width or y >= self.height:
            return False
        return True

    def getEmptyAndCorrectPosition(self):
        while True:
            x = random.randint(0, self.width - 1)
            y = random.randint(0, self.height - 1)
            pos = Point(x, y)
            if (self.isEmptyPosition(pos) and self.isCorrectPosition(pos)): return pos

    def addEvent(self, event):
        self.events.append(event)

    def createRandomOrganism(self, position):
        randomNum = random.randint(0, numberOfTypeOrganims -1)

        if randomNum == 0:
            return Wolf(self, position)
        elif randomNum == 1:
            return Antelope(self, position)
        elif randomNum == 2:
            return BarszczSosnowskiego(self, position)
        elif randomNum == 3:
            return CyberSheep(self, position)
        elif randomNum == 4:
            return Fox(self, position)
        elif randomNum == 5:
            return Sheep(self, position)
        elif randomNum == 6:
            return Turtle(self, position)
        elif randomNum == 7:
            return Berries(self, position)
        elif randomNum == 8:
            return Grass(self, position)
        elif randomNum == 9:
            return Guarana(self, position)
        elif randomNum == 10:
            return Milkweed(self, position)

        return None


    def createWorld(self):
        humanPosition = self.getEmptyAndCorrectPosition()
        self.organisms.append(Human(self, humanPosition))
        for i in range(numberOfOrganisms):
            position = self.getEmptyAndCorrectPosition()
            organism = self.createRandomOrganism(position)
            self.organisms.append(organism)

    def moveOrganism(self, org, position):
        org.setPosition(position)

    def tryToMoveOrganism(self, org, position):
        if not self.isCorrectPosition(position):
            self.addEvent(org.getNazwa() + " probowal wyjsc poza mape")
        else:
            if self.isEmptyPosition(position):
                self.moveOrganism(org, position)
                self.addEvent(f"{org.getNazwa()} przesunal sie na ({position.GetX()}, {position.GetY()})")
            else:
                other = self.getOrganismAtPosition(position)
                other.Collision(org)

    def makeRun(self):
        self.organisms.sort(key=lambda org: (org.getInitiative(), org.getAge()), reverse=True)

        for organism in self.organisms.copy():
            if organism.getIsAlive():
                organism.Action()

        self.cleadDeadOrganism()

    def getHuman(self):
        for organism in self.organisms:
            if isinstance(organism, Human):
                return organism

        return None

    def getOrganismAtPosition(self, position):
        for organism in self.organisms:
            if (organism.getPosition() == position):
                return organism

        return None

    def cleadDeadOrganism(self):
        self.organisms = [organism for organism in self.organisms if organism.getIsAlive()]

    def killHuman(self):
        human = self.getHuman()

        if not human.getImmortality():
            human.setIsAlive(False)
            self.isEndGame = True
            self.addEvent("Czlowiek zginal")
        else:
            self.addEvent("Czlowiek przezyl dzieki aktywowanej niesmiertelnosci")

    def save_to_file(self, filename="save.txt"):
        try:
            with open(filename, "w") as file:
                file.write(f"{self.width} {self.height}\n")

                human = self.getHuman()
                immortality = 1 if human.getImmortality() else 0
                immortality_rounds = human.getImmortalityRounds() if immortality else 0
                file.write(
                    f"{human.getNazwa()} {human.getPosition().GetX()} {human.getPosition().GetY()} {human.getPower()} {human.getInitiative()} {human.getAge()} {immortality} {immortality_rounds}\n")

                for organism in self.organisms:
                    if organism.getIsAlive() and not isinstance(organism, type(human)):
                        file.write(
                            f"{organism.getNazwa()} {organism.getPosition().GetX()} {organism.getPosition().GetY()} {organism.getPower()} {organism.getInitiative()} {organism.getAge()}\n")

            print("Zapisano gre")
        except Exception as e:
            print("Błąd podczas zapisu gry:", e)

    def load_game_from_file(self, filename="save.txt"):
        try:
            self.organisms.clear()
            self.events.clear()

            with open(filename, "r") as file:
                lines = file.readlines()

            w, h = map(int, lines[0].strip().split())
            self.width, self.height = w, h

            # Wczytaj człowieka
            parts = lines[1].strip().split()
            x, y = int(parts[1]), int(parts[2])
            power = int(parts[3])
            initiative = int(parts[4])
            age = int(parts[5])
            immortality = int(parts[6])
            immortality_rounds = int(parts[7])

            from Animals.Human import Human
            p = Point(x, y)
            human = Human(self, p)
            human.setPower(power)
            human.setInitiative(initiative)
            human.setAge(age)
            if immortality:
                human.setImmortality(True)
                human.setImmortalityRounds(immortality_rounds)
            self.organisms.append(human)

            # Wczytaj pozostałe organizmy
            for line in lines[2:]:
                parts = line.strip().split()
                name = parts[0]
                x, y = int(parts[1]), int(parts[2])
                power = int(parts[3])
                initiative = int(parts[4])
                age = int(parts[5])
                p = Point(x, y)

                if name == "Wilk":
                    from Animals.Wolf import Wolf
                    organism = Wolf(self, p)
                elif name == "Lis":
                    from Animals.Fox import Fox
                    organism = Fox(self, p)
                elif name == "Antylopa":
                    from Animals.Antelope import Antelope
                    organism = Antelope(self, p)
                elif name == "BarszczSosnowskiego":
                    from Plants.BarszczSosnowskiego import BarszczSosnowskiego
                    organism = BarszczSosnowskiego(self, p)
                elif name == "CyberOwca":
                    from Animals.CyberSheep import CyberSheep
                    organism = CyberSheep(self, p)
                elif name == "Guarana":
                    from Plants.Guarana import Guarana
                    organism = Guarana(self, p)
                elif name == "Mlecz":
                    from Plants.Milkweed import Milkweed
                    organism = Milkweed(self, p)
                elif name == "Owca":
                    from Animals.Sheep import Sheep
                    organism = Sheep(self, p)
                elif name == "Trawa":
                    from Plants.Grass import Grass
                    organism = Grass(self, p)
                elif name == "WilczeJagody":
                    from Plants.Berries import Berries
                    organism = Berries(self, p)
                elif name == "Zolw":
                    from Animals.Turtle import Turtle
                    organism = Turtle(self, p)
                else:
                    print(f"Nieznany organizm przy wczytywaniu: {name}")
                    continue

                organism.setPower(power)
                organism.setInitiative(initiative)
                organism.setAge(age)
                self.organisms.append(organism)
            print("Wczytano gre")

        except Exception as e:
            print("Błąd podczas wczytywania gry:", e)