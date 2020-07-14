package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** tanh(x): the hyperbolic tangent of a number. */
public final class HyperbolicTangent implements Function {
  
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    double result = Math.tanh(arg.doubleValue());
    return Decimal.from(result);
  }


}
