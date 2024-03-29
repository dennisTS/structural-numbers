package ua.kpi.kafpe.snm.operation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ua.kpi.kafpe.snm.StructuralNumber;
import ua.kpi.kafpe.snm.exception.SizeInconsistencyException;
import ua.kpi.kafpe.snm.operation.Addition;
import ua.kpi.kafpe.snm.operation.Multiplication;

public class AdditionAndMultiplicationTest {
	private StructuralNumber number1, number2, number3;
	
	@Before
	public void init() {
		number1 = new StructuralNumber.Builder()
			.addColumn(2, 7)
			.addColumn(3, 5)
			.build();
		
		number2 = new StructuralNumber.Builder()
			.addColumn(3, 0)
			.addColumn(2, 7)
			.build();
		
		number3 = new StructuralNumber.Builder()
			.addColumn(3, 0, 1)
			.addColumn(2, 7, 3)
			.build();
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddNullArgument() {
		new Addition(number1, null).perform();
	}

	@Test
	public void testAddNull() {
		final StructuralNumber expResult = number1;
		
		final StructuralNumber actResult = new Addition(number1, StructuralNumber.NULL).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testAddToNull() {
		final StructuralNumber expResult = number1;
		
		final StructuralNumber actResult = new Addition(StructuralNumber.NULL, number1).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testAddItself() {
		final StructuralNumber expResult = StructuralNumber.NULL;
		
		final StructuralNumber actResult = new Addition(number1, number1).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testAddAnother() {
		final StructuralNumber expResult = new StructuralNumber.Builder()
												.addColumn(3, 0)
												.addColumn(3, 5)
												.build();
		
		final StructuralNumber actResult = new Addition(number1, number2).perform();
		
		assertEquals(expResult, actResult);
	}

	@Test (expected = SizeInconsistencyException.class)
	public void testAddAnotherOfDifferentSize() {
		new Addition(number1, number3).perform();
	}
	
	@Test (expected = NullPointerException.class)
	public void testMultiplyNullArgument() {
		new Multiplication(number1, null).perform();
	}

	@Test
	public void testMultiplyByNull() {
		final StructuralNumber expResult = number1;
		
		final StructuralNumber actResult = new Multiplication(number1, StructuralNumber.NULL).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testMultiplyNull() {
		final StructuralNumber expResult = number1;
		
		final StructuralNumber actResult = new Multiplication(StructuralNumber.NULL, number1).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testMultiplyItself() {
		final StructuralNumber expResult = StructuralNumber.NULL;
		
		final StructuralNumber actResult = new Multiplication(number1, number1).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testMultiplyAnother() {
		StructuralNumber bigNumber = new StructuralNumber.Builder()
										.addColumn(1, 2)
										.addColumn(3, 5)
										.addColumn(4, 7)
										.build();

		StructuralNumber biggerNumber = new StructuralNumber.Builder()
										.addColumn(2, 5, 1)
										.addColumn(3, 7, 4)
										.addColumn(7, 9, 6)
										.build();

		StructuralNumber expResult = new StructuralNumber.Builder()
										.addColumn(1, 2, 3, 7, 4)
										.addColumn(1, 2, 7, 9, 6)
										.addColumn(3, 5, 7, 9, 6)
										.addColumn(4, 7, 2, 5, 1)
										.build();
	
		final StructuralNumber actResult = new Multiplication(bigNumber, biggerNumber).perform();
		
		assertEquals(expResult, actResult);
	}

	@Test
	public void testMultiplyAnotherOfDifferentSize() {
		number1 = new StructuralNumber.Builder()
					.addColumn(1)
					.addColumn(2)
					.addColumn(3)
					.build();
	
		number2 = new StructuralNumber.Builder()
					.addColumn(2, 3)
					.build();
		
		final StructuralNumber expResult = new StructuralNumber.Builder()
					.addColumn(1, 2, 3)
					.build();

		final StructuralNumber actResult = new Multiplication(number1, number2).perform();

		assertEquals(expResult, actResult);
	}
}
