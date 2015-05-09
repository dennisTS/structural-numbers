package ua.kpi.kafpe.snm.operation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ua.kpi.kafpe.snm.StructuralNumber;

public class ComplementaryObtentionTest {

	private StructuralNumber number;
	
	@Before
	public void init() {
		number = new StructuralNumber.Builder()
						.addColumn(1, 2, 3)
						.addColumn(4, 5, 3)
						.build();
	}
	
	@Test (expected = NullPointerException.class)
	public void testPassNull() {
		new ComplementaryObtention(null).perform();
	}
	
	@Test
	public void testPassNullStructuralNumber() {
		StructuralNumber expResult = StructuralNumber.NULL;
		
		StructuralNumber actResult = new ComplementaryObtention(StructuralNumber.NULL).perform();
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testPerform() {
		StructuralNumber expResult = new StructuralNumber.Builder()
										.addColumn(4, 5)
										.addColumn(1, 2)
										.build();
		
		StructuralNumber actResult = new ComplementaryObtention(number).perform();
		
		assertEquals(expResult, actResult);
	}

}
