package model.Organisms;

import model.Organism;
import model.Plant;
import model.Point;
import model.World;

import java.awt.*;

public class Berries extends Plant {
    private final Color berriesColor = Color.MAGENTA;

    public Berries(World world, Point position){
        super(world, position, 99);
    }

    @Override
    public String getNazwa(){
        return "WilczeJagody";
    }

    @Override
    public Color getColor(){
        return berriesColor;
    }

    @Override
    public Organism makeNewOrganism(Point position){
        return new Berries(world, position);
    }

    @Override
    public void Collision(Organism attacker){
        attacker.setAlive(false);
        world.addEvent(attacker.getNazwa() + " zjadł wilcze jaody i zmarl");
    }
}
