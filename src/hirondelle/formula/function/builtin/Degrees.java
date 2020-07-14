package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** deg(x): convert radians to degrees. */
public final class Degrees implements Function {

  /**
   Convert a single argument, an angle measured in radians, into the 
   same angle measured in degrees.  
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    double result = Math.toDegrees(arg.doubleValue());
    return Decimal.from(result);
  }

}
