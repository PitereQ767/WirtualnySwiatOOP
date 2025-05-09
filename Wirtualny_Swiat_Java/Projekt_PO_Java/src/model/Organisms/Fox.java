package model.Organisms;

import model.Animal;
import model.Point;
import model.World;

import javax.swing.text.Position;
import java.awt.*;

public class Fox extends Animal {
    private Color foxColor = Color.ORANGE;

    public Fox(World world, Point position){
        super(world, position, 3, 7);
    }

    @Override
    public Color getColor(){
        return foxColor;
    }
}
