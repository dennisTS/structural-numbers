package ua.kpi.kafpe.snm.operation;

import ua.kpi.kafpe.snm.StructuralNumber;
import ua.kpi.kafpe.snm.StructuralNumber.StructuralNumberOperation;
import ua.kpi.kafpe.snm.StructuralNumberColumn;

public abstract class Differentiation extends StructuralNumberOperation {

	protected StructuralNumber number;
	protected Integer alpha;

	protected boolean isSingleElementNumber() {
		return (number.getColumnsNumber() == 1 && number.getRowsNumber() == 1) ? true : false;
	}

	protected boolean checkColumnForAlpha(StructuralNumberColumn column) {
		return column.getInnerColumnCopy().contains(alpha);
	}

}