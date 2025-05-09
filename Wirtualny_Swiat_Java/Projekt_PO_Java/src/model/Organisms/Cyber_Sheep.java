package model.Organisms;

import model.Animal;
import model.Point;
import model.World;

import java.awt.*;

public class Cyber_Sheep extends Animal {
    private Color cyberColor = Color.CYAN;

    public Cyber_Sheep(World world, Point position){
        super(world, position, 11, 4);
    }

    @Override
    public Color getColor(){
        return cyberColor;
    }
}
