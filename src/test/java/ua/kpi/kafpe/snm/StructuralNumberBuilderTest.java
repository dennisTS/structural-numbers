package ua.kpi.kafpe.snm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import ua.kpi.kafpe.snm.exception.DuplicateElementsException;
import ua.kpi.kafpe.snm.exception.SizeInconsistencyException;

public class StructuralNumberBuilderTest {
	public StructuralNumber number;
	public StructuralNumber number1;
	public StructuralNumber number2;
	public StructuralNumber number3;

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
	public void testNewInstanceIsNull() {
		number = new StructuralNumber.Builder().build();

		final boolean actResult = number.isNull();

		assertTrue(actResult);
	}

	@Test
	public void testAddColumnAsCollection() {
		number = new StructuralNumber.Builder()
					.addColumn(Arrays.asList(1, 2, 3, 4))
					.addColumn(Arrays.asList(2, 3, 1, 5))
					.addColumn(Arrays.asList(1, 2, 3, 5))
					.build();

		final String expResult = "[[1, 2, 3, 4]]";

		final String actResult = number.toString();

		assertEquals(expResult, actResult);
	}

	@Test
	public void testAddDifferentColumns() {
		number = new StructuralNumber.Builder()
					.addColumn(1, 2, 3, 4)
					.addColumn(2, 3, 1, 5)
					.build();

		final String expResult = "[[1, 2, 3, 4], [1, 2, 3, 5]]";

		final String actResult = number.toString();

		assertEquals(expResult, actResult);
	}

	@Test (expected = SizeInconsistencyException.class)
	public void testAddDifferentSizedColumns() {
		number = new StructuralNumber.Builder()
					.addColumn(1, 2, 3, 4)
					.addColumn(2, 3, 1)
					.build();
	}
	
	@Test
	public void testAddIdenticalColumn() {
		number = new StructuralNumber.Builder()
					.addColumn(1, 2, 3, 4)
					.addColumn(2, 3, 1, 5)
					.addColumn(1, 2, 3, 5)
					.build();

		final String expResult = "[[1, 2, 3, 4]]";

		final String actResult = number.toString();

		assertEquals(expResult, actResult);
	}

	@Test
	public void testAddEmptyColumns() {
		final StructuralNumber expResult = StructuralNumber.NULL;

		final StructuralNumber actResult = new StructuralNumber.Builder()
												.addColumn()
												.addColumn(Collections.emptySet())
												.build();

		assertEquals(expResult, actResult);
	}
	
	@Test(expected = SizeInconsistencyException.class)
	public void testAddColumnsWithDifferentSize() {
		number = new StructuralNumber.Builder()
					.addColumn(1, 2, 3, 4)
					.addColumn(1, 2, 3)
					.build();
	}

	@Test(expected = DuplicateElementsException.class)
	public void testAddColumnsWithIdenticalElements() {
		number = new StructuralNumber.Builder()
					.addColumn(1, 2, 3, 4)
					.addColumn(1, 2, 3, 3)
					.build();
	}

	@Test(expected = DuplicateElementsException.class)
	public void testAddColumnsWithDifferentElementsAndDifferentSize() {
		number = new StructuralNumber.Builder()
					.addColumn(1, 2, 3, 4)
					.addColumn(5, 2, 3, 3, 4)
					.build();
	}

	@Test(expected = DuplicateElementsException.class)
	public void testAddColumnsWithIdenticalElementsAndDifferentSize() {
		number = new StructuralNumber.Builder()
					.addColumn(1, 2, 3, 4)
					.addColumn(1, 2, 3, 3, 4)
					.build();
	}
}