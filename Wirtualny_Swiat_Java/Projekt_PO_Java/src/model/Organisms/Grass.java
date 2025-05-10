package model.Organisms;

import model.Organism;
import model.Plant;
import model.Point;
import model.World;

import java.awt.*;

public class Grass extends Plant {
    private final Color grassColor = Color.BLACK;

    public Grass(World world, Point position){
        super(world, position, 0);
    }

    @Override
    public Color getColor(){
        return grassColor;
    }

    @Override
    public String getNazwa(){
        return "Trawa";
    }

    @Override
    public Organism makeNewOrganism(Point position){
        return new Grass(world, position);
    }
}
