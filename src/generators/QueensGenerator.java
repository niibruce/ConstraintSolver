public final class QueensGenerator {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java QueensGenerator <n>");
      return;
    }
    int n = Integer.parseInt(args[0]);
    System.out.println("//" + n + "-Queens.");
    System.out.println("\n// Number of variables:\n" + n);
    System.out.println("\n// Domains of the variables: 0.. (inclusive)");
    for (int i = 0; i < n; i++) System.out.println("0, " + (n - 1));
    System.out.println("\n// constraints (vars indexed from 0, allowed tuples):");

    for (int row1 = 0; row1 < n - 1; row1++)
      for (int row2 = row1 + 1; row2 < n; row2++) {
        System.out.println("c(" + row1 + ", " + row2 + ")");
        for (int col1 = 0; col1 < n; col1++)
          for (int col2 = 0; col2 < n; col2++) {
            if ((col1 != col2) && (Math.abs(col1 - col2) != (row2 - row1))) {
              System.out.println(col1 + ", " + col2);
            }
          }
        System.out.println();
      }
  }
}
