//
// Created by 48734 on 27.03.2025.
//

#ifndef WILK_H
#define WILK_H
#include "Zwierze.h"

class Wilk : public Zwierze {
public:
    Wilk(Swiat& world, Punkt& position);
    char draw() const override;
    string getNazwa() const override;
    OrganismType getOrganismType() const override;
    Organizm* makeNewOrganism(Punkt position) const override;
};

#endif //WILK_H
