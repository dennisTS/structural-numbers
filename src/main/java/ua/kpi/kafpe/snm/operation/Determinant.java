package ua.kpi.kafpe.snm.operation;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.commons.math3.complex.Complex;

import ua.kpi.kafpe.snm.Expression;
import ua.kpi.kafpe.snm.StructuralNumber;
import ua.kpi.kafpe.snm.StructuralNumber.StructuralNumberOperation;
import ua.kpi.kafpe.snm.StructuralNumberColumn;

public class Determinant extends StructuralNumberOperation {
	
	private final StructuralNumber number;

	private final Map<Integer, Complex> numberMap;

	private Expression expression;
	
	public Determinant(StructuralNumber number, Map<Integer, Complex> numberMap) {
		checkNotNull(number);
		checkNotNull(numberMap);
		
		this.number = copyStructuralNumber(number);
		this.numberMap = new HashMap<>(numberMap);

		expression = new Expression();
	}

	//TODO can be optimized by pre-compiling a function with separate delayed calculation
	public Complex calculate() {
		if (number == StructuralNumber.NULL)
			return Complex.NaN;
		
		Complex sum = Complex.ZERO;
		
		for (StructuralNumberColumn column : getColumnsCopyFromNumber(number)) {
			sum = sum.add(calculateColumnProduct(column));
		}

		return sum;
	}

	private Complex calculateColumnProduct(StructuralNumberColumn column) {
		Expression productExpression = new Expression();

		Complex product = Complex.ONE;
		
		for (Integer integer : column.getInnerColumnCopy()) {
			product = product.multiply(numberMap.get(integer));

			productExpression.multiply("x" + integer);
		}

		expression.addOrSubtract(productExpression, Expression.Sign.PLUS);
		return product;
	}

	public String toStringFormula() {
		return expression.toString();
	}
}
