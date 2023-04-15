package flocking;

import mvc.*;
import simstation.*;

public class FlockingFactory extends SimulationFactory {
    public Model makeModel() { return new FlockingSimulation(); }
    public String getTitle() { return "Flocking";}
    protected StatsCommand statsCommand(Model m) {
        return new FlockingStatsCommand(m);
    }
}
