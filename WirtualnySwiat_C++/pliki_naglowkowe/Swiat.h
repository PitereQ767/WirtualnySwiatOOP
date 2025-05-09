#ifndef SWIAT_H
#define SWIAT_H

#include <iostream>
#include <vector>
#include <iomanip>
#include <typeinfo>
#include  <algorithm>
#include <fstream>
#include "Organizm.h"
#include "OrganismType.h"

#define NUMBER_OF_ORGANISMS 10
#define NUMBER_OF_TYPE_ORGANISMS 10
#define EMPTY '.'


#define GORA 0
#define PRAWO 1
#define DOL 2
#define LEWO 3

using namespace std;

class Organizm;

class Swiat {
private:
    int width;
    int height;
    vector<vector<char>>map;
    vector<Organizm*> organisms;
    vector<string> events;
    bool isEndGame;
    int counterOfOrganisms;
    bool flagSave = false;
    bool flagLoad = false;
    string fileToSave;

    void sortOrganisms();
    void doActions();


public:
    Swiat(int width, int height);
    void EmptyMap();
    void PrintMap();
    void updateMap();
    int getHeight() const;
    int getWidth() const;
    int getNumberOfOrganisms() const;
    bool isFieldFree(Punkt& position) const;
    void addOrganism(Organizm* newOrganism, Punkt& position);
    void createWorld();
    vector<vector<char>> getMap();
    Organizm* createRandomOrganism(Punkt& position);
    vector<Organizm*>& getOrganisms();
    void nextTurn();
    void moveOrganism(Organizm* organism, Punkt& newPosition);
    void endGame();
    void printId();
    void addEvent(const string& evant);
    void printEvants() const;
    void tryMoveOrganism(Organizm* organism, Punkt& newPosition);
    bool positionCorrect(Punkt& position);
    void setIsendGame(bool value);
    bool getIsEndGame() const;
    void deleteOrganism(Organizm* organism, Punkt& position);
    Organizm* getOrganizmAt(Punkt* position);
    void cleanDeadOrganism();
    void counter();
    void saveToFile(string& nazwaPliku);
    void loadGameFromFile(string& nazwaPliku);
    void setSaveFlag(bool value, string& nazwaPliku);
    void setLoadFlag(bool value, string& nazwaPliku);
    void fileOperations();
};

#endif //SWIAT_H
