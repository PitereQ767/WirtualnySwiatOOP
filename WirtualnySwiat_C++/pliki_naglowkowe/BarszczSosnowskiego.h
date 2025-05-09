#ifndef BARSZCZSOSNOWSKIEGO_H
#define BARSZCZSOSNOWSKIEGO_H
#include "Roslina.h"

class BarszczSosnowskiego : public Roslina {
public:
    BarszczSosnowskiego(Swiat& world, Punkt& position);
    char draw() const override;
    string getNazwa() const override;
    void Action() override;
    void Spread(Punkt &newPosition) override;
    void killAnimals(Punkt &position);
    OrganismType getOrganismType() const override;
    void Collision2(Organizm *attacker, Punkt position) override;
};

#endif //BARSZCZSOSNOWSKIEGO_H
