package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** 
daysuntil(y,m,d): the number of days until a given date.
Accepts fractional days. 
*/
public final class DaysUntil implements Function {
  
  /**
  Takes 3 arguments, consisting of a year-month-day triplet. 
  The year-month-day triplet satisfies {@link Check#date(Decimal, Decimal, Decimal)}.

  <P>Example
  (with Decimals represented by their values, for convenience):
<pre>
//number of days until 2013-01-31:
daysuntil(2013,1,31) => 4
</pre>

   <P>This function is unusual, since its return value depends on when you call it.
  */
  public Decimal calculate(Decimal... aArgs) {
    Function daysSince = new DaysSince();
    return daysSince.calculate(aArgs).times(-1);
  }

}
