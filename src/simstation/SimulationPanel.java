package simstation;

import mvc.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.*;

public class SimulationPanel extends AppPanel {

    public SimulationPanel(SimulationFactory factory) {
        super(factory);
        String[] strings = new String[]{"Start", "Suspend", "Resume", "Stop", "Stats"};
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        for (String s : strings) {
            JPanel panel = new JPanel();
            JButton button = new JButton(s);
            panel.add(button);
            button.addActionListener(this);
            controlPanel.add(panel);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmmd = ae.getActionCommand();
        Simulation simulation = (Simulation) model;

        if ((cmmd == "Save" || cmmd == "SaveAs")
                && (simulation.isRunning() && !simulation.isSuspended())) {
            Utilities.error("Cannot save active simulation that isn't suspended. Please suspend it first.");
            return;
        }
        super.actionPerformed(ae);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        if(evt.getPropertyName() == "New"
                || evt.getPropertyName() == "Open") {
            // start all loaded agents to initialize their threads
            Iterator<Agent> ai = ((Simulation)evt.getNewValue()).agentIterator();
            while (ai.hasNext()) {
                ai.next().start();
            }
        }
    }

    // no main because we don't run the basic framework
}
