package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** cos(x): the cosine of an angle. */
public final class Cosine implements Function {
  
  /** The argument is in radians. */
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    double result = Math.cos(arg.doubleValue());
    return Decimal.from(result);
  }

}
