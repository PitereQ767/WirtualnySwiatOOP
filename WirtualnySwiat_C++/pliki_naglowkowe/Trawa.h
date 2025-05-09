
#ifndef TRAWA_H
#define TRAWA_H
#include "Roslina.h"

class Trawa : public Roslina {
public:
    Trawa(Swiat& world, Punkt& position);
    void Spread(Punkt& newPosition) override;
    char draw() const override;
    string getNazwa() const override;
    OrganismType getOrganismType() const override;
};

#endif //TRAWA_H
