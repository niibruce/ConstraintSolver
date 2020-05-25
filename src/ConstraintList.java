import java.util.ArrayList;

public class ConstraintList extends ArrayList<Constraint> {

  public ConstraintList() {
    super();
  }

  /**
   * Add a constraint to the list of constraints
   *
   * @param c A new constraint to be added to the list of constraints
   * @return true/false if adding teh constrain was successful
   */
  @Override
  public boolean add(Constraint c) {
    boolean result;

    if (c != null) {
      result = super.add(c);
    } else {
      result = false;
    }
    return result;
  }

  /**
   * Find out if the integer i has supported values in the domain of the sv of the arc
   * @param i
   * @param a
   * @return
   */
  public ArrayList<Integer> getConstraintsOn(int i, Arc a) {
    ArrayList<Integer> supports = new ArrayList<>();

    for (Constraint c : this) {
      if (c.getFirstVariable().equals(a.getFv()) && c.getSecondVariable().equals(a.getSv())) {

        int[] fv_vals = c.getFv().get(a.getFv());
        int[] sv_vals = c.getSv().get(a.getSv());

        for (int counter = 0; counter < fv_vals.length; counter++) {
          if (fv_vals[counter] == i) {
            supports.add(sv_vals[counter]);
          }
        }
      } // endif
    } // endfor

    return supports;
  } // end method
}
