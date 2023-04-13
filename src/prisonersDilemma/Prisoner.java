package prisonersDilemma;

import simstation.*;
import mvc.*;
public class Prisoner extends Agent {

    // variables
    private Strategy strategy;
    private int fitness;
    private boolean partnerCheated;

    // constructors
    public Prisoner() {
        super();
        fitness = 0;
        partnerCheated = false;
    }

    // getters
    public int getFitness() {
        return fitness;
    }
    public boolean isPartnerCheated() {
        return partnerCheated;
    }

    // setters
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    // methods from prof's diagram
    public void update() {
        // TODO write game logic

    }

    public void updateFitness(int amt) {
        this.fitness += amt;
    }

}
