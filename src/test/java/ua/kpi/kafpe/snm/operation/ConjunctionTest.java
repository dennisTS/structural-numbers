package ua.kpi.kafpe.snm.operation;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.kpi.kafpe.snm.StructuralNumber;

public class ConjunctionTest {

	@Test
	public void testPassNullStructuralNumber() {
		final StructuralNumber number = new StructuralNumber.Builder()
											.addColumn(1)
											.build();
		
		final StructuralNumber expResult = StructuralNumber.NULL;
		
		final StructuralNumber actResult = new Conjunction(StructuralNumber.NULL, number).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testPerform() {
		final StructuralNumber number1 = new StructuralNumber.Builder()
												.addColumn(1, 2)
												.addColumn(2, 3)
												.addColumn(3, 4)
												.build();

		final StructuralNumber number2 = new StructuralNumber.Builder()
												.addColumn(1, 2)
												.addColumn(2, 3)
												.addColumn(5, 4)
												.build();
		
		final StructuralNumber expResult = new StructuralNumber.Builder()
												.addColumn(1, 2)
												.addColumn(2, 3)
												.build();

		final StructuralNumber actResult = new Conjunction(number1, number2).perform();

		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testPerformWithNoCommonColumns() {
		final StructuralNumber number1 = new StructuralNumber.Builder()
												.addColumn(1, 2)
												.addColumn(2, 3)
												.addColumn(3, 4)
												.build();

		final StructuralNumber number2 = new StructuralNumber.Builder()
												.addColumn(5, 2)
												.addColumn(1, 3)
												.addColumn(5, 4)
												.build();
		
		final StructuralNumber expResult = StructuralNumber.NULL;

		final StructuralNumber actResult = new Conjunction(number1, number2).perform();

		assertEquals(expResult, actResult);
	}

}
