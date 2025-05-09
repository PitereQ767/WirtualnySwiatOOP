
#ifndef OWCA_H
#define OWCA_H

#include "Zwierze.h"

class Owca : public Zwierze {
public:
    Owca(Swiat& world, Punkt& position);
    char draw() const override;
    string getNazwa() const override;
    OrganismType getOrganismType() const override;
    Organizm* makeNewOrganism(Punkt position) const override;
};

#endif //OWCA_H
