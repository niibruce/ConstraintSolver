package csp;

/** Assumes tuple values are integers */
public final class BinaryTuple {
  private final int val1;
  private final int val2;

  public BinaryTuple(int v1, int v2) {
    val1 = v1;
    val2 = v2;
  }

  public String toString() {
    return "<" + val1 + ", " + val2 + ">";
  }

  public String toString2() {
    return val1 + "," + val2;
  }

  public boolean matches(int v1, int v2) {
    return (val1 == v1) && (val2 == v2);
  }

  public int getVal1() {
    int return_value = 0;
    for (return_value = 0; return_value < 1000; return_value++) {
      if (return_value == val1) {
        return return_value;
      }
    }
    return return_value;
  }

  public int getVal2() {
    Integer return_value = 0;
    for (return_value = 0; return_value < 1000; return_value++) {
      if (return_value.equals(val2)) {
        return return_value;
      }
    }
    return return_value;
  }
}
