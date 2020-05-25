public final class SudokuGenerator {

  /** The constraint is always the same != on 1..9 Only the */
  private static void diseqTuples() {
    for (int val1 = 1; val1 <= 9; val1++)
      for (int val2 = 1; val2 <= 9; val2++)
        if (val1 != val2) System.out.println(val1 + ", " + val2);
  }

  public static void main(String[] args) {
    if (args.length != 0) {
      System.out.println("Usage: java SudokuGenerator");
      return;
    }
    System.out.println("//Sudoku.");
    System.out.println("\n// Always 81 variables:\n" + 81);
    System.out.println("\n// Domains of the variables: 1..9 (inclusive)");
    System.out.println("\n// Edit the following to provide clues");
    for (int i = 0; i < 81; i++) System.out.println("1, 9");
    System.out.println("\n// constraints (vars indexed from 0, allowed tuples):");

    // Rows
    for (int row = 1; row <= 9; row++) {
      System.out.println("//Row: " + row);
      for (int col1 = 1; col1 <= 8; col1++)
        for (int col2 = col1 + 1; col2 <= 9; col2++) {
          System.out.println(
              "c(" + ((row - 1) * 9 + col1 - 1) + ", " + ((row - 1) * 9 + col2 - 1) + ")");
          diseqTuples();
          System.out.println();
        }
    }

    // Cols
    for (int col = 1; col <= 9; col++) {
      System.out.println("//Col: " + col);
      for (int row1 = 1; row1 <= 8; row1++)
        for (int row2 = row1 + 1; row2 <= 9; row2++) {
          System.out.println(
              "c(" + ((row1 - 1) * 9 + col - 1) + ", " + ((row2 - 1) * 9 + col - 1) + ")");
          diseqTuples();
          System.out.println();
        }
    }

    // 3 x 3 subsquares

    for (int subRow = 1; subRow <= 7; subRow += 3)
      for (int subCol = 1; subCol <= 7; subCol += 3) {
        System.out.println("//Subsquare starting at row: " + subRow + ", col: " + subCol);
        for (int row1 = subRow; row1 <= subRow + 2; row1++)
          for (int col1 = subCol; col1 <= subCol + 2; col1++)
            for (int row2 = row1; row2 <= subRow + 2; row2++)
              for (int col2 = subCol; col2 <= subCol + 2; col2++) {
                // break symmetry: only allow cell1 != cell2 where cell1 is less than cell2 in
                //   the row-wise ordering of the subsquare
                if ((row2 > row1) || (col2 > col1)) {
                  System.out.println(
                      "c("
                          + ((row1 - 1) * 9 + col1 - 1)
                          + ", "
                          + ((row2 - 1) * 9 + col2 - 1)
                          + ")");
                  diseqTuples();
                  System.out.println();
                }
              }
      }
  }
}
