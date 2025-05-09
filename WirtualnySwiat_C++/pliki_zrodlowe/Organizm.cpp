#include "../pliki_naglowkowe/Organizm.h"

Organizm::Organizm(Swiat& world, Punkt& position, int power, int initiative)
    : world(world), position(position), power(power), initiative(initiative), age(0), alive(true) {}

int Organizm::getInitiative() const {
    return initiative;
}

int Organizm::getPower() const {
    return power;
}

Punkt Organizm::getPosition() {
    return position;
}

Swiat *Organizm::getWorld() const {
    return &world;
}

void Organizm::setPosition(Punkt position) {
    this->position = position;
}

void Organizm::increaseAge() {
    age++;
}

int Organizm::getAge() const {
    return age;
}

bool Organizm::getIsAlive() const {
    return alive;
}

void Organizm::setIsAlive(bool alive) {
    this->alive = alive;
}

void Organizm::setPower(int power) {
    this->power = power;
}

void Organizm::setAge(int age) {
    this->age = age;
}

void Organizm::setInitiative(int initiative) {
    this->initiative = initiative;
}


Punkt Organizm::findPosition() {
    vector<Punkt> positions;
    Punkt position = getPosition();

    for(int i = 0; i<=3; i++) {
        Punkt newPosition = position;

        switch (i) {
            case GORA: newPosition.y--;break;
            case PRAWO: newPosition.x++; break;
            case LEWO: newPosition.x--;break;
            case DOL: newPosition.y++;break;
        }

        if (world.positionCorrect(newPosition) && world.isFieldFree(newPosition)) {
            positions.push_back(newPosition);
        }
    }

    if (positions.empty()) {
        return position;
    }

    return positions[rand() % positions.size()];
}

void Organizm::Collision2(Organizm *attacker, Punkt position) {
    if (attacker->getPower() >= this->getPower()) {

        world.addEvent(attacker->getNazwa() + " zabil " + this->getNazwa() + " na pozycji " + to_string(position.GetX()) + ", " + to_string(position.GetY()) + ")\n");
        world.deleteOrganism(this, position);
        if (attacker->getIsAlive()) {
            world.moveOrganism(attacker, position);
        }

    }else {
        Punkt positionAtacker = attacker->getPosition();
        world.addEvent(attacker->getNazwa() + " zostal zabity przez " + this->getNazwa() + " na pozycji (" + to_string(position.GetX()) + ", " + to_string(position.GetY()) + ")\n");
        world.deleteOrganism(attacker, positionAtacker);
        if (this->getIsAlive()) {
            world.moveOrganism(this, position);
        }
    }
}












