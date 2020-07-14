package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;
import static hirondelle.formula.Decimal.E;
import static hirondelle.formula.Decimal.PI;

/**
 factorial(x): the factorial of an integer, in the range 0..300.
*/
public final class Factorial implements Function {

  /** 
   Takes a single argument, a non-negative integer in the range 0..300.
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs);
    Decimal arg = aArgs[0];
    Check.integer(arg, "Argument");
    Check.range(arg, "Argument", Decimal.ZERO, Decimal.from(300));

    Decimal result = Decimal.ONE;
    int value = arg.intValue(); //used for looping only, not calculating
    for(int idx = 1; idx <= value; ++idx){
      result = result.times(idx);
    }
    return result;
  }

  // this blows up because Math.sqrt() cannot take really large numbers
  @SuppressWarnings("unused")
  private Decimal stirlingsApproximation(Decimal n){
    Decimal a = Decimal.from(2).times(PI).times(n);
    Decimal b = n.div(E).pow(n);
    double result = Math.sqrt(a.times(b).doubleValue());
    return Decimal.from(result);
  }
}
