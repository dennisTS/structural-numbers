package ua.kpi.kafpe.snm;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.kpi.kafpe.snm.operation.Addition;
import ua.kpi.kafpe.snm.operation.AlgebraicDifferentiation;
import ua.kpi.kafpe.snm.operation.Multiplication;
import ua.kpi.kafpe.snm.operation.OppositeDifferentiation;

public class ShowcaseTests {

    @Test
    public void testAdd() {
        StructuralNumber number1 = new StructuralNumber.Builder()
                .addColumn(2, 6)
                .addColumn(3, 5)
                .addColumn(5, 7)
                .build();

        StructuralNumber number2 = new StructuralNumber.Builder()
                .addColumn(4, 2)
                .addColumn(2, 6)
                .addColumn(7, 5)
                .build();

        StructuralNumber number3 = new StructuralNumber.Builder()
                .addColumn(1, 2)
                .build();

        StructuralNumber expResult = new StructuralNumber.Builder()
                .addColumn(3, 5)
                .addColumn(4, 2)
                .addColumn(1, 2)
                .build();

        StructuralNumber actResult = new Addition(number2, number1, number3).perform();

        assertEquals(expResult, actResult);
    }

    @Test
    public void testMultiply() {
        StructuralNumber number1 = new StructuralNumber.Builder()
                .addColumn(1, 2)
                .addColumn(3, 5)
                .addColumn(4, 7)
                .build();

        StructuralNumber number2 = new StructuralNumber.Builder()
                .addColumn(2, 3)
                .addColumn(5, 4)
                .addColumn(6, 7)
                .build();

        StructuralNumber expResult = new StructuralNumber.Builder()
                .addColumn(4, 7, 2, 3)
                .addColumn(1, 2, 5, 4)
                .addColumn(1, 2, 6, 7)
                .addColumn(3, 5, 6, 7)
                .build();

        StructuralNumber actResult = new Multiplication(number2, number1).perform();

        assertEquals(expResult, actResult);
    }

    @Test
    public void testDifferentiateAlgebraic() {
        StructuralNumber number = new StructuralNumber.Builder()
                .addColumn(1, 2, 3)
                .addColumn(3, 4, 5)
                .addColumn(5, 6, 2)
                .build();

        StructuralNumber expResult = new StructuralNumber.Builder()
                .addColumn(1, 3)
                .addColumn(5, 6)
                .build();

        StructuralNumber actResult = new AlgebraicDifferentiation(number, 2).perform();

        assertEquals(expResult, actResult);
    }

    @Test
    public void testDifferentiateOpposite() {
        StructuralNumber number = new StructuralNumber.Builder()
                .addColumn(1, 2, 3)
                .addColumn(3, 5, 7)
                .addColumn(4, 2, 6)
                .build();

        StructuralNumber expResult = new StructuralNumber.Builder()
                .addColumn(2, 4, 6)
                .build();

        StructuralNumber actResult = new OppositeDifferentiation(number, 3).perform();

        assertEquals(expResult, actResult);
    }

    @Test
    public void testCase() {
        StructuralNumber number1 = new StructuralNumber.Builder()
                .addColumn(1)
                .addColumn(5)
                .addColumn(2)
                .build();

        StructuralNumber number2 = new StructuralNumber.Builder()
                .addColumn(2)
                .addColumn(6)
                .addColumn(3)
                .build();

        StructuralNumber number3 = new StructuralNumber.Builder()
                .addColumn(3)
                .addColumn(7)
                .addColumn(4)
                .build();

        StructuralNumber number4 = new StructuralNumber.Builder()
                .addColumn(1)
                .addColumn(4)
                .addColumn(8)
                .build();

        StructuralNumber circuit = new Multiplication(number1, number2, number3, number4).perform();

        StructuralNumber differentiationBy1 = new AlgebraicDifferentiation(circuit, 4).perform();

        System.out.println(differentiationBy1.toReadableString());

        for(;;) {
            testAdd();
            testDifferentiateAlgebraic();
            testDifferentiateOpposite();
            testMultiply();
        }
    }

}
