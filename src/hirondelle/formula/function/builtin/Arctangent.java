package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** arctan(x): arc-tangent of a number. */
public final class Arctangent implements Function {

  /** Returns a value in the range -pi/2..+pi/2. */
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    double result = Math.atan(arg.doubleValue());
    return Decimal.from(result);
  }

}
