package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** 
 jd(year, month, day): the Julian Date of a calendar date.
 
 <P>The <a href='http://en.wikipedia.org/wiki/Julian_day'>Julian Date</a>
 is defined by astronomers as a simple sequential date reckoning.
 Its primary use is to make it simple to calculate the number of days 
 between two dates in the Gregorian calendar.    
*/
public final class JulianDate implements Function {

  /**
   Takes 3 parameters for year, month, and day.
   The day may contain a fraction.
   Uses {@link Check#date(Decimal, Decimal, Decimal)}.
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.numArgs(3, aArgs); 
    Decimal year = aArgs[0];
    Decimal month = aArgs[1];
    Decimal day = aArgs[2];
    
    Check.date(year, month, day);
    
    if ( month.intValue() <= 2){
       year = year.minus(1);
       month = month.plus(12);
    }
    
    Decimal a = Decimal.from(year.div(100).intValue()); //an int 
    int b = 2 - a.intValue() + a.div(4).intValue(); //an int
    
    Decimal result = day.plus(b).minus(1524.5);
    
    Decimal c = Decimal.from(30.6001).times(month.plus(1));
    result = result.plus(c.intValue());
    
    Decimal d = Decimal.from(365.25).times(year.plus(4716));
    result = result.plus(d.intValue());
    
    return result;
  }

}
