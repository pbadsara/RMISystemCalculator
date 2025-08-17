package rmi_calc;
//This is the client side of the RMI system, where we are providing values and operators from the client
//These will be passed to the server object, and calculations will be performed accordingly
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculator calc = (Calculator) registry.lookup("CalculatorService");
            //Helps in finding and binding to the RMI registry and locating the object
            
            // Example test sequence, values can be modified:
            calc.pushValue(78);
            calc.pushValue(60);

            //Pushes whatever operator we want and helps the system generate the output accordingly, 
            calc.pushOperation("lcm");
            int result = calc.pop();
            System.out.println("The result :" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
