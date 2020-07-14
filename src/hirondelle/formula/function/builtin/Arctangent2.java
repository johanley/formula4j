package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** arctan2(y,x): arc-tangent of a number. */
public final class Arctangent2 implements Function {
  
  /** 
   Returns a value in the range -pi..+pi.
   
   <p>This function takes two arguments, y and x, corresponding to Cartesian coordinates.
   Note the order of the arguments (y comes first).
   <p>Converts the position to polar coordinates (r,&theta;), and returns the value of &theta;.  
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.twoArgs(aArgs);
    Decimal y = aArgs[0];
    Decimal x = aArgs[1];
    double result = Math.atan2(y.doubleValue(), x.doubleValue());
    return Decimal.from(result);
  }
  

}
