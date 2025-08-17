package rmi_calc;

import static org.junit.Assert.*;
import org.junit.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorSingleClientTest {

    private static Calculator calc;

    @BeforeClass
    public static void setUpClass() throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        calc = (Calculator) registry.lookup("CalculatorService");
        // Clean up stack if not empty
        while (!calc.isEmpty()) { try { calc.pop(); } catch(Exception e) {} }
    }

    @Test
    public void testIsEmptyOnNewStack() throws Exception {
        assertTrue(calc.isEmpty());
    }

    @Test
    public void testMinOperation() throws Exception {
        calc.pushValue(8);
        calc.pushValue(-1);
        calc.pushValue(12);
        calc.pushOperation("min");
        int result = calc.pop();
        assertEquals(-1, result);
    }

    @Test
    public void testMaxOperation() throws Exception {
        calc.pushValue(4);
        calc.pushValue(99);
        calc.pushValue(2);
        calc.pushOperation("max");
        int result = calc.pop();
        assertEquals(99, result);
    }

    @Test
    public void testGcdOperation() throws Exception {
        calc.pushValue(54);
        calc.pushValue(36);
        calc.pushOperation("gcd");
        int result = calc.pop();
        assertEquals(18, result);
    }

    @Test
    public void testLcmOperation() throws Exception {
        calc.pushValue(6);
        calc.pushValue(8);
        calc.pushOperation("lcm");
        int result = calc.pop();
        assertEquals(24, result);
    }

    @Test
    public void testDelayPop() throws Exception {
        calc.pushValue(77);
        long start = System.currentTimeMillis();
        int popped = calc.delayPop(1000);
        long elapsed = System.currentTimeMillis() - start;
        assertEquals(77, popped);
        assertTrue(elapsed >= 1000); // should delay at least 1 second
    }

    @Test(expected = java.rmi.RemoteException.class)
    public void testPopFromEmptyThrows() throws Exception {
        // Stack should be empty now; popping throws exception
        calc.pop();
    }
}


