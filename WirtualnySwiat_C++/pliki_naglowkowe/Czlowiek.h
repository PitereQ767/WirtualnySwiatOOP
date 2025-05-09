#ifndef CZLOWIEK_H
#define CZLOWIEK_H
#include "Zwierze.h"
#include <conio.h>

#define STRZALKA_LEWO 75
#define STRZALKA_PRAWO 77
#define STRZALKA_GORA 72
#define STRZALKA_DOL 80

#define ESC 27
#define ENTER 13
#define N 110
#define S 115
#define L 108

#define POWER_OF_HUMAN 5
#define INITIATIVE_OF_HUMAN 4

class Czlowiek : public Zwierze {
    int counterRound;
    bool activeImmortality;

    int Input();
public:

    Czlowiek(Swiat& world, Punkt& position);
    char draw() const override;
    void Action() override;
    void activateImmortality();
    string getNazwa() const override;
    bool getImmortality()const;
    OrganismType getOrganismType() const override;
    Organizm* makeNewOrganism(Punkt position) const override;
};

#endif //CZLOWIEK_H
