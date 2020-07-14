package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** 
daysplus(y,m,d,n): a date n days after a given date.
Accepts fractional days. 
*/
public final class DaysPlus implements Function {
  
  /**
  Takes 4 arguments, consisting of a year-month-day triplet, and the 
  number of days to add to it.
  The year-month-day triplet satisfies {@link Check#date(Decimal, Decimal, Decimal)}.

  <P>Example
  (with Decimals represented by their values, for convenience):
<pre>
//the day after 1957-10-04:
daysminus(1957,10,4,1) => 19571005
</pre>

   <p><b>The return value is an encoding of a date into a Decimal.</b>
   In the above example, the returned date is 
   <pre>1957-10-05 (October 5, 1957)</pre> 
   Any decimal portion in the return value reflects a fraction of a day. 
 */
  public Decimal calculate(Decimal... aArgs) {
    Check.numArgs(4, aArgs); 
    Decimal year = aArgs[0];
    Decimal month = aArgs[1];
    Decimal day = aArgs[2];
    Decimal increment = aArgs[3];
   
    Check.date(year, month, day);
    
    Function julianDate = new JulianDate();
    Decimal jd = julianDate.calculate(year, month, day);
    Decimal newJD = jd.plus(increment);
    
    Function dayFromJD = new DayFromJD();
    Decimal result = dayFromJD.calculate(newJD);
    return result;
  }

}
