#include "../pliki_naglowkowe/Zwierze.h"

#include "../pliki_naglowkowe/Guarana.h"
#include "../pliki_naglowkowe/Zolw.h"

Zwierze::Zwierze(Swiat& world, Punkt& position, int power, int initiative)
    : Organizm(world, position, power, initiative){}


void Zwierze::Action() {
    increaseAge();
    Punkt currentPosition = getPosition();
    int direction = rand() % 4;
    Punkt newPosition = currentPosition;

    switch (direction) {
        case GORA: newPosition.y--; break;
        case PRAWO: newPosition.x++; break;
        case DOL: newPosition.y++; break;
        case LEWO: newPosition.x--; break;
    }


    world.tryMoveOrganism(this, newPosition);

}

bool Zwierze::canMove(Punkt &position) {
    return  getWorld()->isFieldFree(position) && getWorld()->positionCorrect(position);
}

void Zwierze::Collision(Organizm* other, Punkt currentPosition) {
    OrganismType currnetType = this->getOrganismType();
    OrganismType otherType = other->getOrganismType();

    if (currnetType == otherType) {
        Multiplication(this);
        return;
    }

    other->Collision2(this, currentPosition);

}

void Zwierze::Multiplication(Organizm* organizm) {
    Punkt newPosition = findPosition();
    if (newPosition.x != getPosition().x || newPosition.y != getPosition().y) {
        Organizm* newOrganizm = makeNewOrganism(newPosition);
        world.addOrganism(newOrganizm, newPosition);
        world.addEvent(organizm->getNazwa() + " rozmnozyla sie na pozycji (" + to_string(newPosition.GetX()) + ", " + to_string(newPosition.GetY()) + ")\n");
    }
}




