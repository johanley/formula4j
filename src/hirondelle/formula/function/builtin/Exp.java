package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** 
 exp(x): the exponential function; <em>e</em> raised to the given power.
 
<P>exp(x) is the same as E^x.
*/
public final class Exp implements Function {
  
  /**
   Return Euler's <em>e</em> number raised to the given power.
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    double result = Math.exp(arg.doubleValue());
    return Decimal.from(result);
  }

}
