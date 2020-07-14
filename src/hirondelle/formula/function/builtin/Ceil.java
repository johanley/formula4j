package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

import java.math.RoundingMode;

/** ceil(x): round a number up to the nearest integer. */
public final class Ceil implements Function {

  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    return arg.round(0, RoundingMode.CEILING);
  }

}
