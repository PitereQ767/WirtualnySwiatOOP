//
// Created by 48734 on 28.03.2025.
//

#include "../pliki_naglowkowe/Punkt.h"

Punkt::Punkt(int x, int y ) : x(x), y(y){}

int Punkt::GetX() const {
    return x;
}

int Punkt::GetY() const {
    return y;
}

void Punkt::SetX(int x) {
    this->x = x;
}

void Punkt::SetY(int y) {
    this->y = y;
}

Punkt::~Punkt() {}




