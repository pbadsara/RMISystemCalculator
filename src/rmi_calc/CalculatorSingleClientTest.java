package rmi_calc;

import static org.junit.Assert.*; //Allows us to use all methods from junit.assert without using the assert prefix
import org.junit.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*
  Here we are testing our Calculator RMI system using a single client that interacts with the server
  We have tried to cover all stack operations and operators specified in the assignment and verify correctness.
 */

public class CalculatorSingleClientTest {

    //Calculator reference for invoking all the remote methods from the remote system
    private static Calculator calc;

    //This segment runs once before tests
    //Helps connect to the RMI registry
    @BeforeClass
    public static void setUpClass() throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        calc = (Calculator) registry.lookup("CalculatorService");
        // Clean up stack if not empty
        while (!calc.isEmpty()) { try { calc.pop(); } catch(Exception e) {} }
    }

    //Checks that the stack is empty
    @Test
    public void testIsEmptyOnNewStack() throws Exception {
        assertTrue(calc.isEmpty());
    }

    //The given test checks the working of the 'min' operator
    //Here we are passing three values to the stack and then getting the minimum number as a result
    @Test
    public void testMinOperation() throws Exception {
        calc.pushValue(8);
        calc.pushValue(-1);
        calc.pushValue(12);
        calc.pushOperation("min");
        int result = calc.pop();
        assertEquals(-1, result);//Here the value on the left is given by us, if it matches the result test succeeds
    }

    //The given test checks the working of the 'max' operator
    //Here we are passing three values to the stack and then getting the maximum number as a result
    @Test
    public void testMaxOperation() throws Exception {
        calc.pushValue(4);
        calc.pushValue(99);
        calc.pushValue(2);
        calc.pushOperation("max");
        int result = calc.pop();
        assertEquals(99, result);
    }

    //The below test is being used to check the working of the 'gcd' operator
    @Test
    public void testGcdOperation() throws Exception {
        calc.pushValue(54);
        calc.pushValue(36);
        calc.pushOperation("gcd");
        int result = calc.pop();
        assertEquals(18, result);
    }

    //Below testing helps in verifying the proper working of the 'lcm' operator
    @Test
    public void testLcmOperation() throws Exception {
        calc.pushValue(6);
        calc.pushValue(8);
        calc.pushOperation("lcm");
        int result = calc.pop();
        assertEquals(24, result);
    }

    //Below test verifies that delayPop correctly waits the specified time and pops the top value.
    @Test
    public void testDelayPop() throws Exception {
        calc.pushValue(77);
        long start = System.currentTimeMillis(); //Records time before the delay
        int popped = calc.delayPop(1000); //Requests pop after waiting the specified time, here 1000ms
        long elapsed = System.currentTimeMillis() - start; //here we measure the elapsed time
        assertEquals(77, popped);
        assertTrue(elapsed >= 1000); // should delay at least 1 second
    }

    @Test(expected = java.rmi.RemoteException.class)
    public void testPopFromEmptyThrows() throws Exception {
        // Stack should be empty now; popping throws exception
        calc.pop();
    }
}


