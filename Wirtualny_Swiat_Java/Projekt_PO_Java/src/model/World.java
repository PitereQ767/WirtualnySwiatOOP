package model;

import model.Organisms.*;

import java.io.*;
import java.util.*;

public class World {
    private int width, height;
    private List<Organism> organisms;
    private List<String> events = new ArrayList<>();
    private final int numberOfOrganisms = 20;
    private final int numberOfTypeOrganisms = 10;
    private final Random random = new Random();
    public boolean endGame = false;
    private int tura = 0;

    public enum Direction {
        GORA, DOL, LEWO, PRAWO
    }
    public Direction humanMove = null;

    public int getTura() {
        return tura;
    }

    public void setTura(int tura) {
        this.tura = tura;
    }

    public World(int width, int height){
        this.width = width;
        this.height = height;
        this.organisms = new ArrayList<>();
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public boolean isEmpty(Point position){
        for (Organism o : organisms){
            if (o.position.getX() == position.getX() && o.position.getY() == position.getY()){
                return false;
            }
        }
        return true;
    }

    public boolean isCorrectPosition(Point position){
        int x = position.getX();
        int y = position.getY();

        if (x < 0 || y < 0 || x >= width || y>=height){
            return false;
        }

        return true;
    }

    public void addOrganism(Organism org, Point position){
        org.position.setX(position.getX());
        org.position.setY(position.getY());
        organisms.add(org);
    }

    private Organism createRandomOrganism(Point position){
        int randomNum = random.nextInt(numberOfTypeOrganisms);

        switch (randomNum){
            case 0: return new Wolf(this, position);
            case 1: return new Fox(this, position);
            case 2: return new Turtle(this, position);
            case 3: return new Sheep(this, position);
            case 4: return new Antelope(this, position);
//            case 5: return new Cyber_Sheep(this, position);
            case 5: return new BarszczSosnowskiego(this, position);
            case 6: return new Guarana(this, position);
            case 7: return new Grass(this, position);
            case 8: return new Milkweed(this, position);
            case 9: return new Berries(this, position);
        }

        return null;
    }

    public Point getEmptyAndCorrectPosition(){
        Point position;
        do{
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            position = new Point(x, y);
        }while (!isEmpty(position) || !isCorrectPosition(position));

        return position;
    }

    public void createWorld() {
        for (int i = 0; i < numberOfOrganisms; i++){
            Point newPosition = getEmptyAndCorrectPosition();

            Organism newOrganism = createRandomOrganism(newPosition);
            addOrganism(newOrganism, newPosition);
        }

        Point newPosition = getEmptyAndCorrectPosition();
        Organism newOrganism = new Human(this, newPosition);
        addOrganism(newOrganism, newPosition);
    }

    public Human getHuman(){
        for (Organism o : organisms){
            if (o instanceof Human){
                return (Human) o;
            }
        }
        return null;
    }

    public void killHuman(){
        Human human = getHuman();
        if(!human.getImmortality()){
            human.setAlive(false);
            endGame = true;
            addEvent("Człowiek zginął");
        }else{
            addEvent("Człowiek przeżył dzięki aktywowanej nieśmiertelności");
        }
    }

    public int getNumberOfOrganisms() {
        return numberOfOrganisms;
    }

    public List<Organism> getOrganisms() {
        return organisms;
    }

    public void sortOrganisms(){
        organisms.sort(
                Comparator.comparing(Organism::getInitiative)
                        .thenComparing(Organism::getAge).reversed()
        );
    }

    private void cleadDeadOrganisms(){
        organisms.removeIf(o -> !o.getIsAlive());
    }

    public void makeRun(){
        sortOrganisms();
        for (Organism o : new ArrayList<>(organisms)) {
            if (o.getIsAlive()){
                o.Action();
            }
        }
        cleadDeadOrganisms();
    }

    public void moveOrganism(Organism org, Point position){
        org.setPosition(position);
    }

    public void tryToMoveOrganism(Organism org, Point position){
        if (!isCorrectPosition(position)){
            addEvent(org.getNazwa() + " probował wyjść za mape");
        }else{
            if (isEmpty(position)){
                moveOrganism(org, position);
                addEvent(org.getNazwa() + " przesunal sie na (" + position.getX() + ", " + position.getY() + ")");
            }else {
                Organism other = getOrganismaAtPosition(position);
                other.Collision(org);

            }
        }
    }

    public Organism getOrganismaAtPosition(Point position){
        for (Organism o : organisms){
            if (o.getPosition().getX() == position.getX() && o.getPosition().getY() == position.getY()){
                return o;
            }
        }

        return null;
    }

    public void addEvent(String message){
        events.add(message);
    }

    public List<String> getEvents(){
        return events;
    }

    public void clearEventLog(){
        events.clear();
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void saveToFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"))){
            String stringWidth = new String(String.valueOf(width));
            String stringHeight = new String(String.valueOf(height));
            String turaString = new String(String.valueOf(getTura()));
            String immortalityString;
            String immortalityRounds;

            if (getHuman().getImmortality()){
                 immortalityString =  String.valueOf(1);
                 immortalityRounds = String.valueOf(getHuman().getImortalityRounds());
            }else {
                 immortalityString =  String.valueOf(0);
                 immortalityRounds = String.valueOf(0);
            }
            writer.write(stringWidth + " " + stringHeight + " " + turaString);
            writer.newLine();

            Organism human = getHuman();
            writer.write(human.getNazwa() + " " + human.getPosition().getX() + " " + human.getPosition().getY() + " " + human.getPower() + " " + human.getInitiative() + " " + human.getAge() + " " + immortalityString + " " + immortalityRounds);
            writer.newLine();

            for (Organism o : organisms){
                if (o.getIsAlive()){
                    if (o instanceof Human){
                        continue;
                    }
                    writer.write(o.getNazwa() + " " + o.getPosition().getX() + " " + o.getPosition().getY() + " " + o.getPower() + " " + o.getInitiative() + " " + o.getAge());
                    writer.newLine();
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadGameFromFile(){
        organisms.clear();
        events.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("save.txt"))){
            String line;

            line = reader.readLine();
            String[] parts = line.split(" ");
            width = Integer.valueOf(parts[0]);
            height = Integer.valueOf(parts[1]);
            tura = Integer.valueOf(parts[2]);

            line = reader.readLine();
            String[] parts3 = line.split(" ");
            int xHuman = Integer.parseInt(parts3[1]);
            int yHuman = Integer.parseInt(parts3[2]);
            int powerHuman = Integer.parseInt(parts3[3]);
            int initiativeHuman = Integer.parseInt(parts3[4]);
            int ageHuman = Integer.parseInt(parts3[5]);
            int immortality = Integer.parseInt(parts3[6]);
            int immortalityRounds = Integer.parseInt(parts3[7]);

            Point pHuman = new Point(xHuman, yHuman);
            Human human = new Human(this, pHuman);
            if (immortality == 1){
                human.setImmortality(true);
                human.setImortalityRounds(immortalityRounds);
            }
            human.setPower(powerHuman);
            human.setInitiative(initiativeHuman);
            human.setAge(ageHuman);
            addOrganism(human, pHuman);


            while ((line = reader.readLine()) != null){

                String[] parts2 = line.split(" ");
                Organism newOrgaznizm;
                int x = Integer.parseInt(parts2[1]);
                int y = Integer.parseInt(parts2[2]);
                int power = Integer.parseInt(parts2[3]);
                int initiative = Integer.parseInt(parts2[4]);
                int age = Integer.parseInt(parts2[5]);
                Point p = new Point(x, y);


                if (Objects.equals(parts2[0], "Wilk")) newOrgaznizm = new Wolf(this, p);
                else if (Objects.equals(parts2[0], "Lis")) newOrgaznizm = new Fox(this, p);
                else if (Objects.equals(parts2[0], "Antylopa")) newOrgaznizm = new Antelope(this, p);
                else if (Objects.equals(parts2[0], "BarszczSosnowskiego")) newOrgaznizm = new BarszczSosnowskiego(this, p);
                else if (Objects.equals(parts2[0], "CyberOwca")) newOrgaznizm = new Cyber_Sheep(this, p);
                else if (Objects.equals(parts2[0], "Guarana")) newOrgaznizm = new Guarana(this, p);
                else if (Objects.equals(parts2[0], "Mlecz")) newOrgaznizm = new Milkweed(this, p);
                else if (Objects.equals(parts2[0], "Owca")) newOrgaznizm = new Sheep(this, p);
                else if (Objects.equals(parts2[0], "Trawa")) newOrgaznizm = new Grass(this, p);
                else if (Objects.equals(parts2[0], "WilczeJagody")) newOrgaznizm = new Berries(this, p);
                else if (Objects.equals(parts2[0], "Zolw")) newOrgaznizm = new Turtle(this, p);
                else {
                    System.out.println("Nieznany organizm przy wczytywaniu pliku" + parts2[0]);
                    continue;
                }

                newOrgaznizm.setPower(power);
                newOrgaznizm.setInitiative(initiative);
                newOrgaznizm.setAge(age);

                addOrganism(newOrgaznizm, p);
            }
        }catch (IOException e){
            e.printStackTrace();;
        }
    }
}
