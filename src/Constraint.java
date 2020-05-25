import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Constraint {
  //The use of variable names fv and sv is consistent with the use of the same variable naming scheme
  //in the other java source file provided for this assignment.
  //FV: first variable.i.e. the variable on the left of an equation
  //SV: second variable.i.e the variable on the right of an equation

  private HashMap<Variable, int[]> fv;
  private HashMap<Variable, int[]> sv;

  public Constraint(HashMap<Variable, int[]> fv, HashMap<Variable, int[]> sv) {
    this.fv = new HashMap<>();
    this.fv = fv;
    this.sv = new HashMap<>();
    this.sv = sv;
  }

  public Map<Variable, int[]> getFv() {
    return fv;
  }

  public Map<Variable, int[]> getSv() {
    return sv;
  }

  public Variable getFirstVariable() {
    return fv.keySet().stream().collect(Collectors.toList()).get(0);
  }

  public Variable getSecondVariable() {
    return sv.keySet().stream().collect(Collectors.toList()).get(0);
  }
}
