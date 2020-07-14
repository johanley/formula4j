package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/**
 permutation(n,k): the number of permutations, selecting k of n.
 Returns the number of ways you can select k items from an underlying set of n items.  
*/
public final class Permutation implements Function {

  /** 
   Takes two parameters n and k (in that order). 
   Both are integers greater than 0, with n &gt;= k.
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.twoArgs(aArgs);
    Decimal nValue = aArgs[0];
    Decimal kValue = aArgs[1];
    
    Check.positiveInteger(nValue, "n-value");
    Check.positiveInteger(kValue, "k-value");
    
    if ( nValue.lt(kValue) ){
      throw new IllegalArgumentException("First argument is less than the second argument: " + nValue + ", " + kValue);
    }

    Decimal result = Decimal.ONE;
    int n = nValue.intValue(); //these are used only to control the loop - not to calculate
    int k = kValue.intValue();
    for(int idx = n; idx > (n - k); --idx){
      result = result.times(idx);
    }
    return result;
  }

}
