package ua.kpi.kafpe.snm.operation;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashSet;
import java.util.Set;

import ua.kpi.kafpe.snm.StructuralNumber;
import ua.kpi.kafpe.snm.StructuralNumberColumn;
import ua.kpi.kafpe.snm.StructuralNumber.StructuralNumberOperation;

public class ComplementaryObtention extends StructuralNumberOperation {

	private StructuralNumber number;
	private StructuralNumber complementary;
	
	private Set<Integer> elementSet;
	
	public ComplementaryObtention(StructuralNumber number) {
		checkNotNull(number);
		
		this.number = copyStructuralNumber(number);
		
		complementary = newStructuralNumber();
	}
	
	@Override
	public StructuralNumber perform() {
		if (number.equals(StructuralNumber.NULL))
			return StructuralNumber.NULL;
		
		StructuralNumber tempComplementary = complementary;
		
		fillElementSetForNumber();
		
		fillComplementary();
		
		clean();
		
		return tempComplementary;
	}

	private void clean() {
		number = null;
		complementary = null;
		elementSet = null;
	}

	private void fillElementSetForNumber() {
		elementSet = new HashSet<Integer>();
		
		for (StructuralNumberColumn column : getColumnsCopyFromNumber(number)) {
			elementSet.addAll(column.getInnerColumnCopy());
		}
	}

	private void fillComplementary() {
		for (StructuralNumberColumn column : getColumnsCopyFromNumber(number)) {
			addColumnToNumber(getComplementaryColumn(column), complementary);
		}
	}

	private StructuralNumberColumn getComplementaryColumn(StructuralNumberColumn column) {
		Set<Integer> tempSet = new HashSet<Integer>(elementSet);
		tempSet.removeAll(column.getInnerColumnCopy());
		
		return new StructuralNumberColumn(tempSet);
	}
}
