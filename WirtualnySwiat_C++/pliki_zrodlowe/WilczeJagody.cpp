#include "../pliki_naglowkowe/WilczeJagody.h"

WilczeJagody::WilczeJagody(Swiat &world, Punkt &position) : Roslina(world, position, 99) {}

char WilczeJagody::draw() const {
    return 'J';
}

string WilczeJagody::getNazwa() const {
    return "WilczeJagody";
}

void WilczeJagody::Spread(Punkt &newPosition) {
    Organizm* newPlant = new WilczeJagody(world, newPosition);
    world.addOrganism(newPlant, newPosition);
    world.addEvent("Wilcze Jagody rozsialy sie na (" + to_string(newPosition.GetX()) + ", " + to_string(newPosition.GetY()) + ")\n");
}

OrganismType WilczeJagody::getOrganismType() const {
    return OrganismType::WILCZEJAGODY;
}

void WilczeJagody::Collision2(Organizm *attacker, Punkt position) {
    attacker->setIsAlive(false);
    world.addEvent(attacker->getNazwa() + " zjadl wilcze jagody i zginal\n.");
}
