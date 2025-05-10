package model;

import model.Organisms.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class World {
    private final int width, height;
    private List<Organism> organisms;
    private List<String> events = new ArrayList<>();
    private final int numberOfOrganisms = 20;
    private final int numberOfTypeOrganisms = 11;
    private final Random random = new Random();
    public boolean endGame = false;

    public enum Direction {
        GORA, DOL, LEWO, PRAWO
    }
    public Direction humanMove = null;


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
            case 5: return new Cyber_Sheep(this, position);
            case 6: return new BarszczSosnowskiego(this, position);
            case 7: return new Guarana(this, position);
            case 8: return new Grass(this, position);
            case 9: return new Milkweed(this, position);
            case 10: return new Berries(this, position);
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
}
