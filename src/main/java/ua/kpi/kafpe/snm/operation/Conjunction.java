package ua.kpi.kafpe.snm.operation;

import static com.google.common.base.Preconditions.checkNotNull;
import static ua.kpi.kafpe.snm.StructuralNumber.NULL;

import java.util.Set;

import ua.kpi.kafpe.snm.StructuralNumber;
import ua.kpi.kafpe.snm.StructuralNumber.StructuralNumberOperation;
import ua.kpi.kafpe.snm.StructuralNumberColumn;

public class Conjunction extends StructuralNumberOperation {

	private StructuralNumber number1;
	private StructuralNumber number2;
	
	public Conjunction(StructuralNumber number1, StructuralNumber number2) {
		checkNotNull(number1);
		checkNotNull(number2);
		
		this.number1 = copyStructuralNumber(number1);
		this.number2 = copyStructuralNumber(number2);
	}
	
	@Override
	public StructuralNumber perform() {
		if (number1.equals(NULL) || number2.equals(NULL))
			return NULL;
		
		StructuralNumber temp = newStructuralNumber();
		
		Set<StructuralNumberColumn> columnSet = getColumnsCopyFromNumber(number1);
		columnSet.retainAll(getColumnsCopyFromNumber(number2));
		
		addAllColumnsToNumber(columnSet, temp);
		
		return temp;
	}

}
