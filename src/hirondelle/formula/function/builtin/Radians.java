package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** rad(x): convert degrees to radians. */
public final class Radians implements Function {

  /**
   Convert a single argument, an angle measured in degrees, into the 
   same angle measured in radians.  
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    double result = Math.toRadians(arg.doubleValue());
    return Decimal.from(result);
  }

}
