package ua.kpi.kafpe.snm.operation;

import org.apache.commons.math3.complex.Complex;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.kpi.kafpe.snm.StructuralNumber;
import ua.kpi.kafpe.snm.exception.SizeInconsistencyException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SimultaneousFunctionTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testPassNullStructuralNumbers() {
        final Complex expResult = Complex.NaN;

        final Complex actResult = new SimultaneousFunction(StructuralNumber.NULL, 1, 1).calculate();

        assertEquals(expResult, actResult);
    }

    @Test
    public void testPassZeroAlpha() {
        exception.expect(SizeInconsistencyException.class);
        exception.expectMessage("One of the edges is not present in the number");

        final StructuralNumber number = new StructuralNumber.Builder()
                .addColumn(1)
                .build();

        final Complex actResult = new SimultaneousFunction(number, 0, 1).calculate();
    }

    @Test
    public void testPassZeroBeta() {
        final Complex expResult = Complex.NaN;

        final Complex actResult = new SimultaneousFunction(StructuralNumber.NULL, 1, 1).calculate();

        assertEquals(expResult, actResult);

        fail();
    }
}
