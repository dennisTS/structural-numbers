package ua.kpi.kafpe.snm.operation;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.kpi.kafpe.snm.StructuralNumber;

public class AlgebraicDifferentiationTest {

	@Test
	public void testPassNullStructuralNumber() {
		final StructuralNumber expResult = StructuralNumber.NULL;
		
		final StructuralNumber actResult = new AlgebraicDifferentiation(StructuralNumber.NULL, 0).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testSingleElementStructuralNumber() {
		final StructuralNumber number = new StructuralNumber.Builder()
										.addColumn(1)
										.build();
		
		final StructuralNumber expResult = StructuralNumber.NULL;
		
		final StructuralNumber actResult = new AlgebraicDifferentiation(number, 0).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testPerform() {
		final StructuralNumber number = new StructuralNumber.Builder()
										.addColumn(1, 2, 3)
										.addColumn(3, 4, 5)
										.addColumn(1, 3, 6)
										.build();

		final StructuralNumber expResult = new StructuralNumber.Builder()
										.addColumn(2, 3)
										.addColumn(3, 6)
										.build();

		final StructuralNumber actResult = new AlgebraicDifferentiation(number, 1).perform();

		assertEquals(expResult, actResult);
	}

}
