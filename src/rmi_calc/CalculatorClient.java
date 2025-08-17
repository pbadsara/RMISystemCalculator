package rmi_calc;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculator calc = (Calculator) registry.lookup("CalculatorService");
            // Example test sequence:
            calc.pushValue(72);
            calc.pushValue(64);
            calc.pushOperation("lcm");
            int result = calc.pop();
            System.out.println("The result :" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
