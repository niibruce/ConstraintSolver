import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// An assigned variable has been assigned by a
// left branch.
// A variable with one value remaining is not an
// assigned variable.

public class Variable {

  private int id = -1;
  private int depth;
  private int[] domain = null;

  // ToDo: this may need to be implemented as a stack of integer arrays to enable each variable to
  // undo pruning
  private LinkedList<ArrayList<Integer>> marked = new LinkedList<ArrayList<Integer>>();
  private LinkedList<Integer> pruned = new LinkedList<>();

  private ArrayList<Integer> mostRecentlyPruned = new ArrayList<Integer>();
  private ArrayList<Integer> mostRecentlyMarked = new ArrayList<Integer>();
  private int value = -1;
  private int[] previously_assigned;

  // ToDo: We might also need to keep a list of previously assigned values

  public Variable(int id) {
    this.id = id;
    this.depth = 0;
  }

  public Variable() {}

  /**
   * Add an integer value to the domain of the variable
   *
   * @param
   */
  // public void addToDomain(Integer a) {
  // domain.add(Integer.valueOf(a));
  // }

  /** @param dom an array of ints */
  public void addDomain(int[] dom) {
    this.domain = dom;
  }

  /**
   * Removes a value from the domain of a variable and puts it into the list of most recently pruned
   * values
   *
   * @param a
   */
  public void prune(int a) {
    this.domain = ArrayUtils.removeElement(this.domain, a);
    //mostRecentlyPruned.add(a);
    this.pruned.add(a);
  }

  /**
   * Pushes the list of most recently pruned values onto the pruned array and clears the most
   * recently pruned array
   */
  public void savePrune() {
    // only prune if something has been added to the list of most recently pruned

    if (mostRecentlyPruned.size() > 0) {

      //actually remove the elements
      //for(int i: mostRecentlyPruned){
        //this.domain = ArrayUtils.removeElement(this.domain, i);
      //}

      pruned.add(mostRecentlyPruned.get(0));
      mostRecentlyPruned = new ArrayList<Integer>();
    }

  }

  /**
   * Get the current value which has been assigned to this variable
   *
   * @return
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Set the value of this variable
   *
   * @param value
   */
  public void assign(int value) {
    this.value = value;

    //add the value to the list of previously assigned values
    previously_assigned = ArrayUtils.add(previously_assigned, value);
    //marked = null;
  }

  public int[] getDomain() {
    return this.domain;
  }

  /**
   * Add a single integer to the domain of the variable
   * @param val
   */
  public void addIntToDomain(int val){
    this.domain = ArrayUtils.add(this.domain, val);

  }

  /**
   * duplicate method as addDomain above. Remove
   *
   * @param domain
   */
  public void setDomain(int[] domain) {
    this.domain = domain;
  }

  public int getId() {
    return id;
  }

  public Variable getCopy() {
    Variable new_var = new Variable(this.id);
    new_var.setDomain(this.getDomain());
    // new_var.setPruned(this.getPruned());
    return new_var;
  }

  public List<Integer> getPruned() {
    return pruned;
  }

  public void setPruned(LinkedList<Integer> pruned) {
    this.pruned = pruned;
  }

  /**
   * Reverse pruning by popping the most recently added integer from queue of pruned values back
   * into the list of valid values for this variable
   */
  public void undoPruning() {
    int popped = this.pruned.remove(pruned.size()-1);
    this.domain = ArrayUtils.add(this.domain, popped);
    //undo the most recent marking
    //undoMarking();
  }

  public void undoMarking(){
    marked.removeLast();
  }

  /** Unassigns a variable by setting its value to -1; */
  public void unassign() {
    this.value = -1;
  }

  /**
   * Deletes a value completely from the domain
   * @param value
   */
  public void delete(int value){
    this.domain = ArrayUtils.removeElement(this.domain, value);
  }

