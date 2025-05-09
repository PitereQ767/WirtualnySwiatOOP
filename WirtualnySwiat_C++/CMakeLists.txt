cmake_minimum_required(VERSION 3.29)
project(Wirtualny_Swiat_C__)

set(CMAKE_CXX_STANDARD 20)


add_executable(Wirtualny_Swiat_C__ main.cpp
        pliki_naglowkowe/Swiat.h
        pliki_zrodlowe/Swiat.cpp
        pliki_naglowkowe/Organizm.h
        pliki_zrodlowe/Organizm.cpp
        pliki_naglowkowe/Zwierze.h
        pliki_zrodlowe/Zwierze.cpp
        pliki_zrodlowe/Wilk.cpp
        pliki_naglowkowe/Wilk.h
        pliki_naglowkowe/Punkt.h
        pliki_zrodlowe/Punkt.cpp
        pliki_zrodlowe/Owca.cpp
        pliki_naglowkowe/Owca.h
        pliki_naglowkowe/Zolw.h
        pliki_zrodlowe/Zolw.cpp
        pliki_zrodlowe/Antylopa.cpp
        pliki_naglowkowe/Antylopa.h
        pliki_zrodlowe/Cyber_Owca.cpp
        pliki_naglowkowe/Cyber_Owca.h
        pliki_zrodlowe/Czlowiek.cpp
        pliki_naglowkowe/Czlowiek.h
        pliki_naglowkowe/Lis.h
        pliki_zrodlowe/Lis.cpp
        pliki_naglowkowe/Roslina.h
        pliki_zrodlowe/Roslina.cpp
        pliki_naglowkowe/Trawa.h
        pliki_zrodlowe/Trawa.cpp
        pliki_naglowkowe/Mlecz.h
        pliki_zrodlowe/Mlecz.cpp
        pliki_naglowkowe/Guarana.h
        pliki_zrodlowe/Guarana.cpp
        pliki_naglowkowe/WilczeJagody.h
        pliki_zrodlowe/WilczeJagody.cpp
        pliki_naglowkowe/BarszczSosnowskiego.h
        pliki_zrodlowe/BarszczSosnowskiego.cpp
        pliki_naglowkowe/OrganismType.h)
