package model.Organisms;

import model.Organism;
import model.Point;
import model.World;
import model.Animal;

import java.awt.*;

public class Wolf extends Animal {
    private Color wilkColor = Color.GRAY;

    public Wolf(World world, Point position){
        super(world, position, 9, 5);
    }

    @Override
    public Color getColor(){
        return wilkColor;
    }

    @Override
    public String getNazwa(){
        return "Wilk";
    }

    @Override
    public Organism makeNewOrganism(Point position){
        return new Wolf(world, position);
    }

}
