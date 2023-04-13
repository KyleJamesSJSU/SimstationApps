package prisonersDilemma;

import simstation.*;
import mvc.*;

import java.awt.*;
import java.util.Iterator;

public class PrisonerView extends SimulationView {

    public PrisonerView(Model m) {
        super(m);
        this.setBackground(Color.DARK_GRAY); // different from SimulationView
    }

    private final Color COLOR_COOPERATE = Color.GREEN;
    private final Color COLOR_RANDOMLY_COOPERATE = Color.YELLOW;
    private final Color COLOR_CHEAT = Color.RED;
    private final Color COLOR_TIT4TAT = Color.BLUE;


    @Override
    protected void drawAgents(Graphics gc) {
        // custom agent drawing
        PrisonerSimulation ps = (PrisonerSimulation)model;
        // get agent iterator
        Iterator<Agent> it = ps.agentIterator();

        // draw a circle for every agent centered on the agent's x and y
        int centerOffset = AGENT_SIZE / 2;
        gc.setColor(agentColor);
        while (it.hasNext()) {
            Prisoner c = (Prisoner)it.next();
            StrategyType type = c.getStrategy().getType();
            switch (type) {
                case COOPERATE: {
                    gc.setColor(COLOR_COOPERATE);
                    break;
                }
                case RANDOMLY_COOPERATE: {
                    gc.setColor(COLOR_RANDOMLY_COOPERATE);
                    break;
                }
                case CHEAT: {
                    gc.setColor(COLOR_CHEAT);
                    break;
                }
                case TIT4TAT: {
                    gc.setColor(COLOR_TIT4TAT);
                    break;
                }
            }
            // draw prisoner
            gc.fillOval(c.xc - centerOffset, c.yc - centerOffset, AGENT_SIZE, AGENT_SIZE);
        }
    }
}
