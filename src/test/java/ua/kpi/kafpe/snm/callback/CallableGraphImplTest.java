package ua.kpi.kafpe.snm.callback;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;

public class CallableGraphImplTest {
    MockCallableGraph callableGraph;

    @Before
    public void setUp() {
        callableGraph = new MockCallableGraph();
    }

    @Test
    public void test() {
        assertTrue(callableGraph.hasOrientationAgreement(new HashSet<>(Arrays.asList(1, 2, 3))));
    }
}
