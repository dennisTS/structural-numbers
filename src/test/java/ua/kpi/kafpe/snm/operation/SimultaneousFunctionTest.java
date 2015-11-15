package ua.kpi.kafpe.snm.operation;

import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.kpi.kafpe.snm.StructuralNumber;
import ua.kpi.kafpe.snm.callback.CallableGraph;
import ua.kpi.kafpe.snm.callback.MockCallableGraph;
import ua.kpi.kafpe.snm.exception.SizeInconsistencyException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SimultaneousFunctionTest {

    private StructuralNumber number;
    private CallableGraph graph;
    private Map<Integer, Complex> numberMap;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        number = new StructuralNumber.Builder()
                .addColumn(1, 4, 2)
                .addColumn(1, 4, 3)
                .addColumn(1, 5, 2)
                .addColumn(1, 5, 3)
                .addColumn(1, 5, 4)
                .addColumn(1, 6, 2)
                .addColumn(1, 6, 3)
                .addColumn(1, 6, 4)
                .addColumn(2, 4, 3)
                .addColumn(2, 5, 3)
                .addColumn(2, 6, 3)
                .addColumn(2, 6, 4)
                .addColumn(5, 4, 3)
                .addColumn(5, 6, 2)
                .addColumn(5, 6, 3)
                .addColumn(5, 6, 4)
                .build();

        graph = new MockCallableGraph();

        numberMap = new HashMap<>();
        numberMap.put(1, Complex.valueOf(1));
        numberMap.put(2, Complex.valueOf(2));
        numberMap.put(3, Complex.valueOf(3));
        numberMap.put(4, Complex.valueOf(4));
        numberMap.put(5, Complex.valueOf(5));
        numberMap.put(6, Complex.valueOf(6));
    }

    @Test
    public void testPassNullStructuralNumbers() {
        final Complex expResult = Complex.NaN;

        final Complex actResult = new SimultaneousFunction(StructuralNumber.NULL, graph, numberMap).calculate(1, 1);

        assertEquals(expResult, actResult);
    }

    @Test
    public void testPassZeroAlpha() {
        exception.expect(SizeInconsistencyException.class);
        exception.expectMessage("One of the edges is not present in the number");

        final StructuralNumber number = new StructuralNumber.Builder()
                .addColumn(1)
                .build();

        final Complex actResult = new SimultaneousFunction(number, graph, numberMap).calculate(0, 1);
    }

    @Test
    public void testPassZeroBeta() {
        exception.expect(SizeInconsistencyException.class);
        exception.expectMessage("One of the edges is not present in the number");

        final StructuralNumber number = new StructuralNumber.Builder()
                .addColumn(1)
                .build();

        final Complex actResult = new SimultaneousFunction(number, graph, numberMap).calculate(1, 0);
    }

    @Test
    public void testCalculate() {
        final Complex expResult = Complex.valueOf(3);

        final Complex actResult = new SimultaneousFunction(number, graph, numberMap).calculate(1, 4);

        assertEquals(expResult, actResult);
    }
}
