package ua.kpi.kafpe.snm.callback;

import java.util.Set;

public abstract class CallableGraph {

    public abstract boolean hasOrientationAgreement() throws UnsupportedOperationException;

    public abstract void removeEdges(Set<Integer> edges);
}
