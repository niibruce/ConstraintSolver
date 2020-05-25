public class Arc {
  private final Variable fv; // first variable
  private final Variable sv; // second variable

  public Arc(Variable fv, Variable sv) {
    this.fv = fv;
    this.sv = sv;
  }

  /**
   * Returns a new reversed form of the arc with the first and second variables swapped
   *
   * @return A new arc representing the reversed form of the current Arc. For instance: if the
   *     current arc is (X1, X2), then reverse returns the arc (X2, X1)
   */
  public Arc reverse() {
    return new Arc(this.sv, this.fv);
  }

  public Variable getFv() {
    return this.fv;
  }

  public Variable getSv() {
    return this.sv;
  }

  /**
   * Two arcs are only the same if their respective variables have the same IDs
   *
   * @param a An arc
   * @return True if the argument is the same arc as the current arc object.
   */
  public boolean equals(Arc a) {
    return this.fv.equals(a.getFv()) && this.sv.equals(a.getSv());
  }
}
