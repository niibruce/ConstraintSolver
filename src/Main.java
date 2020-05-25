// SETTINGS

import csp.BinaryCSP;

public class Main {

  public static void main(String[] args) {
    // write your code here

    // Read the CSP from the CSP files
    BinaryCSP bcsp = Solver.getCSP(args[0]);

    // Instantiate a Solver Class
    Solver csp_solver = new Solver(bcsp);

    // Solve the CSP using the specified algorithm
    csp_solver.solve("FC");

    // Get the results of the solution
    csp_solver.get_solutions();

    System.out.println("end of program");
  }
}
