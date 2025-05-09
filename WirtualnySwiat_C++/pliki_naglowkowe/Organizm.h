//
// Created by 48734 on 27.03.2025.
//

#ifndef ORGANIZM_H
#define ORGANIZM_H

#include "Punkt.h"
#include "../pliki_naglowkowe/Swiat.h"
#include "OrganismType.h"

using namespace std;

class Swiat;

class Organizm {
    int initiative, age;
    bool alive;
protected:
    int power;
    Swiat& world;
    Punkt position;

public:
    Organizm(Swiat& world, Punkt& position, int power, int initiative);
    virtual ~Organizm() = default;

    Punkt getPosition();
    Punkt findPosition();
    void setPosition(Punkt position);
    void setPower(int power);
    int getPower() const;
    int getInitiative() const;
    void setInitiative(int initiative);
    Swiat* getWorld() const;
    void increaseAge();
    int getAge() const;
    void setAge(int age);
    bool getIsAlive() const;
    void setIsAlive(bool alive);

    virtual char draw() const = 0;
    virtual std::string getNazwa() const = 0;
    virtual void Action() = 0;
    virtual OrganismType getOrganismType() const = 0;
    virtual void Collision(Organizm* other, Punkt position) = 0;
    virtual void Collision2(Organizm* attacker, Punkt position);
};



#endif //ORGANIZM_H
