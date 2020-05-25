public final class LangfordsGenerator {

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Usage: java LangfordsGenerator <k> <n>");
      System.out.println("for <k> sets of <n> integers");
      return;
    }
    int k = Integer.parseInt(args[0]);
    int n = Integer.parseInt(args[1]);
    System.out.println("//Langford, k = " + k + " n = " + n);

    int seqLength = k * n;
    System.out.println("\n// Number of variables:\n" + seqLength);
    System.out.println("\n// Domains of the variables: 1.. (inclusive)");
    // Variables are organised in n blocks of k, representing pos of each of the n ints
    for (int i = 0; i < seqLength; i++) System.out.println("1, " + seqLength);

    System.out.println("\n// constraints (vars indexed from 0, allowed tuples):");

    // iterate over the n blocks of k positions
    for (int block = 1; block <= n; block++)
      // iterate over the variables within a block
      for (int i = 0; i < k; i++) {
        if (i < k - 1) {
          // constrain relative to neighbour
          System.out.println("c(" + ((block - 1) * k + i) + ", " + ((block - 1) * k + i + 1) + ")");
          // acceptable assignments position the occurrences block+1 apart
          for (int pos = 1; pos < seqLength; pos++)
            if (pos + block + 1 <= seqLength) System.out.println(pos + ", " + (pos + block + 1));
          System.out.println();
        }
        // constrain relative to future blocks to prevent same position being used twice
        // find start of next block
        for (int j = block * k; j < seqLength; j++) {
          System.out.println("c(" + ((block - 1) * k + i) + ", " + j + ")");
          for (int val1 = 1; val1 <= seqLength; val1++)
            for (int val2 = 1; val2 <= seqLength; val2++)
              if (val1 != val2) System.out.println(val1 + ", " + val2);
          System.out.println();
        }
      }
  }
}
