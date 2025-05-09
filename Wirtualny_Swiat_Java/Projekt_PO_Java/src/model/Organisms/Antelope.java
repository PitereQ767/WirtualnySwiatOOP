package model.Organisms;

import model.Animal;
import model.Point;
import model.World;

import java.awt.*;

public class Antelope extends Animal {
    private Color antylopeColor = Color.YELLOW;

    public Antelope(World world, Point position){
        super(world, position, 4, 4);
    }

    @Override
    public Color getColor(){
        return antylopeColor;
    }
}
