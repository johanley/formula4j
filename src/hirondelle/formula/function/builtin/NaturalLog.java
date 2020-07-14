package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** ln(x): natural log of a positive number.   */
public final class NaturalLog implements Function {

  /** The single argument must be positive.   */
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    Check.positive(arg, "Argument");
    double result = Math.log(arg.doubleValue());
    return Decimal.from(result);
  }

}
