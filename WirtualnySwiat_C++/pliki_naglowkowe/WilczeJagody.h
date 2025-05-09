#ifndef WILCZEJAGODY_H
#define WILCZEJAGODY_H
#include "Roslina.h"

class WilczeJagody : public Roslina {
public:
    WilczeJagody(Swiat& world, Punkt& position);
    void Spread(Punkt &newPosition) override;
    char draw() const override;
    string getNazwa() const override;
    OrganismType getOrganismType() const override;
    void Collision2(Organizm *attacker, Punkt position) override;
};

#endif //WILCZEJAGODY_H
