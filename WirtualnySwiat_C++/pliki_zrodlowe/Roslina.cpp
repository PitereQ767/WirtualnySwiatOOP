#include "../pliki_naglowkowe/Roslina.h"

#include "../pliki_naglowkowe/Zwierze.h"

Roslina::Roslina(Swiat &world, Punkt &position, int power)
    : Organizm(world, position, power, PLANT_INITIATIVE) {}

void Roslina::Action() {
    increaseAge();

    if (trySpread()) {
        Punkt newPosition = findPosition();
        if (newPosition.x != getPosition().x || newPosition.y != getPosition().y) {
            Spread(newPosition);
        }
    }
}

bool Roslina::trySpread() const {
    return (rand() % 100) < SPREAD_CHANCE;
}

void Roslina::Collision(Organizm *other, Punkt position) {

}




