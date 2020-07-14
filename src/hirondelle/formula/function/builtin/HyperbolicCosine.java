package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** cosh(x): the hyperbolic cosine of a number. */
public final class HyperbolicCosine implements Function {

  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    double result = Math.cosh(arg.doubleValue());
    return Decimal.from(result);
  }

}
