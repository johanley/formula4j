package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** 
 invest(principal, interest-rate, years, times-per-year): compound interest on an investment.   
*/
public final class Investment implements Function  {
  
  /**
   Returns the final value of a principal amount invested at a given interest rate,
   and over a given number of years. The result may have more than two decimal places.
   
   <p>There are 4 arguments to this method (in this order):
   <ul>
    <li>principal, &gt;= 0
    <li>interest rate, &gt;= 0
    <li>number of years, integer &gt;= 0
    <li>number of times per year the interest is paid, integer &gt;= 0
   </ul> 
   
   <P>Example:
   <ul>
    <li>principal = 100
    <li>interest rate = 0.05 (that is, 5%)
    <li>number of years = 2
    <li>number of times per year the interest is paid = 2
   </ul>
    <P>Then
<pre>invest(100, 0.05, 2, 1) = 110.25</pre>
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.numArgs(4, aArgs); // principal (>=0), annual interest rate (>=0), num years (int), num times per year (int)
    Decimal principal = aArgs[0];
    Decimal interest = aArgs[1];
    Decimal years = aArgs[2];
    Decimal timesPerYear = aArgs[3];
    
    Check.nonNegative(principal, "Principal");
    Check.nonNegative(interest, "Annual interest rate");
    Check.nonNegative(years, "Number of years");
    Check.integer(years, "Number of years");
    Check.nonNegative(timesPerYear, "Number of times compounded per year");
    Check.integer(timesPerYear, "Number of times compounded per year");
    
    Decimal result = Decimal.ONE.plus(interest.div(timesPerYear));
    result = result.pow(timesPerYear.times(years).intValue());
    result = result.times(principal);
    return result;
  }

}
