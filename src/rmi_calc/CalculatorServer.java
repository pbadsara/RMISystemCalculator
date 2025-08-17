package rmi_calc;
//This is the server implementation of the RMI system, or the server side
//The RMI registry is also initiated here and binds with the object 
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            CalculatorImplementation calc = new CalculatorImplementation();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("CalculatorService", calc);
            System.out.println("Calculator server ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


