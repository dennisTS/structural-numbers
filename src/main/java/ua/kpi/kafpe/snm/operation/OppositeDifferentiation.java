package ua.kpi.kafpe.snm.operation;

import static com.google.common.base.Preconditions.checkNotNull;
import ua.kpi.kafpe.snm.StructuralNumber;
import ua.kpi.kafpe.snm.StructuralNumberColumn;

public class OppositeDifferentiation extends Differentiation {

	public OppositeDifferentiation(StructuralNumber number, Integer alpha) {
		checkNotNull(number);
		
		this.number = copyStructuralNumber(number);
		this.alpha = alpha;
	}
	
	@Override
	public StructuralNumber perform() {
		if (number.equals(StructuralNumber.NULL) || isSingleElementNumber())
			return StructuralNumber.NULL;
		
		StructuralNumber temp = newStructuralNumber();
		
		for (StructuralNumberColumn column : getColumnsCopyFromNumber(number)) {
			if (!checkColumnForAlpha(column)) {
				addColumnToNumber(column, temp);
			}
		}
		
		return temp;
	}

}
