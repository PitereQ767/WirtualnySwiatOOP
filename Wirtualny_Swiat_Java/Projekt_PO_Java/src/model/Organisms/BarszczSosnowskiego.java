package model.Organisms;

import model.Organism;
import model.Point;
import model.World;

import java.awt.*;

public class BarszczSosnowskiego extends Plant {
    private final Color barszczColor = Color.RED;

    public BarszczSosnowskiego(World world, Point position){
        super(world, position, 10);
    }

    @Override
    public Color getColor(){
        return barszczColor;
    }

    @Override
    public String getNazwa(){
        return "BarszczSosnowskiego";
    }

    @Override
    public Organism makeNewOrganism(Point position){
        return new BarszczSosnowskiego(world, position);
    }
}
