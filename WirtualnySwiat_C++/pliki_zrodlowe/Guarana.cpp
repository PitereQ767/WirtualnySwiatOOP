#include "../pliki_naglowkowe/Guarana.h"

Guarana::Guarana(Swiat &world, Punkt &position) : Roslina(world, position, 0){

}

void Guarana::Spread(Punkt &newPosition) {
    Organizm* newPlant = new Guarana(world, newPosition);
    world.addOrganism(newPlant, newPosition);
    world.addEvent("Guarana rozmnozyla sie na (" + to_string(newPosition.GetX()) + ", " + to_string(newPosition.GetY()) + ")\n");
}

char Guarana::draw() const {
    return 'G';
}

string Guarana::getNazwa() const {
    return "Guarana";
}

OrganismType Guarana::getOrganismType() const {
    return OrganismType::GUARANA;
}

void Guarana::Collision2(Organizm *attacker, Punkt position) {
    int power = attacker->getPower() + 3;
    cout << attacker->getPower() << endl;
    attacker->setPower(power);
    world.addEvent("Guarana zwiwekszyla moc " + attacker->getNazwa() + " na pozycji " + to_string(position.GetX()) + ", " + to_string(position.GetY()) + ") o 3\n");
    cout << attacker->getPower() << endl;

    world.deleteOrganism(this, position);
    if (attacker->getIsAlive()) {
        world.moveOrganism(attacker, position);
    }
}





