package prisonersDilemma;

import simstation.*;
import mvc.*;

import java.util.*;

public class PrisonerSimulation extends Simulation {

    private final static int PRISONERS_PER_STRATEGY = 10; // amount of prisoners made per strategy
    public final static int STEP_VARIANCE = 10; // distance from 1 to X inclusive that prisoners will step between
    public final static int SEARCH_RADIUS = 10; // distance for prisoners to search for partners to play with

    public void populate() {
        for (int i = 0; i < PRISONERS_PER_STRATEGY; i++) { // for however many prisoners we want
            for (int j = 0; j < Strategy.STRATEGY_COUNT; j++) { // for however many strategies we have
                addAgent(createPrisoner(j)); // create a prisoner with strategy j and add it
            }
        }
    }

    private Prisoner createPrisoner(int type) {
        Prisoner p = new Prisoner();
        switch (type) {
            case 0: {
                p.setStrategy(new Cooperate(p));
                break;
            }
            case 1: {
                p.setStrategy(new RandomlyCooperate(p));
                break;
            }
            case 2: {
                p.setStrategy(new Cheat(p));
                break;
            }
            case 3: {
                p.setStrategy(new Tit4Tat(p));
                break;
            }
        }
        return p;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PrisonerFactory());
        panel.display();
    }

    // Returns a table of all 4 averages, formatted for stats dialogue box
    public synchronized String[] getAverageStats() {
        String[] messages = new String[Strategy.STRATEGY_COUNT];
        double cooperate = 0;
        double randomly_cooperate = 0;
        double cheat = 0;
        double tit4tat = 0;

        Iterator<Agent> it = this.agentIterator();
        while (it.hasNext()) {
            // get next agent
            Prisoner p = (Prisoner)it.next();
            double fitnessAvg = p.getFitness() / (double)PRISONERS_PER_STRATEGY;
            StrategyType type = p.getStrategy().getType();
            switch (type) {
                case COOPERATE: {
                    cooperate += fitnessAvg;
                    break;
                }
                case RANDOMLY_COOPERATE: {
                    randomly_cooperate += fitnessAvg;
                    break;
                }
                case CHEAT: {
                    cheat += fitnessAvg;
                    break;
                }
                case TIT4TAT: {
                    tit4tat += fitnessAvg;
                    break;
                }
            }
        }

        // format message
        messages[0] = "Cooperate average fitness = " + cooperate;
        messages[1] = "Randomly-Cooperate average fitness = " + randomly_cooperate;
        messages[2] = "Cheat average fitness = " + cheat;
        messages[3] = "Tit4Tat average fitness = " + tit4tat;

        return messages;
    }

}
