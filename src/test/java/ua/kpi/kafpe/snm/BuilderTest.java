package ua.kpi.kafpe.snm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import ua.kpi.kafpe.snm.exception.DuplicateElementsException;
import ua.kpi.kafpe.snm.exception.SizeInconsistencyException;

public class BuilderTest {
	private StructuralNumber number;

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
												.addColumn(Collections.<Integer>emptySet())
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