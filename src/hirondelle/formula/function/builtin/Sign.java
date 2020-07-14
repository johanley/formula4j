package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** sign(x): the sign of a number.   */
public final class Sign implements Function {
  
  /** Returns 0, -1, or +1. */
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal result = Decimal.ZERO;
    Decimal num = aArgs[0];
    if ( num.lt(0) ){
      result = Decimal.MINUS_ONE;
    }
    else if (num.gt(0)){
      result = Decimal.ONE;
    }
    return result;
  }
  
}
