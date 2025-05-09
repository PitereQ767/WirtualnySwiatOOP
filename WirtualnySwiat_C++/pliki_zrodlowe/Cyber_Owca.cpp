#include "../pliki_naglowkowe/Cyber_Owca.h"

Cyber_Owca::Cyber_Owca(Swiat &world, Punkt &position) : Zwierze(world, position, 11, 4) {}

char Cyber_Owca::draw() const {
    return 'C';
}

string Cyber_Owca::getNazwa() const {
    return "CyberOwca";
}

