package ua.kpi.kafpe.snm.operation;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.kpi.kafpe.snm.StructuralNumber;

public class OppositeDifferentiationTest {

	@Test
	public void testPassNullStructuralNumber() {
		StructuralNumber expResult = StructuralNumber.NULL;
		
		StructuralNumber actResult = new OppositeDifferentiation(StructuralNumber.NULL, 0).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testSingleElementStructuralNumber() {
		StructuralNumber number = new StructuralNumber.Builder()
										.addColumn(1)
										.build();
		
		StructuralNumber expResult = StructuralNumber.NULL;
		
		StructuralNumber actResult = new OppositeDifferentiation(number, 0).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testPerform() {
		StructuralNumber number = new StructuralNumber.Builder()
										.addColumn(1, 2, 3)
										.addColumn(3, 4, 5)
										.addColumn(1, 3, 6)
										.build();

		StructuralNumber expResult = new StructuralNumber.Builder()
										.addColumn(3, 4, 5)
										.build();

		StructuralNumber actResult = new OppositeDifferentiation(number, 1).perform();

		assertEquals(expResult, actResult);
	}

}
