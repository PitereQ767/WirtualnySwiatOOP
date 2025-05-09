package model;

import model.Organisms.Wolf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private final int width, height;
    private List<Organism> organisms;
    private final int numberOfOrganisms = 10;
    private final int numberOfTypeOrganisms = 1;
    private final Random random = new Random();

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

    private void addOrganism(Organism org, Point position){
        org.position.setX(position.getX());
        org.position.setY(position.getY());
        organisms.add(org);
    }

    private Organism createRandomOrganism(Point position){
        int randomNum = random.nextInt(numberOfTypeOrganisms);

        switch (randomNum){
            case 0: return new Wolf(this, position);
        }

        return null;
    }

    public void createWorld() {
        for (int i = 0; i < numberOfOrganisms; i++){
            Point position;
            do{
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                position = new Point(x, y);
            } while (!isEmpty(position));

            Organism newOrganism = createRandomOrganism(position);
            addOrganism(newOrganism, position);
        }
    }

    public int getNumberOfOrganisms() {
        return numberOfOrganisms;
    }

    public List<Organism> getOrganisms() {
        return organisms;
    }
}
