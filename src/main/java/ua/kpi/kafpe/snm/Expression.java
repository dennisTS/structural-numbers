package ua.kpi.kafpe.snm;

public class Expression {

    private StringBuilder stringExpression = new StringBuilder();

    public Expression() {

    }

    public Expression(String initialString) {
        stringExpression.append(initialString);
    }

    public Expression add(String addend) {
        stringExpression.append(" + ");
        stringExpression.append(addend);

        return this;
    }

    @Override
    public String toString() {
        return stringExpression.toString();
    }
}
