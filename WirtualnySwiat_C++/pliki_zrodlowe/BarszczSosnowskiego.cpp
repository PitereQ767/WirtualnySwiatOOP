#include "../pliki_naglowkowe/BarszczSosnowskiego.h"

#include "../pliki_naglowkowe/Czlowiek.h"
#include "../pliki_naglowkowe/Zwierze.h"

BarszczSosnowskiego::BarszczSosnowskiego(Swiat &world, Punkt &position) : Roslina(world, position, 10) {}

char BarszczSosnowskiego::draw() const {
    return 'B';
}

string BarszczSosnowskiego::getNazwa() const {
    return "BarszczSosnowskiego";
}

void BarszczSosnowskiego::Spread(Punkt &newPosition) {
    Organizm* newPlant = new BarszczSosnowskiego(world, newPosition);
    world.addOrganism(newPlant, newPosition);
    world.addEvent("Barszcz Sosnowskiego rozmnozyl sie na (" + to_string(newPosition.GetX()) + ", " + to_string(newPosition.GetY()) + ")\n");
}

void BarszczSosnowskiego::Action() {
    Roslina::Action();
    Punkt currentPos = getPosition();

    for (int i = 0; i < 4; i++) {
        Punkt target = currentPos;
        switch (i) {
            case GORA:
                target.y--;
                break;
            case DOL:
                target.y++;
                break;
            case LEWO:
                target.x--;
                break;
            case PRAWO:
                target.x++;
                break;
        }
        if (world.positionCorrect(target) && !world.isFieldFree(target)) {
            Organizm* organizm = world.getOrganizmAt(&target);
            Zwierze* zwierze = dynamic_cast<Zwierze *>(organizm); // dynamiczyne rzutowanie na dany typ (klasa pochodna)
            if (zwierze ) {
                Czlowiek* czlowiek = dynamic_cast<Czlowiek *>(zwierze);
                if (czlowiek != nullptr && czlowiek->getImmortality()) {
                    world.addEvent("Czlowieka uratowala niesmiertelnosc\n");
                    continue;
                }
                world.deleteOrganism(zwierze, target);
                world.addEvent("Barszcz Sosnowskiego zabil " + zwierze->getNazwa() + " na pozycji (" + to_string(target.GetX()) + ", " + to_string(target.GetY()) + ")\n");
            }
        }
    }
}

OrganismType BarszczSosnowskiego::getOrganismType() const {
    return OrganismType::BARSZCZSOSNOWSKIEGO;
}

void BarszczSosnowskiego::Collision2(Organizm *attacker, Punkt position) {
    attacker->setIsAlive(false);
    world.addEvent(attacker->getNazwa() + " zjadlo barszcz Sosnowskiego i ginie.\n");
}





