package hirondelle.formula;

import java.util.Arrays;

/**
 A formula contains a function that's not defined.
 
 <P>All functions need to be either a built-in function, or a 
 custom function defined by the application.
*/
public final class UnknownFunctionException extends RuntimeException {
  
  public UnknownFunctionException(String... aFunctionNames) {
    super("Please supply a definition for each function(s) in this list: " + Arrays.asList(aFunctionNames));
  }
  
  private static final long serialVersionUID = 3068887032039270305L;

}
