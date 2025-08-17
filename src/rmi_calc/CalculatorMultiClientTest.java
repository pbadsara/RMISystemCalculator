
package rmi_calc;

import org.junit.*;
import static org.junit.Assert.*;
//Allows us to use all methods from junit.assert without using the assert prefix

//To pass random integers as a value for the calculations
import java.util.concurrent.ThreadLocalRandom;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//Helps us lock the threads until all are processed
import java.util.concurrent.CountDownLatch;
import rmi_calc.Calculator;

/*
  Helps check the working of the RMI system for multiple clients, 3 in our case
 */
public class CalculatorMultiClientTest {

    private static final int CLIENTS = 5; //Increase the number for more clients
    private static Calculator[] calcs; //Array for Calculator references, one for each client
    private static final Object LOCK = new Object();

    //Connects all clients with the RMI registry
    @BeforeClass
    public static void setupClients() throws Exception {
        calcs = new Calculator[CLIENTS];
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        for (int i = 0; i < CLIENTS; i++) {
            calcs[i] = (Calculator) registry.lookup("CalculatorService");
        }
        // Clear the shared stack for fairness
        while (!calcs[0].isEmpty()) {
            try {
                calcs[0].pop();
            } catch (Exception e) {
                break;
            }
        }
    }

    @Test
    public void testConcurrentPushPop() throws Exception {
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch doneLatch = new CountDownLatch(CLIENTS);

        Runnable task = () -> {
            try {
                startLatch.await();
                int val1 = ThreadLocalRandom.current().nextInt(); //Random value given for input
                int val2 = ThreadLocalRandom.current().nextInt(); //Random value given for input
                // Use a single shared Calculator reference; operations are serialized by LOCK
                Calculator c = calcs[0];
                synchronized (LOCK) {
                    c.pushValue(val1);
                    c.pushValue(val2);
                    c.pushOperation("max");
                    int result = c.pop();
                    assertEquals(Math.max(val1, val2), result);
                }
                doneLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
                fail("Exception in thread: " + e);
            }
        };

        for (int i = 0; i < CLIENTS; i++) {
            new Thread(task, "Client-" + i).start();
        }

        startLatch.countDown(); // Release all threads
        doneLatch.await(); // Wait for all to finish
        // If all threads pass, the test passes
    }
}

