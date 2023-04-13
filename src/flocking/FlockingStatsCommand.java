package flocking;

import mvc.*;
import simstation.*;
import java.util.Iterator;

public class FlockingStatsCommand extends StatsCommand {
    public FlockingStatsCommand(Model model) {
        super(model);
    }
    private String[] getStatsMessage() {
        // get specific stats from model
        Simulation simulation = (Simulation) model;
        String[] result = new String[Bird.MAX_SPEED];
        int[] speedList = new int[Bird.MAX_SPEED];
        Iterator<Agent> it = simulation.agentIterator();
        while(it.hasNext())
        {
            Bird curr = (Bird) it.next();
            speedList[curr.speed-1]++;
        }
        for(int i = 1; i < Bird.MAX_SPEED+1; i++)
        {
            result[i-1] = "#birds @ speed " + i + " = " + speedList[i-1];
        }
        // create string for inform
        return result;
    }
    public void execute() throws Exception {
        // send message
        Utilities.inform(getStatsMessage());
    }
}
