package ua.kpi.kafpe.snm.operations;

import java.util.Arrays;
import java.util.Queue;

import com.google.common.collect.Queues;

import static com.google.common.base.Preconditions.*;
import ua.kpi.kafpe.snm.StructuralNumber;
import ua.kpi.kafpe.snm.StructuralNumber.StructuralNumberOperation;
import ua.kpi.kafpe.snm.exception.SizeInconsistencyException;

public class Addition extends StructuralNumberOperation {
	
	private Queue<StructuralNumber> addends;

	public Addition(StructuralNumber... addends) {
		this.addends = Queues.newArrayDeque();
		this.addends.addAll(Arrays.asList(addends));
	}
	
	@Override
	public StructuralNumber perform() {
		if (addends == null)
			throw new UnsupportedOperationException("The same operation can`t be performed twice");
		
		StructuralNumber temp = newStructuralNumber();
		
		for (StructuralNumber addend : addends) {
			temp = addTwoNumbers(temp, addend);
		}
		
		addends = null;
		
		return temp;
	}

	private StructuralNumber addTwoNumbers(StructuralNumber firstAddend, StructuralNumber secondAddend) {
		checkNotNull(firstAddend);
		checkNotNull(secondAddend);

		if (secondAddend.isNull())
			return firstAddend;
		else if (firstAddend.isNull())
			return secondAddend;
		
		if (firstAddend.equals(secondAddend))
			return StructuralNumber.NULL;
		
		if (firstAddend.getRowsNumber() != secondAddend.getRowsNumber())
			throw new SizeInconsistencyException();
		
		StructuralNumber copyDestination = copyStructuralNumber(firstAddend);
		StructuralNumber copySource = copyStructuralNumber(secondAddend);
		
		addAllColumnsToNumber(getColumnsFromNumber(copySource), copyDestination);
		
		return copyDestination;
	}

}
