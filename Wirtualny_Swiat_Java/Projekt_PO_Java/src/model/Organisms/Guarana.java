package model.Organisms;

import model.Organism;
import model.Plant;
import model.Point;
import model.World;

import java.awt.*;

public class Guarana extends Plant {
    private final Color guaranaColor = Color.gray;

    public Guarana(World world, Point position){
        super(world, position, 0);
    }

    @Override
    public String getNazwa(){
        return "Guarana";
    }

    @Override
    public Color getColor(){
        return guaranaColor;
    }

    @Override
    public Organism makeNewOrganism(Point position){
        return new Guarana(world, position);
    }
}
