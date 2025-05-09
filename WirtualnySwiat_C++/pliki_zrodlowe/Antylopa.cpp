#include "../pliki_naglowkowe/Antylopa.h"

Antylopa::Antylopa(Swiat &world, Punkt &position)
    : Zwierze(world, position, 4, 4) {}

char Antylopa::draw() const {
    return 'A';
}

string Antylopa::getNazwa() const {
    return "Antylopa";
}

void Antylopa::Action() {
    increaseAge();
    Punkt currentPos = getPosition();
    int direction = rand()%4;
    Punkt newPosition = currentPos;

    switch (direction) {
        case GORA: newPosition.y -= 2; break;
        case LEWO: newPosition.x -= 2; break;
        case PRAWO: newPosition.x += 2; break;
        case DOL: newPosition.y += 2; break;
    }

    world.tryMoveOrganism(this, newPosition);
}

OrganismType Antylopa::getOrganismType() const {
    return OrganismType::ANTYLOPA;
}

Organizm* Antylopa::makeNewOrganism(Punkt position) const {
    return new Antylopa(world, position);
}

void Antylopa::Collision(Organizm *other, Punkt position) {
    OrganismType currentType = this->getOrganismType();
    OrganismType otherType = other->getOrganismType();

    if (currentType == otherType) {
        Multiplication(this);
        return;
    }

    if (rand() % 100 < 50) {
        Punkt newPosition = findPositionForAntylope();
        world.addEvent("Antylopa uciekla przed " + other->getNazwa() + " na pozycje (" + to_string(newPosition.GetX()) + ", " + to_string(newPosition.GetY()) + ")\n");
        world.moveOrganism(this, newPosition);
        return;
    }

    Zwierze::Collision(other, position);
}

Punkt Antylopa::findPositionForAntylope() {
    vector<Punkt> positions;
    Punkt currentPosition = getPosition();

    for (int i = 0; i<4; i++) {
        Punkt newPosition = currentPosition;
        switch (i) {
            case GORA: newPosition.y -= 2;break;
            case DOL: newPosition.y += 2;break;
            case LEWO: newPosition.x -= 2; break;
            case PRAWO: newPosition.x += 2;break;
        }

        if (world.positionCorrect(newPosition) && world.isFieldFree(newPosition)) {
            positions.push_back(newPosition);
        }
    }

    if (positions.empty()) return position;

    return positions[rand() % positions.size()];
}





