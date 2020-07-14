package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** 
 pow(x,y): raise x to the power y.
 
  <P>pow(x,y) is the same as x^y.
*/
public final class Power implements Function {
  
  /**
   The first argument is the base, and the second argument is the exponent.
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.twoArgs(aArgs);
    Decimal base = aArgs[0];
    Decimal exponent = aArgs[1];
    return base.pow(exponent);
  }
}
