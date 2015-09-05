package ua.kpi.kafpe.snm.callback;

import java.util.Set;

public interface CallableGraph {

    boolean hasOrientationAgreement(Set<Integer> removedEdges) throws UnsupportedOperationException;
}
