package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** cbrt(x): cube root of a number. */
public final class CubeRoot implements Function {

  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    double result = Math.cbrt(arg.doubleValue());
    return Decimal.from(result);
  }

}
