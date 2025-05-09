#ifndef ANTYLOPA_H
#define ANTYLOPA_H
#include "Zwierze.h"

class Antylopa : public Zwierze {
public:
    Antylopa(Swiat& world, Punkt& position);
    char draw() const override;
    string getNazwa() const override;
    void Action() override;
    OrganismType getOrganismType() const override;
    Organizm* makeNewOrganism(Punkt position) const;
    void Collision(Organizm *other, Punkt position) override;
    Punkt findPositionForAntylope();
};

#endif //ANTYLOPA_H
