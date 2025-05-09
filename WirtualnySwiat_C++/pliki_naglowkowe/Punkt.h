

#ifndef PUNKT_H
#define PUNKT_H

class Punkt {
public:
    int x, y;
    Punkt(int x = 0, int y = 0);
    int GetX() const;
    int GetY() const;
    void SetX(int x);
    void SetY(int y);
    ~Punkt();
};

#endif //PUNKT_H
