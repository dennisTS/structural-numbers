package ua.kpi.kafpe.snm.operation;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.complex.Complex;
import org.junit.Test;

import ua.kpi.kafpe.snm.StructuralNumber;

public class DeterminantTest {

	@Test (expected = UnsupportedOperationException.class)
	public void testPerformThrowsException() {
		new Determinant(StructuralNumber.NULL, new HashMap<Integer, Complex>()).perform();
	}

	@Test
	public void testCalculateWithNull() {
		final Complex expResult = Complex.NaN;
		
		final Complex actResult = new Determinant(StructuralNumber.NULL, new HashMap<Integer, Complex>()).calculate();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testCalculate() {
		final StructuralNumber number = new StructuralNumber.Builder()
											.addColumn(1, 2)
											.addColumn(9, 8)
											.build();
		final Map <Integer, Complex> numberMap = new HashMap<>();
		numberMap.put(1, new Complex(1D, 0D));
		numberMap.put(2, new Complex(2D, 1D));
		numberMap.put(9, new Complex(0D, -2D));
		numberMap.put(8, new Complex(4D, 8D));
		
		final Complex expResult = new Complex(18D, -7D);
		
		final Complex actResult = new Determinant(number, numberMap).calculate();
		
		assertEquals(expResult, actResult);
	}

}
