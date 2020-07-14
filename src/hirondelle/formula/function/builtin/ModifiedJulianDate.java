package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** 
mjd(year, month, day): the Modified Julian Date of a calendar date.

<P>The <a href='http://en.wikipedia.org/wiki/Julian_day'>Julian Date</a>
is defined by astronomers as a simple sequential date reckoning.
Its primary use is to make it simple to calculate the number of days 
between two dates in the Gregorian calendar.

 <P>Since the origin of the Julian Date is in the distant past, it takes on large values 
 for modern dates. The Modified Julian Date merely changes day 0, in order to 
 result in smaller values for modern dates.
*/
public final class ModifiedJulianDate implements Function {

  /**
   Takes 3 parameters for year, month, and day.
   
   <p>The day may contain a fraction.
   Uses {@link Check#date(Decimal, Decimal, Decimal)}.
  */
  public Decimal calculate(Decimal... aArgs) {
    Function jd = new JulianDate();
    Decimal result = jd.calculate(aArgs);
    return result.minus(2400000.5);
  }

}
