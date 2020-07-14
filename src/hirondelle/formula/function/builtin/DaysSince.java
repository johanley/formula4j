package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

import java.util.Calendar;

/** 
dayssince(y,m,d): the number of days since a given date.
Accepts fractional days. 
*/
public final class DaysSince implements Function {
  
/**
  Takes 3 arguments, consisting of a year-month-day triplet. 
  The year-month-day triplet satisfies {@link Check#date(Decimal, Decimal, Decimal)}.

  <P>Example
  (with Decimals represented by their values, for convenience):
<pre>
//number of days since 2013-01-01:
dayssince(2013,1,1) => 4
</pre>

    <P>This function is unusual, since its return value depends on when you call it.
 */
  public Decimal calculate(Decimal... aArgs) {
    Check.numArgs(3, aArgs); 
    Decimal year1 = aArgs[0];
    Decimal month1 = aArgs[1];
    Decimal day1 = aArgs[2];

    Check.date(year1, month1, day1);

    Calendar now = Calendar.getInstance();
    now.set(Calendar.MILLISECOND, 0);
    now.set(Calendar.SECOND, 0);
    now.set(Calendar.MINUTE, 0);
    now.set(Calendar.HOUR, 0);
    now.set(Calendar.HOUR_OF_DAY, 0);
    
    Decimal from = julianDate(year1, month1, day1);
    Decimal to = julianDate(
      Decimal.from(now.get(Calendar.YEAR)), 
      Decimal.from(now.get(Calendar.MONTH) + 1), // 0-based months!! 
      Decimal.from(now.get(Calendar.DAY_OF_MONTH))
    );
    
    return to.minus(from);
  }

  private Decimal julianDate(Decimal aYear, Decimal aMonth, Decimal aDay){
    Function mjd = new ModifiedJulianDate();
    return mjd.calculate(aYear, aMonth, aDay);
  }
  
}
