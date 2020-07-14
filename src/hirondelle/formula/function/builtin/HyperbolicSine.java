package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** sinh(x): the hyperbolic sine of a number. */
public final class HyperbolicSine implements Function {

  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    double result = Math.sinh(arg.doubleValue());
    return Decimal.from(result);
  }

}
