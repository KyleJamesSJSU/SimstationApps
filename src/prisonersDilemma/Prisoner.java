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
    public Strategy getStrategy() {
        return strategy;
    }
    public boolean isPartnerCheated() {
        return partnerCheated;
    }

    // setters
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void onStart() {
        this.fitness = 0;
        this.partnerCheated = false;
    }

    // methods from prof's diagram
    public void update() {
        // randomly walk around the simulation
        this.heading = Heading.random();
        int steps = Utilities.rng.nextInt(PrisonerSimulation.STEP_VARIANCE) + 1;
        move(steps);
        // find neighbor to play with
        Prisoner partner = (Prisoner)world.getNeighbor(this, PrisonerSimulation.SEARCH_RADIUS);
        // check that partner exists
        if (partner != null) {
            // play with partner
            this.playWith(partner);
        }
    }

    private void playWith(Prisoner partner) {
        // get choices
        boolean myChoice = cooperate();
        boolean partnerChoice = partner.cooperate();

        // get score for fitness
        if (myChoice) {
            if (partnerChoice) {
                // both cooperated
                this.updateFitness(3);
                partner.updateFitness(3);
            } else {
                // i cooperated, partner cheated
                partner.updateFitness(5);
            }
        } else {
            if (partnerChoice) {
                // i cheated, partner cooperated
                this.updateFitness(5);
            } else {
                // both cheated
                this.updateFitness(1);
                partner.updateFitness(1);
            }
        }
        // update choices
        this.partnerCheated = !partnerChoice;
        partner.partnerCheated = !myChoice;
    }

    private synchronized boolean cooperate() {
        return strategy.cooperate();
    }

    public synchronized void updateFitness(int amt) {
        this.fitness += amt;
    }

}
