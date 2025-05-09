
#ifndef GUARANA_H
#define GUARANA_H
#include "Roslina.h"

class Guarana : public Roslina {
public:
    Guarana(Swiat& world, Punkt& position);
    void Spread(Punkt &newPosition) override;
    char draw() const override;
    string getNazwa() const override;
    OrganismType getOrganismType() const override;
    void Collision2(Organizm *attacker, Punkt position) override;
};

#endif //GUARANA_H
