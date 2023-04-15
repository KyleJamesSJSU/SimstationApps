package flocking;

import mvc.*;
import simstation.*;

public class FlockingSimulation extends Simulation {
    public void populate() {
        for(int i = 0; i < 50; i++)
            addAgent(new Bird());
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }
}
