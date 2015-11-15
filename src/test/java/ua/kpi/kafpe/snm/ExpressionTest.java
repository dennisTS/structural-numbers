package ua.kpi.kafpe.snm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExpressionTest {

    private Expression expression;

    @Before
    public void setUp() {
        expression = new Expression();
    }

    @Test
    public void shouldInitializeExpression() {
        expression = new Expression("x1");

        String expResult = "x1";

        String actResult = expression.toString();

        assertEquals(expResult, actResult);
    }

    @Test
    public void shouldInitializeExpressionWithEmptyStringWhenPassedNull() {
        expression = new Expression(null);

        String expResult = "";

        String actResult = expression.toString();

        assertEquals(expResult, actResult);
    }

    @Test
    public void shouldInitializeExpressionWithEmptyStringAndDefaultConstructor() {
        expression = new Expression();

        String expResult = "";

        String actResult = expression.toString();

        assertEquals(expResult, actResult);
    }

    @Test
    public void shouldAddStringToExpressionWhenSumming() {
        expression = new Expression("x1");
        expression.add("x2");

        String expResult = "x1 + x2";

        String actResult = expression.toString();

        assertEquals(expResult, actResult);
    }

    @Test
    public void shouldNotSumWhenPassedEmptyString() {
        expression = new Expression("x1");
        expression.add("");

        String expResult = "x1";

        String actResult = expression.toString();

        assertEquals(expResult, actResult);
    }
}
