package simstation;

import mvc.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

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
                && (simulation.isRunning() || simulation.isSuspended())) {
            Utilities.error("Cannot save while simulation is active. Please stop it first.");
            return;
        }
        super.actionPerformed(ae);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }

    // no main because we don't run the basic framework
}
