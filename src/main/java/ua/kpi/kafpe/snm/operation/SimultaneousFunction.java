package ua.kpi.kafpe.snm.operation;

import org.apache.commons.math3.complex.Complex;
import ua.kpi.kafpe.snm.StructuralNumber;
import ua.kpi.kafpe.snm.StructuralNumberColumn;
import ua.kpi.kafpe.snm.callback.CallableGraph;
import ua.kpi.kafpe.snm.exception.SizeInconsistencyException;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class SimultaneousFunction extends StructuralNumber.StructuralNumberOperation {

    private static final String INVALID_EDGE_MESSAGE = "One of the edges is not present in the number";

    private StructuralNumber number;
    private CallableGraph graph;
    private Map<Integer, Complex> numberMap;

    private StringBuilder stringFormula;

    public SimultaneousFunction(StructuralNumber number, CallableGraph graph, Map<Integer, Complex> numberMap) {
        checkNotNull(number);
        checkNotNull(graph);
        checkNotNull(numberMap);

        this.number = copyStructuralNumber(number);
        this.graph = graph;
        this.numberMap = Collections.unmodifiableMap(numberMap);
    }

    public Complex calculate(Integer alpha, Integer beta) {
        checkNotNull(alpha);
        checkNotNull(beta);

        if (number.equals(StructuralNumber.NULL))
            return Complex.NaN;

        if (!(isEdgeValid(alpha) && isEdgeValid(beta)))
            throw new SizeInconsistencyException(INVALID_EDGE_MESSAGE);

        StructuralNumber differentiationByAlpha = new AlgebraicDifferentiation(number, alpha).perform();
        StructuralNumber differentiationByBeta = new AlgebraicDifferentiation(number, beta).perform();

        StructuralNumber conjunction = new Conjunction(differentiationByAlpha, differentiationByBeta).perform();

        return calculateNumber(conjunction);
    }

    private Complex calculateNumber(StructuralNumber conjunction) {
        Complex result = Complex.ZERO;

        for (StructuralNumberColumn column : getColumnsCopyFromNumber(conjunction)) {
            boolean isPlus = graph.hasOrientationAgreement(column.getInnerColumnCopy());

            StructuralNumber tempNumber = newStructuralNumber();
            addColumnToNumber(column, tempNumber);

            Determinant determinant = new Determinant(tempNumber, numberMap);
            Complex complexDeterminant = determinant.calculate();

            result = isPlus ? result.add(complexDeterminant) : result.subtract(complexDeterminant);
        }

        return result;
    }

    private boolean isEdgeValid(Integer edgeNumber) {
        return (edgeNumber > 0);
    }
}
