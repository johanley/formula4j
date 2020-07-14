package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** abs(x): absolute value of a number. */
public final class Abs implements Function {

  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    return aArgs[0].abs();
  }

}
