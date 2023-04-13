package prisonersDilemma;

import simstation.*;
import mvc.*;

public class PrisonerSimulation extends Simulation {

    private final static int PRISONERS_PER_STRATEGY = 10;

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

}
