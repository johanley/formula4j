package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

import java.util.Random;

/** randg(mean, variance): a pseudo-random number with a gaussian distribution.   */
public final class RandomGaussian implements Function {

  /**
    Takes two arguments, representing the mean and the variance, respectively,
    of the gaussian distribution.
   */
  public Decimal calculate(Decimal... aArgs) {
    Check.twoArgs(aArgs);
    double mean = aArgs[0].doubleValue();
    double variance = aArgs[1].doubleValue();
    Random random = new Random();
    double result = mean + random.nextGaussian() * variance;
    return Decimal.from(result);
  }

}
