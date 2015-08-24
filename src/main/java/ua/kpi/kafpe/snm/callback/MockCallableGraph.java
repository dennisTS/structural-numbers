package ua.kpi.kafpe.snm.callback;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class MockCallableGraph extends CallableGraph {

    private Set<Integer> removedEdges;

    public MockCallableGraph() {
        removedEdges = new HashSet<>();
    }

    @Override
    public boolean hasOrientationAgreement() throws UnsupportedOperationException {
        if (removedEdges.isEmpty())
            JOptionPane.showMessageDialog(null, "No edges were removed");

        int choice = JOptionPane.showOptionDialog(null,
                "Does the graph have orientation agreement after removing edges " + removedEdges + "?",
                "Orientation check",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null);

        if (choice == JOptionPane.OK_OPTION)
            return true;

        return false;
    }

    @Override
    public void removeEdges(Set<Integer> edges) {
        removedEdges.addAll(edges);
    }
}
