package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

import java.math.RoundingMode;

/** floor(x): round a number down to the nearest integer. */
public final class Floor implements Function {

  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    return arg.round(0, RoundingMode.FLOOR);
  }

}
