#include "../pliki_naglowkowe/Czlowiek.h"

Czlowiek::Czlowiek(Swiat &world, Punkt &position)
    : Zwierze(world, position, POWER_OF_HUMAN, INITIATIVE_OF_HUMAN), counterRound(0), activeImmortality(false){}

char Czlowiek::draw() const {
    return 'H';
}

void Czlowiek::Action() {
    increaseAge();

    if (activeImmortality) {
        if (--counterRound <= 0) {
            activeImmortality = false;
            world.addEvent("Niesmiertelnosc wygasla\n");
        }
    }

    int direction = Input();

    if(direction == -2) {
        return;
    }

     if (direction != -1) {
        Punkt newPosition = getPosition();
        switch (direction) {
            case GORA: newPosition.y--; break;
            case PRAWO: newPosition.x++; break;
            case DOL: newPosition.y++; break;
            case LEWO: newPosition.x--; break;
        }
        world.tryMoveOrganism(this, newPosition);
    }
}

int Czlowiek::Input() {

    int key = _getch();
    // cout << "Zczytano klawisz: " << key << endl;

    switch (key) {
        case STRZALKA_GORA: return GORA;
        case STRZALKA_DOL: return DOL;
        case STRZALKA_LEWO: return LEWO;
        case STRZALKA_PRAWO: return PRAWO;
        case N: {
            if (!activeImmortality ) {
                activateImmortality();
            }
            return -1;
        }
        case ENTER: {
            return -2;
        }
        case ESC: {
            world.setIsendGame(true);
            return -2;
        }
        case S: {
            string nazwa;
            cout << "Podaj nazwe pliku do zapisu: ";
            cin >> nazwa;
            world.setSaveFlag(true, nazwa);
            return -2;
        }
        case L: {
            string nazwa;
            cout << "Podaj nazwe pliku z ktora wczytac gre: ";
            cin >> nazwa;
            world.setLoadFlag(true, nazwa);
            return -2;
        }
        default:
            return -1;
    }

}

void Czlowiek::activateImmortality() {
    activeImmortality = true;
    counterRound = 10;
    world.addEvent("Niesmiertelnosc aktywna na 5 tur\n");
}

string Czlowiek::getNazwa() const {
    return "Czlowiek";
}

bool Czlowiek::getImmortality() const {
    return this->activeImmortality;
}

OrganismType Czlowiek::getOrganismType() const {
    return OrganismType::CZLOWIEK;
}

Organizm *Czlowiek::makeNewOrganism(Punkt position) const {
    return new Czlowiek(world, position);
}
