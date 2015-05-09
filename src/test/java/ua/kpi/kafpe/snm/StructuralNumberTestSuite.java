package ua.kpi.kafpe.snm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.kpi.kafpe.snm.operation.AdditionAndMultiplicationTest;

@RunWith(Suite.class)
@SuiteClasses({ ColumnTest.class, 
				StructuralNumberTest.class, 
				AdditionAndMultiplicationTest.class,
				BuilderTest.class })
public class StructuralNumberTestSuite {

}
