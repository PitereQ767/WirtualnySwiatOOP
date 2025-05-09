#include "../pliki_naglowkowe/Mlecz.h"

Mlecz::Mlecz(Swiat &world, Punkt &position) : Roslina(world, position, 0) {}

void Mlecz::Spread(Punkt &newPosition) {
    Organizm* newPlant = new Mlecz(world, newPosition);
    world.addOrganism(newPlant, newPosition);
    world.addEvent("Mlecz rozsial sie na (" + to_string(newPosition.GetX()) + ", " + to_string(newPosition.GetY()) +")\n");
}

char Mlecz::draw() const {
    return 'M';
}

string Mlecz::getNazwa() const {
    return "Mlecz";
}

void Mlecz::Action() {
    increaseAge();

    for (int i = 0; i<3; i++) {
        if (trySpread()) {
            Punkt newPosition = findPosition();
            if (newPosition.x != getPosition().x || newPosition.y != getPosition().y) {
                Spread(newPosition);
                break;
            }
        }
    }
}

OrganismType Mlecz::getOrganismType() const {
    return OrganismType::MLECZ;
}





