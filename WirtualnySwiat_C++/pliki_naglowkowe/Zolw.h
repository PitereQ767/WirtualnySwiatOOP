#ifndef ZOLW_H
#define ZOLW_H
#include "Zwierze.h"

class Zolw : public Zwierze {
public:
    Zolw(Swiat& world, Punkt& position);
    char draw() const override;
    string getNazwa() const override;
    void Action() override;
    OrganismType getOrganismType() const override;
    Organizm* makeNewOrganism(Punkt position) const override;
    // void Collision(Organizm *other) override;
    void Collision2(Organizm *attacker, Punkt position) override;
};

#endif //ZOLW_H
