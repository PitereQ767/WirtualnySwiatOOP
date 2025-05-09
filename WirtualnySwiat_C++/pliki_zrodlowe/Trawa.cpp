#include "../pliki_naglowkowe/Trawa.h"

Trawa::Trawa(Swiat &world, Punkt &position) : Roslina(world, position, 0) {}


void Trawa::Spread(Punkt& newPosition) {
    Organizm* newGrass = new Trawa(world, newPosition);
    world.addOrganism(newGrass, newPosition);
    world.addEvent("Trawa rozsiala sie na (" + to_string(newPosition.GetX()) + ", " + to_string(newPosition.GetY())+ ")\n");
}

char Trawa::draw() const {
    return 'T';
}

string Trawa::getNazwa() const {
    return "Trawa";
}

OrganismType Trawa::getOrganismType() const {
    return OrganismType::TRAWA;
}



