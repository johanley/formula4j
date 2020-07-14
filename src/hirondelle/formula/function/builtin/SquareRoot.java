package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** sqrt(x): the square root of a number.   */
public final class SquareRoot implements Function {

  /** The single argument must be &gt;= 0.   */
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    Check.nonNegative(arg, "Argument");
    double result = Math.sqrt(arg.doubleValue());
    return Decimal.from(result);
  }

}
