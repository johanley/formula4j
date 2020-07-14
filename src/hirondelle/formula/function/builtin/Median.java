package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Function;

import java.util.Arrays;

/** 
 median(x1,x2,x3,...): the median of a set of numbers.
 <P>The median is often considered a better statistic than the mean, 
 since it's less sensitive to extreme values that may appear in the set of numbers. 
*/
public final class Median implements Function {
  
  /** 
  Returns the median of the given parameters.
  
  <P>The number of parameters in this case (0..N) is not fixed.
   If no parameters are passed, then zero is returned.
   The parameters can be passed in any order. 
  */
  public Decimal calculate(Decimal... aArgs) {
    Decimal result = null;
    if (aArgs.length == 0){
      result = Decimal.ZERO;
    }
    else if (aArgs.length == 1) {
      result = aArgs[0];
    }
    else {
      Arrays.sort(aArgs);
      if (aArgs.length % 2 == 0){
        // there are an even number of items in the list
        // take the arithmetic mean of the 2 items in the middle
        int a = (aArgs.length / 2) - 1; // integer division here is ok
        int b = a + 1;
        Decimal sum = aArgs[a].plus(aArgs[b]);
        result = sum.div(2);
      }
      else {
        // there are an odd number of items in the list
        // take the middle one
        int medianIdx = (aArgs.length  - 1) / 2; //integer division here is ok
        result = aArgs[medianIdx];
      }
    }
    return result;
  }

}
