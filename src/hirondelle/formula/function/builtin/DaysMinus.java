package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** 
daysminus(y,m,d,n): a date n days before a given date.
Accepts fractional days. 
*/
public final class DaysMinus implements Function {
  
  /**
  Takes 4 arguments, consisting of a year-month-day triplet, and the 
  number of days to subtract from it.
  The year-month-day triplet satisfies {@link Check#date(Decimal, Decimal, Decimal)}.

  <P>Example
  (with Decimals represented by their values, for convenience):
<pre>
//the day before 1957-10-04:
daysminus(1957,10,4,1) => 19571003
</pre>

   <p><b>The return value is an encoding of a date into a Decimal.</b>
   In the above example, the returned date is 
   <pre>1957-10-03 (October 3, 1957)</pre> 
   Any decimal portion in the return value reflects a fraction of a day. 
 */
  public Decimal calculate(Decimal... aArgs) {
    Check.numArgs(4, aArgs); 
    Decimal year = aArgs[0];
    Decimal month = aArgs[1];
    Decimal day = aArgs[2];
    Decimal increment = aArgs[3];
    Check.date(year, month, day);

    //same as DaysPlus, but negate the increment
    Function daysPlus = new DaysPlus();
    return daysPlus.calculate(year, month, day, increment.negate());
  }


}
