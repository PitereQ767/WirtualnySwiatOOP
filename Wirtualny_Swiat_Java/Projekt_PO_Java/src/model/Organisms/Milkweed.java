package model.Organisms;

import model.Organism;
import model.Plant;
import model.Point;
import model.World;

import java.awt.*;

public class Milkweed extends Plant {
    private final Color milkweedColor = Color.BLUE;

    public Milkweed(World world, Point position){
        super(world, position, 0);
    }

    @Override
    public String getNazwa(){
        return "Mlecz";
    }

    @Override
    public Color getColor(){
        return milkweedColor;
    }

    @Override
    public Organism makeNewOrganism(Point position){
        return new Milkweed(world, position);
    }
}
