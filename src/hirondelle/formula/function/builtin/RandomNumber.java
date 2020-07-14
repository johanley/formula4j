package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

import java.util.Random;

/** rand(x): a pseudo-random number in a certain range.  */
public final class RandomNumber implements Function {

  /**
   Takes either 0 or 2 arguments.
   
   <P>For 0 arguments, returns a random number &gt;= 0 and &lt; 1 
   <P>For 2 arguments, the first argument is the lower limit (inclusive) and the second argument 
   is the upper limit (inclusive) for the return value.
  */
  public Decimal calculate(Decimal... aArgs) {
    Decimal result = null;
    if (aArgs.length == 2){
      //integer in a certain range (inclusive)
      result = range(aArgs);
    }
    else if (aArgs.length == 0){
      // float in range 0 (inclusive)..1 (exclusive)
      double value = Math.random();
      result = Decimal.from(value);
    }
    else {
      throw new WrongNumberOfArgs("Number of arguments should be 0 or 2, but is actually: "  + aArgs.length);
    }
    return result;
  }
  
  // PRIVATE 
  
  private Decimal range(Decimal... aArgs){
    Check.integer(aArgs[0], "Low");
    Check.integer(aArgs[1], "High");
    int from = aArgs[0].intValue();
    int to = aArgs[1].intValue();
    if (from >= to ){
      throw new IllegalArgumentException("The first argument must be less than the second argument: (" + from + ", " + to + ")");
    }
    Random random = new Random();
    //get the range, casting to long to avoid overflow problems
    long range = (long)to - (long)from + 1;
    // compute a fraction of the range, 0 <= frac < range
    long fraction = (long)(range * random.nextDouble());
    int randomNumber =  (int)(fraction + from);        
    return Decimal.from(randomNumber);
  }

}
