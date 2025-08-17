package rmi_calc;

// Calculator.java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    /**
     * Pushes an integer value onto the calculator stack.
     * @param val — the integer to push.
     */
    void pushValue(int val) throws RemoteException;

    /**
     * Pushes an operation ("min", "max", "lcm", "gcd") onto the stack.
     * Performs the operation on all current values.
     * @param operator — operation keyword.
     */
    void pushOperation(String operator) throws RemoteException;

    /**
     * Pops and returns the top value from the stack.
     * @return integer value popped.
     */
    int pop() throws RemoteException;

    /**
     * Returns true if the stack is empty, false otherwise.
     * @return boolean indicating if the stack is empty.
     */
    boolean isEmpty() throws RemoteException;

    /**
     * Delays by <millis> milliseconds before performing pop.
     * @param millis — delay in milliseconds.
     * @return integer value popped.
     */
    int delayPop(int millis) throws RemoteException;
}


