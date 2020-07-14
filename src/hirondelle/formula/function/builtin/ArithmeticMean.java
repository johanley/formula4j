package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Function;

import java.util.Arrays;

/** avg(x1,x2,x3,...): the arithmetic mean of a set of numbers. */
public final class ArithmeticMean implements Function {

  /** 
   Returns the average (arithmetic mean) of the given parameters.
   
   <P>The number of parameters in this case (0..N) is not fixed.
   If no parameters are passed, then zero is returned.
   The parameters can be passed in any order. 
  */
  public Decimal calculate(Decimal... aArgs) {
    Decimal result = Decimal.ZERO;
    if (aArgs.length == 0){
      result = Decimal.ZERO;
    }
    else if (aArgs.length == 1) {
      result = aArgs[0];
    }
    else {
      Decimal total =  Decimal.sum(Arrays.asList(aArgs));
      result = total.div(aArgs.length);
    }
    return result;
  }

}
