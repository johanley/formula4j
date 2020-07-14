package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Function;

/** sum(x1, x2, x3, ...): the sum of a set of numbers.   */
public final class Sum implements Function {
  
  /** 
  Returns the sum of the given parameters.
  
  <P>The number of parameters in this case (0..N) is not fixed.
  If no parameters are passed, then zero is returned.
  The parameters can be passed in any order. 
 */
  public Decimal calculate(Decimal... aArgs) {
    Decimal result = Decimal.ZERO;
    for (Decimal num : aArgs){
      result = result.plus(num);
    }
    return result;
  }

}
