#include "../pliki_naglowkowe/Wilk.h"


Wilk::Wilk(Swiat& world, Punkt& position)
    : Zwierze(world, position, 9, 5){}


char Wilk::draw() const{
    return 'W';
}

string Wilk::getNazwa() const {
    return "Wilk";
}

OrganismType Wilk::getOrganismType() const {
    return OrganismType::WILK;
}

Organizm *Wilk::makeNewOrganism(Punkt position) const {
    return new Wilk(world, position);
}

