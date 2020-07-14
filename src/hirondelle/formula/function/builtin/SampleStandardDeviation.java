package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Function;

/** sstdev(x1, x2, x3, ...): the sample standard deviation of a set of 2 or more numbers.   */
public final class SampleStandardDeviation implements Function {

  /**
   Takes two or more arguments.
   The order of the arguments is arbitrary.
  */
  public Decimal calculate(Decimal... aArgs) {
    PopulationStandardDeviation std = new PopulationStandardDeviation();
    return std.stdDev(aArgs.length - 1, aArgs);
  }

}
