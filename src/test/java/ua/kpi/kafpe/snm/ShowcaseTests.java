package ua.kpi.kafpe.snm;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.kpi.kafpe.snm.operations.Addition;
import ua.kpi.kafpe.snm.operations.Multiplication;

public class ShowcaseTests {

	@Test
	public void testAdd() {
		StructuralNumber number1 = new StructuralNumber.Builder()
			.addColumn(2, 6)
			.addColumn(3, 5)
			.addColumn(5, 7)
			.build();
	
		StructuralNumber number2 = new StructuralNumber.Builder()
			.addColumn(4, 2)
			.addColumn(2, 6)
			.addColumn(7, 5)
			.build();
		
		StructuralNumber number3 = new StructuralNumber.Builder()
			.addColumn(1, 2)
			.build();
	
		StructuralNumber expResult = new StructuralNumber.Builder()
										.addColumn(3, 5)
										.addColumn(4, 2)
										.addColumn(1, 2)
										.build();
		
		StructuralNumber actResult = new Addition(number2, number1, number3).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testMultiply() {
		StructuralNumber number1 = new StructuralNumber.Builder()
			.addColumn(1, 2)
			.addColumn(3, 5)
			.addColumn(4, 7)
			.build();
	
		StructuralNumber number2 = new StructuralNumber.Builder()
			.addColumn(2, 3)
			.addColumn(5, 4)
			.addColumn(6, 7)
			.build();
	
		StructuralNumber expResult = new StructuralNumber.Builder()
										.addColumn(4, 7, 2, 3)
										.addColumn(1, 2, 5, 4)
										.addColumn(1, 2, 6, 7)
										.addColumn(3, 5, 6, 7)
										.build();
		
		StructuralNumber actResult = new Multiplication(number2, number1).perform();
		
		assertEquals(expResult, actResult);
	}

}
