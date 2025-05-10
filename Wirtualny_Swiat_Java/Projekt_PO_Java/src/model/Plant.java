package model;

public abstract class Plant extends Organism {
    private final int SPREAD_CHANCE = 1;


    public Plant(World world, Point position, int power){
        super(world, position, power, 0);
    }


    @Override
    public void Action(){
        increaseAge();

        if (trySpread()){
            Point newPos = findPosition();
            if (newPos.getX() != getPosition().getX() || newPos.getY() != getPosition().getY()){
                Organism newOrganism = makeNewOrganism(newPos);
                world.addOrganism(newOrganism, newPos);
                world.addEvent(newOrganism.getNazwa() + " rozmnożył sie na pozycji " + newPos.getX() + ", " + newPos.getY() + ")");
            }
        }
    }

    protected boolean trySpread(){
        int rand = random.nextInt(100);
        return rand < SPREAD_CHANCE;
    }

    @Override
    public void Collision(Organism attacker){
        collisionHelper(attacker, this.getPosition());
    }
}
