package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** 
 daysbetween(y1,m1,d1, y2,m2,d2): the number of days between two calendar dates.
 Accepts fractional days. 
*/
public final class DaysBetween implements Function {
  
  /**
   Takes 6 arguments, consisting of a pair of year-month-day triplets.
   Each triplet satisfies {@link Check#date(Decimal, Decimal, Decimal)}.

   <P>Example 
   (with Decimals represented by their values, for convenience):
<pre>daysbetween(1991, 7, 11, 2018, 11, 26) => 10,000</pre>
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.numArgs(6, aArgs); 
    Decimal year1 = aArgs[0];
    Decimal month1 = aArgs[1];
    Decimal day1 = aArgs[2];

    Decimal year2 = aArgs[3];
    Decimal month2 = aArgs[4];
    Decimal day2 = aArgs[5];

    Check.date(year1, month1, day1);
    Check.date(year2, month2, day2);
    
    Decimal from = julianDate(year1, month1, day1);
    Decimal to = julianDate(year2, month2, day2);
    
    return to.minus(from);
  }
  
  private Decimal julianDate(Decimal aYear, Decimal aMonth, Decimal aDay){
    Function mjd = new ModifiedJulianDate();
    return mjd.calculate(aYear, aMonth, aDay);
  }

}
