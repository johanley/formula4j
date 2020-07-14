package hirondelle.formula;

import java.util.Arrays;

/**
 A formula contains a variable that hasn't been assigned a value.
*/
public final class UnpopulatedVariableException  extends RuntimeException {
  
  public UnpopulatedVariableException(String... aVariableNames){
    super("Please supply a value for each of the variable(s) in this list: " + Arrays.asList(aVariableNames));
  }

  private static final long serialVersionUID = 8039658142620976654L;

}
