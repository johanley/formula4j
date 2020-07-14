package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** log(x): log base 10 of a positive number.   */
public final class LogBase10 implements Function {

  /** The single argument must be positive.   */
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    Check.positive(arg, "Argument");
    double result = Math.log10(arg.doubleValue());
    return Decimal.from(result);
  }

}
