#ifndef MLECZ_H
#define MLECZ_H
#include "Roslina.h"

class Mlecz : public Roslina {
public:
    Mlecz(Swiat& world, Punkt& position);
    void Spread(Punkt &newPosition) override;
    char draw() const override;
    string getNazwa() const override;
    void Action() override;
    OrganismType getOrganismType() const override;
};

#endif //MLECZ_H