  /**
   * If the domain(list of possible values that the variable can assume) is empty, wipeout has
   * occured
   *
   * @return a boolean indicating whether wipeout has occurred.
   */
  public boolean isWipeout() {
    //Wipeout occurs when there is no number from the domain can be found in pruned or marked
    for(int i: domain){
      if(!pruned.contains(i) && !isMarked(i)){
        return false;
      }
    }

    return true;
  }

  /**
   * Gets the minimum value in the domain
   *
   * @return
   */
  public int getMinValInDomain() {
    int lowest = 1000000;

    for (int v : domain) {
      if(    v < lowest
              && !ArrayUtils.contains(this.previously_assigned, v)
              && !this.isMarked(v)  ) {
        lowest = v;
      }
    }
    return lowest;
  }

  /**
   * Gets the maximum value in the domain, ensuring to select values that haven't been previously assigned
   *
   *
   * @return
   */
  public int getMaxValInDomain() {
    int max = -1;

    for (int v : domain) {
      if (    v > max
              && !ArrayUtils.contains(this.previously_assigned, v)
              && !this.isMarked(v)  ) {

        max = v;
      }
    }
    return max;
  }

  /**
   * Checks if the domain is empty
   *
   * @return
   */
  public boolean isDomainEmpty() {
    return ArrayUtils.isEmpty(this.domain);
  }

  /**
   * Tells us if the variable has been assigned a value
   *
   * @return
   */
  public boolean isAssigned() {
    return value >= 0;
  }

  /**
   * Two variables are the same if they have the same id
   *
   * @param v
   * @return
   */
  public boolean equals(Variable v) {

    return this.id == v.getId();
  }

  /**
   * Returns true if the integer value from the domain of Variable v can be found in the domain of
   * the current variable
   *
   * @param value an integer value
   * @return True if the value has support in the domain
   */
  public boolean hasSupport(int value, Variable v) {
    return ArrayUtils.contains(this.domain, value);
  }

  /**
   * Returns true if the integer value can be found in the domain of the current variable
   *
   * @param value an integer value
   * @return True if the value has support in the domain
   */
  public boolean hasSupport(int value) {
    return ArrayUtils.contains(this.domain, value);
  }

  public boolean hasSupport(ArrayList<Integer> supports) {
    int intersection_count = 0;

    for (int s : supports) {
      if (ArrayUtils.contains(this.domain, s) && !isMarked(s)) {
      //if (ArrayUtils.contains(this.domain, s)) {
        intersection_count += 1;
      }
    }

    return intersection_count >= 1;
  }

  public int getDepth() {
    return depth;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }

  public ArrayList<Integer> getMostRecentlyPruned() {
    return this.mostRecentlyPruned;
  }

  /**
   * Removes the unsupported values from the domain of the variable
   * @param supported_values
   */
  public void markUnsupportedValues(ArrayList<Integer> supported_values){
    //ArrayList<Integer> m = new ArrayList<>();
  for(int k=0; k< this.domain.length; k++){
    if(!supported_values.contains(this.domain[k]) && !isMarked(this.domain[k])){
     mostRecentlyMarked.add(this.domain[k]);
    }
  }

  //marked.add(mostRecentlyMarked);
  //mostRecentlyMarked.clear();
    //this.savePrune();
  }

  public void saveMark(){
    if(mostRecentlyMarked.size() > 0){
      marked.add(mostRecentlyMarked);
      mostRecentlyMarked = new ArrayList<Integer>();
    }

  }

  /**
   * Check if an integer value is marked
   * @param a
   * @return
   */
  public boolean isMarked(int a){
    for(ArrayList<Integer> l: marked){
      if(l.contains(a)){
        return true;
      }
    }

    return false;
  }

  public int[] getInDomainAndUnmarked(){
    int[] inDomainUnmarked = null;

    for(int i: domain){
      if(!isMarked(i)){
        inDomainUnmarked = ArrayUtils.add(inDomainUnmarked, i);
      }
    }

    return inDomainUnmarked;

  }

}


