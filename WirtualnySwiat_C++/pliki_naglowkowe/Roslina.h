
#ifndef ROSLINA_H
#define ROSLINA_H
#include "Organizm.h"

#define PLANT_INITIATIVE 0
#define SPREAD_CHANCE 5

class Roslina : public Organizm {


public:
    Roslina(Swiat& world, Punkt& position, int power);

    void Action() override;
    virtual void Spread(Punkt& newPosition) = 0;
    bool trySpread() const;
    void Collision(Organizm *other, Punkt position) override;

    virtual char draw() const  = 0;
};

#endif //ROSLINA_H
