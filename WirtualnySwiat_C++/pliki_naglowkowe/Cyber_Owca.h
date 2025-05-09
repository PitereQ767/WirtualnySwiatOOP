//
// Created by 48734 on 28.03.2025.
//

#ifndef CYBER_OWCA_H
#define CYBER_OWCA_H
#include "Zwierze.h"

class Cyber_Owca : public Zwierze {
public:
    Cyber_Owca(Swiat& world, Punkt& position);
    char draw() const override;
    string getNazwa() const override;
};

#endif //CYBER_OWCA_H
