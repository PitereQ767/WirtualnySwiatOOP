package model.Organisms;

import model.Animal;
import model.Organism;
import model.Point;
import model.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.geom.Point2D.distance;

public class Cyber_Sheep extends Animal {
    private Color cyberColor = Color.CYAN;

    public Cyber_Sheep(World world, Point position){
        super(world, position, 11, 4);
    }

    @Override
    public Color getColor(){
        return cyberColor;
    }

    @Override
    public String getNazwa(){
        return "Cyber_Owca";
    }

    @Override
    public void Action(){
        Point currentPos = getPosition();
        List<Point> barszcze = new ArrayList<>();
        List<Organism> organisms = world.getOrganisms();
        Point closest = null;
        double minDist = 100000;

        for (Organism o : organisms){
            if (o instanceof BarszczSosnowskiego){
                barszcze.add(o.getPosition());
            }
        }

        for(Point barszcz : barszcze){
            double dist = distance(this.getPosition().getX(), this.getPosition().getY(), barszcz.getX(), barszcz.getY());
            if (dist < minDist){
                minDist = dist;
                closest = new Point(barszcz.getX(), barszcz.getY());
            }
        }

        if (closest != null){
            int dx = Integer.compare(closest.getX(), currentPos.getX());
            int dy = Integer.compare(closest.getY(), currentPos.getY());

            Point newPos = new Point(currentPos.getX() + dx, currentPos.getY() + dy);

            world.tryToMoveOrganism(this, newPos);
        }
    }
}
