package model;

import model.Organisms.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class World {
    private final int width, height;
    private List<Organism> organisms;
    private final int numberOfOrganisms = 10;
    private final int numberOfTypeOrganisms = 6;
    private final Random random = new Random();

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

    private boolean isEmpty(Point position){
        for (Organism o : organisms){
            if (o.position.getX() == position.getX() && o.position.getY() == position.getY()){
                return false;
            }
        }
        return true;
    }

    private boolean isCorrectPosition(Point position){
        int x = position.getX();
        int y = position.getY();

        if (x < 0 || y < 0 || x >= width || y>=height){
            return false;
        }

        return true;
    }

    private void addOrganism(Organism org, Point position){
        org.position.setX(position.getX());
        org.position.setY(position.getY());
        organisms.add(org);
    }

    private Organism createRandomOrganism(Point position){
        int randomNum = random.nextInt(numberOfTypeOrganisms);

        switch (randomNum){
            case 0: return new Wolf(this, position);
            case 1: return new Sheep(this, position);
            case 2: return new Turtle(this, position);
            case 3: return new Fox(this, position);
            case 4: return new Antelope(this, position);
            case 5: return new Cyber_Sheep(this, position);
        }

        return null;
    }

    public Point getEmptyAndCorrectPosition(){
        Point position;
        do{
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            position = new Point(x, y);
        }while (!isEmpty(position) && !isCorrectPosition(position));

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

    public int getNumberOfOrganisms() {
        return numberOfOrganisms;
    }

    public List<Organism> getOrganisms() {
        return organisms;
    }

    public void sortOrganisms(){
        organisms.sort(
                Comparator.comparing(Organism::getInitiative).reversed()
                        .thenComparing(Organism::getAge).reversed()
        );
    }
}
