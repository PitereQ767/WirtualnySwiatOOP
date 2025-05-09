
#ifndef LIS_H
#define LIS_H
#include "Zwierze.h"

class Lis : public Zwierze {
    bool shouldMove(Punkt& position);
public:
    Lis(Swiat& world, Punkt& position);
    char draw() const override;
    string getNazwa() const override;
    void Action() override;
    OrganismType getOrganismType() const override;
    Organizm* makeNewOrganism(Punkt position) const override;
};

#endif //LIS_H
