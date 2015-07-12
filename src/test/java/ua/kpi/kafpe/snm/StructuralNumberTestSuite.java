package ua.kpi.kafpe.snm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.kpi.kafpe.snm.operation.AdditionAndMultiplicationTest;
import ua.kpi.kafpe.snm.operation.ComplementaryObtentionTest;
import ua.kpi.kafpe.snm.operation.DeterminantalTest;
import ua.kpi.kafpe.snm.operation.AlgebraicDifferentiationTest;

@RunWith(Suite.class)
@SuiteClasses({ ColumnTest.class, 
				StructuralNumberTest.class, 
				AdditionAndMultiplicationTest.class,
				ComplementaryObtentionTest.class,
				DeterminantalTest.class,
				AlgebraicDifferentiationTest.class,
				BuilderTest.class })
public class StructuralNumberTestSuite {

}
