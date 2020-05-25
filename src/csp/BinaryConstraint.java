package csp;

import java.util.ArrayList;

public final class BinaryConstraint {
  private final int firstVar;
  private final int secondVar;
  private final ArrayList<BinaryTuple> tuples;

  public BinaryConstraint(int fv, int sv, ArrayList<BinaryTuple> t) {
    firstVar = fv;
    secondVar = sv;
    tuples = t;
  }

  public String toString() {
    StringBuffer result = new StringBuffer();
    result.append("c(" + firstVar + ", " + secondVar + ")\n");
    for (BinaryTuple bt : tuples) result.append(bt + "\n");
    return result.toString();
  }

  public int getFirstVar() {
    return firstVar;
  }

  public int getSecondVar() {
    return secondVar;
  }

  public ArrayList<BinaryTuple> getTuples() {
    return tuples;
  }

  // SUGGESTION: You will want to add methods here to reason about the constraint
}
