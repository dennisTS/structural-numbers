package ua.kpi.kafpe.snm.operation;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.commons.math3.complex.Complex;

import ua.kpi.kafpe.snm.StructuralNumber;
import ua.kpi.kafpe.snm.StructuralNumber.StructuralNumberOperation;
import ua.kpi.kafpe.snm.StructuralNumberColumn;

public class Determinant extends StructuralNumberOperation {
	
	private final StructuralNumber number;

	private final Map<Integer, Complex> numberMap;

	private StringBuilder stringFormula;
	
	public Determinant(StructuralNumber number, Map<Integer, Complex> numberMap) {
		checkNotNull(number);
		checkNotNull(numberMap);
		
		this.number = copyStructuralNumber(number);
		this.numberMap = new HashMap<>(numberMap);

		stringFormula = new StringBuilder();
	}

	//TODO can be optimized by pre-compiling a function with separate delayed calculation
	public Complex calculate() {
		if (number == StructuralNumber.NULL)
			return Complex.NaN;
		
		Complex sum = Complex.ZERO;
		
		for (StructuralNumberColumn column : getColumnsCopyFromNumber(number)) {
			sum = sum.add(calculateColumnProduct(column));

			stringFormula.append(" + ");
		}

		trimFormulaForSign("+");

		return sum;
	}

	private Complex calculateColumnProduct(StructuralNumberColumn column) {
		Complex product = Complex.ONE;
		
		for (Integer integer : column.getInnerColumnCopy()) {
			product = product.multiply(numberMap.get(integer));

			stringFormula.append("x" + integer + " * ");
		}

		trimFormulaForSign("*");

		return product;
	}

	private void trimFormulaForSign(String sign) {
		stringFormula.deleteCharAt(stringFormula.lastIndexOf(sign));
		stringFormula = new StringBuilder(stringFormula.toString().trim());
	}

	public String toStringFormula() {
		return stringFormula.toString();
	}
}
