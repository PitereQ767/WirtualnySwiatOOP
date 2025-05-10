package model.Organisms;

import model.Organism;
import model.Point;
import model.World;

import java.awt.*;

public class BarszczSosnowskiego extends Organism {
    private final Color barszczColor = Color.RED;

    public BarszczSosnowskiego(World world, Point position){
        super(world, position, 10, 0);
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
    public void Action(){

    }
}
