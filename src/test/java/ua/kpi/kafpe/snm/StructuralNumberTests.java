package ua.kpi.kafpe.snm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ StructuralNumberColumnTest.class, 
				StructuralNumberTest.class, 
				StructuralNumberOperationsTest.class,
				StructuralNumberBuilderTest.class })
public class StructuralNumberTests {

}
