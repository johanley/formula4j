package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Function;

import java.util.Arrays;

/** max(x1, x2, x3, ...): the maximum value in a set of numbers.  */
public final class Max implements Function {
  
  /**
   Return the maximum value found in the given parameters.
   
   <P>The number of parameters in this case (0..N) is not fixed.
   If no parameters are passed, then zero is returned.
   The parameters can be passed in any order. 
  */
  public Decimal calculate(Decimal... aArgs) {
    Decimal result = Decimal.ZERO;
    if (aArgs.length == 0){
      result = Decimal.ZERO;
    }
    else {
      Arrays.sort(aArgs); 
      result = aArgs[aArgs.length-1];
    }
    return result;
  }

}
