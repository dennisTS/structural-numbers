package ua.kpi.kafpe.snm;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.Queues;

import ua.kpi.kafpe.snm.StructuralNumber.StructuralNumberOperation;

public class Multiplication extends StructuralNumberOperation{

	private StructuralNumber source;
	private StructuralNumber destination;
	
	private StructuralNumber result;
	
	private Queue<StructuralNumber> factors;
	
	public Multiplication(StructuralNumber... factors) {
		this.factors = Queues.newArrayDeque();
		this.factors.addAll(Arrays.asList(factors));
	}
	
	@Override
	public StructuralNumber perform() {
		StructuralNumber temp = newStructuralNumber();
		
		for (StructuralNumber factor : factors) {
			temp = multiplyTwoNumbers(temp, factor);
		}
		
		return temp;
	}
	
	private StructuralNumber multiplyTwoNumbers(StructuralNumber firstFactor, StructuralNumber secondFactor) {
		setFields(copyStructuralNumber(firstFactor), copyStructuralNumber(secondFactor), newStructuralNumber());
		
		Iterator<StructuralNumberColumn> sourceIterator = getColumnsFromNumber(source).iterator();
		
		//TODO do-while
		while (sourceIterator.hasNext()) {
			StructuralNumberColumn sourceColumn = sourceIterator.next();
			
			result.addAllColumns(getColumnUnionsForSource(sourceColumn));
		}

		//setFields(null, null, null);
		
		return result;
	}
	
	private void setFields(StructuralNumber source, StructuralNumber destination, StructuralNumber result) {
		this.source = source;
		this.destination = destination;
		this.result = result;
	}

	Set<StructuralNumberColumn> getColumnUnionsForSource(StructuralNumberColumn sourceColumn) {
		Set<StructuralNumberColumn> singleSourceUnion = new HashSet<StructuralNumberColumn>();
		
		Iterator<StructuralNumberColumn> destinationIterator = getColumnsFromNumber(destination).iterator();
		
		while (destinationIterator.hasNext()) {
			StructuralNumberColumn destinationColumn = new StructuralNumberColumn(destinationIterator.next());
			
			if (destinationColumn.addAllElementsFromColumn(sourceColumn)) {
				singleSourceUnion.add(destinationColumn);
			}
		}
		
		return singleSourceUnion;
	}
	
}