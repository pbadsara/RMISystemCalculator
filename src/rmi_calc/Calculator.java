package rmi_calc;

// Calculator.java
import java.rmi.Remote;
import java.rmi.RemoteException; //Helps handle any exceptions that might assise during RMI functions/operation

public interface Calculator extends Remote {
    /**
     * The Below Method helps us push an integer value onto the calculator stack.
     * @param val — gives the integer to push into the stack.
     */
    void pushValue(int val) throws RemoteException;

    /**
     * Pushes an operation selected by the client ("min", "max", "lcm", "gcd") onto the stack.
     * Operates on all current values.
     * @param operator — stores and tells the operation.
     */
    void pushOperation(String operator) throws RemoteException;

    /**
     * Pops and returns the top value from the stack.
     * @return integer value popped.
     */
    int pop() throws RemoteException;

    /**
     * Returns true if the stack is empty, and false if it's not empty.
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


