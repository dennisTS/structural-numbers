package ua.kpi.kafpe.snm.operation;

import org.apache.commons.math3.complex.Complex;
import ua.kpi.kafpe.snm.StructuralNumber;
import ua.kpi.kafpe.snm.exception.SizeInconsistencyException;

import static com.google.common.base.Preconditions.checkNotNull;

public class SimultaneousFunction extends StructuralNumber.StructuralNumberOperation {

    private static final String INVALID_EDGE_MESSAGE = "One of the edges is not present in the number";
    private final StructuralNumber number;

    private Integer alpha;
    private Integer beta;

    public SimultaneousFunction(StructuralNumber number, Integer alpha, Integer beta) {
        checkNotNull(number);

        this.number = number;
    }

    public Complex calculate() {
        if (number.equals(StructuralNumber.NULL))
            return Complex.NaN;

        if (!(isEdgeValid(alpha) && isEdgeValid(beta)))
            throw new SizeInconsistencyException(INVALID_EDGE_MESSAGE);


        return null;
    }

    private boolean isEdgeValid(Integer edgeNumber) {
        return true;
    }
}
