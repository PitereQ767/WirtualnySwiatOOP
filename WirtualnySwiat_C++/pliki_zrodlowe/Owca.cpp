#include "../pliki_naglowkowe/Owca.h"

Owca::Owca(Swiat &world, Punkt &position) : Zwierze(world, position, 4, 4){}

char Owca::draw() const {
    return 'O';
}

string Owca::getNazwa() const {
    return "Owca";
}

OrganismType Owca::getOrganismType() const {
    return OrganismType::OWCA;
}

Organizm *Owca::makeNewOrganism(Punkt position) const {
    return new Owca(world, position);
}


