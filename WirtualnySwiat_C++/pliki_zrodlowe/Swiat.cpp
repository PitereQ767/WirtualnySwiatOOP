
#include "../pliki_naglowkowe/Swiat.h"

#include <set>

#include "../pliki_naglowkowe/Antylopa.h"
#include "../pliki_naglowkowe/BarszczSosnowskiego.h"
#include "../pliki_naglowkowe/Cyber_Owca.h"
#include "../pliki_naglowkowe/Czlowiek.h"
#include "../pliki_naglowkowe/Guarana.h"
#include "../pliki_naglowkowe/Lis.h"
#include "../pliki_naglowkowe/Mlecz.h"
#include "../pliki_naglowkowe/Owca.h"
#include "../pliki_naglowkowe/Trawa.h"
#include "../pliki_naglowkowe/WilczeJagody.h"
#include "../pliki_naglowkowe/Wilk.h"
#include "../pliki_naglowkowe/Zolw.h"

Swiat::Swiat(int width, int height) : width(width), height(height), isEndGame(false){}

void Swiat::EmptyMap() {
    map.clear();
    for (int i = 0; i < height; i++){
        vector<char> row(width, EMPTY);
        map.push_back(row);
    }
}

void Swiat::printId() {
    cout << "**************************" << endl;
    cout << "* Piotr Ratkowski 203285 *" << endl;
    cout << "**************************" << endl;
    cout << endl;
}


void Swiat::PrintMap() {
    cout << "Liczba organizmow: " << counterOfOrganisms << endl;
    cout << "  ";
    for (int i = 0; i < width; i++) {
        cout << setw(2) <<  i  << " ";
    }

    cout << endl;

    for (int i = 0; i<height; i++) {
        cout << setw(2) << i ;
        for (int j = 0; j<width; j++) {
            cout << setw(2) << map[i][j] << " ";
        }
        cout << endl;
    }
}

void Swiat::updateMap() {
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            map[i][j] = EMPTY;
        }
    }

    for (auto* organism : organisms) {
        if (organism->getIsAlive()) {
            Punkt position = organism->getPosition();
            map[position.GetY()][position.GetX()] = organism->draw();
        }
    }
}


int Swiat::getHeight() const{
    return height;
}

int Swiat::getWidth() const{
    return width;
}

vector<vector<char>> Swiat::getMap() {
    return map;
}

bool Swiat::isFieldFree(Punkt& position) const{
    int x = position.GetX();
    int y = position.GetY();

    return map[y][x] == EMPTY;
}

bool Swiat::positionCorrect(Punkt &position) {
    int x = position.GetX();
    int y = position.GetY();
    if (x < 0 || x>= width || y<0 || y >=height) {
        return false;
    }
    return true;
}


void Swiat::addOrganism(Organizm *newOrganism, Punkt& position) {
    newOrganism->setPosition(position);
    organisms.push_back(newOrganism);
    map[position.GetY()][position.GetX()] = newOrganism->draw();
}

void Swiat::deleteOrganism(Organizm *organism, Punkt &position) {
    map[position.GetY()][position.GetX()] = EMPTY;
    organism->setIsAlive(false);
}

Organizm *Swiat::getOrganizmAt(Punkt *position) {
    for (auto organism : organisms) {
        if (organism->getPosition().GetX() == position->GetX() && organism->getPosition().GetY() == position->GetY()) {
            return organism;
        }
    }
    return nullptr;
}



void Swiat::createWorld() {
    EmptyMap();
    Punkt humanPosition;
    counterOfOrganisms = NUMBER_OF_ORGANISMS + 1;
    do {
        humanPosition={rand() % width, rand() % height};
    }while (!isFieldFree(humanPosition));
    Organizm* human = new Czlowiek(*this, humanPosition);
    addOrganism(human, humanPosition);

    for (int i = 0; i < NUMBER_OF_ORGANISMS; i++) {
        Punkt position;
        do {
            position = {rand() % width, rand() % height};
        }while (!isFieldFree(position));

        Organizm* newOrganism = createRandomOrganism(position);
        addOrganism(newOrganism, position);
    }
}

