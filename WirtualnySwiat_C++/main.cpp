#include <iostream>
#include <cstdlib>
#include  <conio.h>



#include "pliki_naglowkowe/Swiat.h"


using  namespace std;


int main() {
    srand(time(NULL));
    int szerokosc, wysokosc;
    cout << "Podaj rozmiary planszy " << endl;
    cout << "Szerokosc: ";
    cin >> szerokosc;
    cout << "Wysokosc: ";
    cin >> wysokosc;


    Swiat swiat(szerokosc, wysokosc);
    swiat.printId();
    swiat.createWorld();
    swiat.PrintMap();
    cout << "Nacisnij ENTER aby przejsc do kolejnej tury" << endl;
    cout << "ESC - wyjscie, S - zapisz gre, L - wczytaj gre" << endl;

    while (!swiat.getIsEndGame()) {
        if(_kbhit()) {
            swiat.nextTurn();
        }
    }

    return 0;
}
