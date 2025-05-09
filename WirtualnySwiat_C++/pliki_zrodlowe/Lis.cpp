#include "../pliki_naglowkowe/Lis.h"

Lis::Lis(Swiat &world, Punkt &position) : Zwierze(world, position, 3, 7) {}


char Lis::draw() const {
    return 'L';
}

string Lis::getNazwa() const {
    return "Lis";
}

void Lis::Action() {
    increaseAge();
    Punkt currentPosition = getPosition();

    for (int i = 0; i < 10; i++) {
        int direction = rand() % 4;
        Punkt newPosition = currentPosition;

        switch (direction) {
            case GORA: newPosition.y--;break;
            case PRAWO: newPosition.x++;break;
            case LEWO: newPosition.x--;break;
            case DOL: newPosition.y++; break;
        }
        if (shouldMove(newPosition)) {
            world.tryMoveOrganism(this, newPosition);
            return;
        }
    }
}

bool Lis::shouldMove(Punkt& position) {
    for (Organizm* org: world.getOrganisms()) {
        if (org->getPosition().x == position.x && org->getPosition().y == position.y && org->getPower() > this->getPower()) {
            return false;
        }
    }
    return true;
}

OrganismType Lis::getOrganismType() const {
    return OrganismType::LIS;
}

Organizm *Lis::makeNewOrganism(Punkt position) const {
    return new Lis(world, position);
}