Organizm *Swiat::createRandomOrganism(Punkt &position) {
    int random = rand() % NUMBER_OF_TYPE_ORGANISMS;

    switch (random) {
        case 0: return new Wilk(*this, position);
        case 1: return new Owca(*this, position);
        case 2: return new Zolw(*this, position);
        case 3: return new Antylopa(*this, position);
        case 4: return new Guarana(*this, position);
        // case 4: return new Cyber_Owca(*this, position);
        case 5: return new Lis(*this, position);
        case 6: return new Trawa(*this, position);
        case 7: return new Mlecz(*this, position);
        case 8: return new WilczeJagody(*this, position);
        case 9: return new BarszczSosnowskiego(*this, position);
    }
}

void Swiat::sortOrganisms() {
    int n = organisms.size();

    for (int i = 0; i < n -1; i++) {
        for (int j = 0; j < n - i -1; j++) {
            if (organisms[j]->getInitiative() < organisms[j+1]->getInitiative() ||
                (organisms[j]->getInitiative() == organisms[j+1]->getInitiative() &&
                    organisms[j]->getAge() < organisms[j+1]->getAge())) {

                Organizm* temp = organisms[j];
                organisms[j] = organisms[j+1];
                organisms[j+1] = temp;
            }
        }
    }
}

void Swiat::doActions() {
    for (auto* organism : organisms) {
        organism->Action();
    }
}


void Swiat::nextTurn() {

    endGame();
    sortOrganisms();
    doActions();
    updateMap();
    PrintMap();
    printEvants();
    events.clear();
    cout << "Tura zakonczona.\n";
    cleanDeadOrganism();
    counter();
    fileOperations();
}

void Swiat::moveOrganism(Organizm *organism, Punkt &newPosition) {
    Punkt currentPosition = organism->getPosition();
    map[currentPosition.y][currentPosition.x] = EMPTY;

    organism->setPosition(newPosition);
    map[newPosition.y][newPosition.x] = organism->draw();
}

void Swiat::endGame() {
    bool czlowiekZyje = false;
    bool inneZyja = false;

    for (auto o : organisms) {
        if (o->getIsAlive()) {
            if (o->getNazwa() == "Czlowiek") {
                czlowiekZyje = true;
            } else {
                inneZyja = true;
            }
        }
    }

    if (!czlowiekZyje || !inneZyja) {
        setIsendGame(true);
    } else {
        setIsendGame(false);
    }
}

void Swiat::addEvent(const string &event) {
    events.push_back(event);
}

void Swiat::printEvants() const {
    cout << "Historia zdarzen: " << endl;
    for (auto& event : events) {
        cout << "- " << event;
    }
}

void Swiat::tryMoveOrganism(Organizm *organism, Punkt &newPosition) {
    if (!positionCorrect(newPosition)) {
        addEvent(organism->getNazwa() + " probowal wyjsc za mape\n");
    }else {
        if(isFieldFree(newPosition)) {
            moveOrganism(organism, newPosition);
            addEvent(organism->getNazwa() + " przesunal sie na (" + to_string(newPosition.x) + ", " + to_string(newPosition.y) + ")\n");
        }else {
            Organizm* other = getOrganizmAt(&newPosition);
            organism->Collision(other, newPosition);
        }
    }
}

void Swiat::setIsendGame(bool value) {
    this->isEndGame = value;
}

bool Swiat::getIsEndGame() const {
    return isEndGame;
}

vector<Organizm *>& Swiat::getOrganisms() {
    return organisms;
}

void Swiat::cleanDeadOrganism() {
    for (auto tmp = organisms.begin(); tmp != organisms.end();) {
        if (!(*tmp)->getIsAlive()) {
            delete *tmp;
            tmp = organisms.erase(tmp);
        }else {
            tmp++;
        }
    }
}

int Swiat::getNumberOfOrganisms() const {
    return counterOfOrganisms;
}

