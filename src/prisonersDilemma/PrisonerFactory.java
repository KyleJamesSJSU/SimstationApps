package prisonersDilemma;

import simstation.*;
import mvc.*;

public class PrisonerFactory extends SimulationFactory {

    @Override
    public Model makeModel() {
        return new PrisonerSimulation();
    }

    @Override
    public View makeView(Model m) {
        return new PrisonerView(m);
    }

    @Override
    public String getTitle() {
        return "Prisoner's Dilemma Simulation";
    }

    @Override
    protected StatsCommand statsCommand(Model m) {
        return new PrisonerStatsCommand(m);
    }

}

class PrisonerStatsCommand extends StatsCommand {
    public PrisonerStatsCommand(Model m) {
        super(m);
    }

    @Override
    protected String[] getStatsMessage() {
        // get specific stats from model
        Simulation simulation = (Simulation)model;
        // create string for inform
        return new String[]{"TODO write stats"};
    }
}