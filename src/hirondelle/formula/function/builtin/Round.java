package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** round(x): round to 0 or more decimals. */
public final class Round implements Function {

  /**
   Takes either 1 or 2 arguments. 
   If the second argument is present, then it must be an integer, 0..N. 
  */
  public Decimal calculate(Decimal... aArgs) {
    if ( aArgs.length > 2 ){
      throw new WrongNumberOfArgs("Expecting 1..2 arguments, but you have passed this many arguments: "  + aArgs.length);
    }
    Decimal number = aArgs[0];
    Decimal numDecimals = Decimal.ZERO; // default 
    if (aArgs.length == 2){
      numDecimals = aArgs[1];
      Check.nonNegative(numDecimals, "Number of decimals");
      Check.integer(numDecimals, "Number of decimals");
    }
    return number.round(numDecimals.intValue());
  }

}