void Swiat::counter() {
    int tmp = 0;
    for (auto organism:organisms) {
        tmp++;
    }
    counterOfOrganisms = tmp;
}


void Swiat::saveToFile(string& nazwaPliku) {
    ofstream plik(nazwaPliku);

    if (!plik.is_open()) {
        cout << "Nie udalo sie otworzyc pliku przy zapisie." << endl;
        return;
    }
    plik << width << " " << height << endl;

    int livingOrganisms = 0;
    for (auto* org : organisms) {
        if (org->getIsAlive()) livingOrganisms++;
    }
    plik << livingOrganisms << endl;

    for (auto * organizm : organisms) {
        plik << organizm->getNazwa() << " " << organizm->getPosition().GetX() << " " << organizm->getPosition().GetY() << " " << organizm->getPower() << " " << organizm->getInitiative() << " " << organizm->getAge() << "\n";
    }

    plik.close();
    cout << "Gra zostala zapisana do pliku: " + nazwaPliku << endl;
}

void Swiat::loadGameFromFile(string &nazwaPliku) {
    ifstream plik(nazwaPliku);

    if (!plik.is_open()) {
        cout << "Nie udalo sie otworzyc pliku przy wczytywaniu. " << endl;
        return;
    }


    organisms.clear();
    EmptyMap();
    events.clear();

    int newWidth, newHeight;
    plik >> newWidth >> newHeight;
    width = newWidth;
    height = newHeight;
    EmptyMap();

    int liczbaOrganizmow;
    plik >> liczbaOrganizmow;

    for (int i = 0; i < liczbaOrganizmow; i++) {
        string nazwa;
        int x, y, power, initiative, age;

        plik >> nazwa >> x >> y >> power >> initiative >> age;
        Punkt p(x, y);

        Organizm* newOrgaznizm = nullptr;

        if (nazwa == "Wilk") newOrgaznizm = new Wilk(*this, p);
        else if (nazwa == "Lis") newOrgaznizm = new Lis(*this, p);
        else if (nazwa == "Antylopa") newOrgaznizm = new Antylopa(*this, p);
        else if (nazwa == "BarszczSosnowskiego") newOrgaznizm = new BarszczSosnowskiego(*this, p);
        // else if (nazwa == "CyberOwca") newOrgaznizm = new Cyber_Owca(*this, p);
        else if (nazwa == "Czlowiek") newOrgaznizm = new Czlowiek(*this, p);
        else if (nazwa == "Guarana") newOrgaznizm = new Guarana(*this, p);
        else if (nazwa == "Mlecz") newOrgaznizm = new Mlecz(*this, p);
        else if (nazwa == "Owca") newOrgaznizm = new Owca(*this, p);
        else if (nazwa == "Trawa") newOrgaznizm = new Trawa(*this, p);
        else if (nazwa == "WilczeJagody") newOrgaznizm = new WilczeJagody(*this, p);
        else if (nazwa == "Zolw") newOrgaznizm = new Zolw(*this, p);
        else {
            cout << "Nieznany organizm: " + nazwa << endl;
            continue;
        }

        newOrgaznizm->setPower(power);
        newOrgaznizm->setAge(age);
        newOrgaznizm->setInitiative(initiative);
        addOrganism(newOrgaznizm, p);
    }

    plik.close();
    cout << "Gra zostala wczytana z pliku: " << nazwaPliku << endl;
    updateMap();
}

void Swiat::setLoadFlag(bool value, string &nazwaPliku) {
    this->flagLoad = value;
    fileToSave = nazwaPliku;
}

void Swiat::setSaveFlag(bool value, string &nazwaPliku) {
    this->flagSave = value;
    fileToSave = nazwaPliku;
}

void Swiat::fileOperations() {
    if (flagLoad) {
        flagLoad = false;
        loadGameFromFile(fileToSave);
        return;
    }

    if (flagSave) {
        flagSave = false;
        saveToFile(fileToSave);
        return;
    }
}




