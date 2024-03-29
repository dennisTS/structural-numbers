package ua.kpi.kafpe.snm.callback;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class MockCallableGraph implements CallableGraph {

    private final Set<Integer> removedEdges;

    public MockCallableGraph() {
        removedEdges = new HashSet<>();
    }

    @Override
    public boolean hasOrientationAgreement(Set<Integer> removedEdges) throws UnsupportedOperationException {
        this.removedEdges.addAll(removedEdges);

        if (this.removedEdges.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No edges were removed");
        }

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

}
