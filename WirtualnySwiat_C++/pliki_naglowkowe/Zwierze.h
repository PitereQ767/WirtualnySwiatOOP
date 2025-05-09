#ifndef ZWIERZE_H
#define ZWIERZE_H
#include "Organizm.h"


class Zwierze : public Organizm {
protected:
    void makeRun();
public:
    Zwierze(Swiat& world, Punkt& position, int power, int initiative);


    virtual char draw() const = 0;
    void Action() override;
    bool canMove(Punkt& position);

    void Collision(Organizm* other, Punkt currentPosition) override;
    void Multiplication(Organizm* organizm);
    virtual Organizm* makeNewOrganism(Punkt position) const = 0;

};

#endif //ZWIERZE_H
