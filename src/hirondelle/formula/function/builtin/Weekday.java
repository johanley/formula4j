package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** weekday(year, month, day): the day of the week for the given calendar day.   */
public final class Weekday implements Function {

  /**
   Returns 1 for Sunday, 2 for Monday, and so on.
   
  <P>Takes 3 parameters for year-month-day (in that order).
   The day may contain a fraction.
   Uses {@link Check#date(Decimal, Decimal, Decimal)}.
  */
  public Decimal calculate(Decimal... aArgs) {
    Function mjd = new ModifiedJulianDate();
    Decimal result = mjd.calculate(aArgs);
    result = result.plus(2400000.5); //the jd at midnight
    result = result.plus(1.5); // an int
    int idx = (result.intValue() % 7) + 1;
    return Decimal.from(idx);
  }
}
