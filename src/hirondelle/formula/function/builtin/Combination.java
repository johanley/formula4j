package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Function;

/** 
 combination(n,k): the number of combinations, selecting k of n.
 Returns the number of <em>distinct</em> ways you can select 
 k items from an underlying set of n items.  
*/
public final class Combination implements Function {
  
  /** 
   Takes two parameters n and k (in that order). 
   Both are integers greater than 0, with n &gt;= k.
  */
 public Decimal calculate(Decimal... aArgs) {
    Function permutation = new Permutation();
    Decimal perms = permutation.calculate(aArgs);
    
    Function factorial = new Factorial();
    Decimal kFactorial = factorial.calculate(aArgs[1]);
    
    return perms.div(kFactorial);
  }

}
