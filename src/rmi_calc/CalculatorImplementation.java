package rmi_calc;
//This is the part of the RMI system where actual work is stored
//Here, we tell the system what to do in what condition/operator/method
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Stack; //Used to implement the stack 

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {
    private final Stack<Integer> stack = new Stack<>();

    public CalculatorImplementation() throws RemoteException {}

    /** Pushes a value onto the stack */
    public synchronized void pushValue(int val) throws RemoteException {
        stack.push(val);
    }

    /** Performs the specified operation on the entire stack and pushes the result. */
    public synchronized void pushOperation(String operator) throws RemoteException {
        if (stack.isEmpty()) return;

        // Pop all values into an array to process
        int n = stack.size();
        int[] values = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            values[i] = stack.pop();
        }

        int result = values[0];

        if ("min".equals(operator)) {
            for (int i = 1; i < n; i++) {
                if (values[i] < result) result = values[i];
            }
        } else if ("max".equals(operator)) {
            for (int i = 1; i < n; i++) {
                if (values[i] > result) result = values[i];
            }
        } else if ("gcd".equals(operator)) {
            for (int i = 1; i < n; i++) {
                result = gcd(result, values[i]);
            }
        } else if ("lcm".equals(operator)) {
            for (int i = 1; i < n; i++) {
                result = lcm(result, values[i]);
            }
        } else {
            throw new IllegalArgumentException("Unknown operator: " + operator);
        }

        stack.push(result);
    }

    /** Pops the stack */
    public synchronized int pop() throws RemoteException {
        if (stack.isEmpty()) throw new RemoteException("Stack empty");
        return stack.pop();
    }

    /** Returns true if stack is empty, false otherwise */
    public synchronized boolean isEmpty() throws RemoteException {
        return stack.isEmpty();
    }

    /** Waits before popping */
    public synchronized int delayPop(int millis) throws RemoteException {
        try { Thread.sleep(millis); } catch (InterruptedException e) {}
        return pop();
    }

    /** Greatest Common Divisor/gcd */
    //GCD is the Greatest Common Divisor, that is, the biggest number that divides both values
    private int gcd(int a, int b) {
        // Manual absolute value
        a = (a < 0) ? -a : a;
        b = (b < 0) ? -b : b;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /** Least Common Multiple/lcm */
    // It is the smallest number that can be divided by both inputs without leaving a remainder
    private int lcm(int a, int b) {
        int absA = (a < 0) ? -a : a;
        int absB = (b < 0) ? -b : b;
        if (absA == 0 || absB == 0) return 0;
        return (absA / gcd(absA, absB)) * absB;
    }
}
