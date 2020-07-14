package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Function;

/** pstdev(x1, x2, x3, ...): the population standard deviation of a set of 2 or more numbers.   */
public final class PopulationStandardDeviation implements Function {

  /**
   Takes two or more arguments.
   The order of the arguments is arbitrary.
  */
  public Decimal calculate(Decimal... aArgs) {
    return stdDev(aArgs.length, aArgs);
  }

  Decimal stdDev(int aSize, Decimal... aArgs) {
    if(aArgs.length < 2){
      throw new WrongNumberOfArgs("Standard deviation needs to be calculated for at least 2 items, but you have passed this many: " + aArgs.length);
    }
    Function meanFunc = new ArithmeticMean();
    Decimal mean = meanFunc.calculate(aArgs);
    
    Decimal totalSquaredVariance = Decimal.ZERO;
    Decimal size = Decimal.from(aSize);
    for (Decimal number : aArgs){
      Decimal variance = number.minus(mean);
      totalSquaredVariance = totalSquaredVariance.plus(variance.pow(2));
    }
    
    Decimal meanSqrVariance = totalSquaredVariance.div(size);
    
    Function squareRoot = new SquareRoot();
    return squareRoot.calculate(meanSqrVariance);
  }

}
