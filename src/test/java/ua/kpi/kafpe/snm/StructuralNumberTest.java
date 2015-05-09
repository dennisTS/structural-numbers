package ua.kpi.kafpe.snm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ua.kpi.kafpe.snm.operation.Addition;

public class StructuralNumberTest {

	private StructuralNumber number;
	
	private StructuralNumber number1, number2, number3;
	
	@Before
	public void init() {
		number1 = new StructuralNumber.Builder()
			.addColumn(2, 7)
			.addColumn(3, 5)
			.addColumn(4, 7)
			.build();
		
		number2 = new StructuralNumber.Builder()
			.addColumn(2, 7)
			.addColumn(3, 5)
			.addColumn(4, 7)
			.build();
		
		number3 = new StructuralNumber.Builder()
			.addColumn(2, 7, 1)
			.addColumn(3, 5, 2)
			.addColumn(4, 7, 3)
			.build();
	}
	

	
	@Test
	public void testCopyConstructor() {
		final StructuralNumber simpleNumber1 = new StructuralNumber.Builder()
													.addColumn(0, 1)
													.build();
		
		final StructuralNumber simpleNumber2 = new StructuralNumber.Builder()
													.addColumn(2, 3)
													.build();

		final StructuralNumber expResult = new StructuralNumber.Builder()
													.addColumn(0, 1)
													.addColumn(2, 3)
													.build();
		
		final StructuralNumber actResult = new Addition(simpleNumber1, simpleNumber2).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testGetColumnsNumber() {
		number = new StructuralNumber.Builder()
			.addColumn(1, 2, 3, 4)
			.addColumn(1, 2, 3, 5)
			.build();
		
		final int expResult = 2;
		
		final int actResult = number.getColumnsNumber();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testGetColumnsNumberWhenZero() {
		number = new StructuralNumber.Builder()
			.build();
		
		final int expResult = 0;
		
		final int actResult = number.getColumnsNumber();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testGetRowsNumber() {
		number = new StructuralNumber.Builder()
			.addColumn(1, 2, 3, 4)
			.addColumn(1, 2, 3, 5)
			.build();
		
		final int expResult = 4;
		
		final int actResult = number.getRowsNumber();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testGetRowsNumberWhenZero() {
		number = new StructuralNumber.Builder()
			.build();
		
		final int expResult = 0;
		
		final int actResult = number.getRowsNumber();
		
		assertEquals(expResult, actResult);
	}
	
	@Ignore
	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsWithItself() {
		final boolean expResult = true;
		
		final boolean actResult = number1.equals(number1);
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testEqualsWithIdenticalValues() {
		final boolean expResult = true;
		
		final boolean actResult = number1.equals(number2);
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testEqualsWithDifferentType() {
		final boolean expResult = false;
		
		final boolean actResult = number1.equals("numberString1");
		
		assertEquals(expResult, actResult);
	}

	@Test
	public void testEqualsWithDifferentSizes() {
		final boolean expResult = false;
		
		final boolean actResult = number1.equals(number3);
		
		assertEquals(expResult, actResult);
	}

	
	@Test
	public void testToString() {
		final String expResult = "[[2, 7]]";
		
		final String actResult = new StructuralNumber.Builder()
									.addColumn(2, 7)
									.build()
									.toString();
		
		assertEquals(expResult, actResult);
	}

}
