package ua.kpi.kafpe.snm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import ua.kpi.kafpe.snm.exception.DuplicateElementsException;

public class ColumnTest {

	private Set<Integer> testSet1, testSet2, testSet3;
	private StructuralNumberColumn column1, column2, column3;
	
	@Before
	public void init() {
		testSet1 = new TreeSet<Integer>(Arrays.<Integer>asList(1, 2, 3, 4));
		testSet2 = new TreeSet<Integer>(Arrays.<Integer>asList(1, 2, 3, 4));
		
		testSet3 = new TreeSet<Integer>(Arrays.<Integer>asList(5, 6, 7, 8));
		
		column1 = new StructuralNumberColumn(testSet1);
		column2 = new StructuralNumberColumn(testSet2);
		
		column3 = new StructuralNumberColumn(testSet3);
	}
	
	@Test
	public void testCopyConstructor() {
		StructuralNumberColumn expResult = column1;
		
		StructuralNumberColumn actResult = new StructuralNumberColumn(column1);
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testAddElementReturnsFalseWhenContainsElement() {
		boolean expResult = false;
		
		boolean actResult = column1.addElement(4);
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testAddElement() {
		boolean result = column1.addElement(5);
		
		String expResult = "[1, 2, 3, 4, 5]";
		
		String actResult = column1.toString();
		
		assertEquals(expResult, actResult);
		assertTrue(result);
	}
	
	@Test
	public void testAddAllElementsFromColumnWithCompleteRepetition() {
		boolean result = column1.addAllElementsFromColumn(column1);
		
		String expResult = "[1, 2, 3, 4]";
		
		String actResult = column1.toString();
		
		assertEquals(expResult, actResult);
		assertFalse(result);
	}
	
	@Test
	public void testAddAllElementsFromColumnWithoutRepetition() {
		boolean result = column1.addAllElementsFromColumn(column3);
		
		String expResult = "[1, 2, 3, 4, 5, 6, 7, 8]";
		
		String actResult = column1.toString();
		
		assertEquals(expResult, actResult);
		assertTrue(result);
	}
	
	@Test
	public void testHashCodeMultipleTimes() {
		Set<Integer> set = new HashSet<Integer>();
		
		for (int i = 0; i <= 10000; i++) {
			set.add(column1.hashCode());
		}
		
		int expResult = 1;
		
		int actResult = set.size();
		
		assertEquals(expResult, actResult);
	}
	
	@SuppressWarnings("unused")
	@Test (expected = DuplicateElementsException.class)
	public void testNewColumnAsCollectionWithDuplicateElements() {
		Collection<Integer> testCollection = new ArrayList<Integer>(Arrays.<Integer>asList(1, 2, 2, 7));
		StructuralNumberColumn column = new StructuralNumberColumn(testCollection);
	}
	
	@Test
	public void testEqualsWithDifferentObjects() {
		boolean expResult = true;
		
		boolean actResult = column1.equals(column2);
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testEqualsWithSameObjects() {
		boolean expResult = true;
		
		boolean actResult = column1.equals(column1);
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testEqualsWithDifferentType() {
		boolean expResult = false;
		
		boolean actResult = column1.equals("columnString");
		
		assertEquals(expResult, actResult);
	}

	@Test
	public void testEqualsWithDifferentValues() {
		boolean expResult = false;
		
		boolean actResult = column1.equals(column3);
		
		assertEquals(expResult, actResult);
	}
	
	@Test
	public void testToString() {
		String expResult = "[1, 2, 3, 4]";
		
		String actResult = column1.toString();

		assertEquals(expResult, actResult);
	}

}
