import java.util.LinkedList;

/**
 * Custom class to represent a collection of Arcs and to simplify the execution of
 * operation on collections of arcs. This is a subclass of the LinkedList class, and
 * only contains Objects of type Arc.
 */
public class ArcList extends LinkedList<Arc> {

  public ArcList() {
    super();
  }

  /**
   * Add an arc to the list of arcs
   *
   * @param a A new arc to be added to the list
   * @return True if adding the new Arc was successful
   */
  @Override
  public boolean add(Arc a) {
    if (a != null) {
      return super.add(a);
    } else return false;
  }

  /**
   * Create a new arc from the arguments fv and sv, both of type Variable, and add to the
   * list of arcs.
   * @param fv The first variable, or the variable on the left of an equation
   * @param sv The second variable, or the variable on the right of an equation
   */
  public void makeAndAddArc(Variable fv, Variable sv) {
    Arc new_arc = new Arc(fv, sv);
    this.add(new_arc);
  }

  /**
   * Takes each element(an arc) from another list of arcs al
   * and inserts into the current list.
   * @param al The argument, of type ArcList
   */
  public void add(ArcList al) {
    for (Arc arc : al) {
      this.add(arc);
    }
  }
}
