package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** arccos(x): arc-cosine of a number. */
public final class Arccosine implements Function {

  /** Returns a value in the range 0.0..pi. */
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    double result = Math.acos(arg.doubleValue());
    return Decimal.from(result);
  }

}
