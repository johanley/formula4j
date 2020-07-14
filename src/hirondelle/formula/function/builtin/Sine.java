package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;
import java.lang.Math;

/** sin(x): the sine of an angle. */
public final class Sine implements Function {
  
  /** The argument is in radians. */
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    double result = Math.sin(arg.doubleValue());
    return Decimal.from(result);
  }

}
