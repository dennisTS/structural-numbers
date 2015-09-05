package ua.kpi.kafpe.snm.operation;


import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.Queues;

import static com.google.common.base.Preconditions.*;
import ua.kpi.kafpe.snm.StructuralNumber;
import ua.kpi.kafpe.snm.StructuralNumberColumn;
import ua.kpi.kafpe.snm.StructuralNumber.StructuralNumberOperation;

public class Multiplication extends StructuralNumberOperation{

	private StructuralNumber source;
	private StructuralNumber destination;
	
	private StructuralNumber result;
	
	private Queue<StructuralNumber> factors;
	
	public Multiplication(StructuralNumber... factors) {
		checkNotNull(factors);
		
		this.factors = Queues.newArrayDeque();
		this.factors = copyStructuralNumbers(factors);
	}
	
	@Override
	public StructuralNumber perform() {
		if (factors == null)
			throw new UnsupportedOperationException("The same operation can`t be performed twice");
		
		StructuralNumber temp = newStructuralNumber();
		
		for (StructuralNumber factor : factors) {
			temp = multiplyTwoNumbers(temp, factor);
		}
		
		factors = null;
		
		return temp;
	}
	
	private StructuralNumber multiplyTwoNumbers(StructuralNumber firstFactor, StructuralNumber secondFactor) {
		checkNotNull(firstFactor);
		checkNotNull(secondFactor);
		
		if (firstFactor.isNull())
			return secondFactor;
		else if (secondFactor.isNull())
			return firstFactor;
		
		if (firstFactor.equals(secondFactor))
			return StructuralNumber.NULL;
		
		setFields(copyStructuralNumber(firstFactor), copyStructuralNumber(secondFactor), newStructuralNumber());

		for (StructuralNumberColumn sourceColumn : getColumnsCopyFromNumber(source)) {
			addAllColumnsToNumber(getColumnUnionsForSource(sourceColumn), result);
		}

		//setFields(null, null, null);
		
		return result;
	}
	
	private void setFields(StructuralNumber source, StructuralNumber destination, StructuralNumber result) {
		this.source = source;
		this.destination = destination;
		this.result = result;
	}

	private Set<StructuralNumberColumn> getColumnUnionsForSource(StructuralNumberColumn sourceColumn) {
		Set<StructuralNumberColumn> singleSourceUnion = new HashSet<>();

		for (StructuralNumberColumn column : getColumnsCopyFromNumber(destination)) {
			StructuralNumberColumn destinationColumn = new StructuralNumberColumn(column);

			if (destinationColumn.addAllElementsFromColumn(sourceColumn)) {
				singleSourceUnion.add(destinationColumn);
			}
		}
		
		return singleSourceUnion;
	}
	
}