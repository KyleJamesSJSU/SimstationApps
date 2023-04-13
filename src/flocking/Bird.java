package flocking;

import simstation.*;
import mvc.*;
public class Bird extends Agent {
    public int speed;
    public static int MAX_SPEED = 5;

    public Bird()
    {
        // speed of birds range from 1 to MAX_SPEED
        this.speed = Utilities.rng.nextInt(MAX_SPEED) + 1;
        heading = Heading.random();
    }

    @Override
    public void update() {
        FlockingSimulation simulation = (FlockingSimulation) world;
        // bird copies properties of neighbor if it can find one
        Bird neighbor = (Bird) simulation.getNeighbor(this, 10);
        if(neighbor != null)
        {
            this.heading = neighbor.heading;
            this.speed = neighbor.speed;
        }
        move(speed);
    }
}
