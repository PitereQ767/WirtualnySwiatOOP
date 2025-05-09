#include "../pliki_naglowkowe/Zolw.h"


Zolw::Zolw(Swiat &world, Punkt &position) : Zwierze(world, position, 2, 1) {}

char Zolw::draw() const {
    return 'Z';
}

string Zolw::getNazwa() const {
    return "Zolw";
}

void Zolw::Action() {
    if (rand()%100 < 75) {
        increaseAge();
        world.addEvent("Zolw zostaje w miejscu.\n");
        return;
    }
    Zwierze::Action();
}

OrganismType Zolw::getOrganismType() const {
    return OrganismType::ZOLW;
}

Organizm *Zolw::makeNewOrganism(Punkt position) const {
    return new Zolw(world, position);
}

void Zolw::Collision2(Organizm *attacker, Punkt position) {
    if (attacker->getPower() < 5) {
        world.addEvent("Zolw odparl atak " + attacker->getNazwa() + " na pozycji " + to_string(position.GetX()) + ", " + to_string(position.GetY()) + ")\n");
        return;
    }

    Organizm::Collision2(attacker, position);
}




