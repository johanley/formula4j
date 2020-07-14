package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** 
  dayfromjd(jd): convert a Julian Date into a calendar date. 
  Accepts fractional days. 
 */
public final class DayFromJD implements Function {

  /**
   The single argument is a Julian Date. 
   For example, this call returns a day in 1957 
   (with Decimals represented by their values, for convenience):
<pre>
dayfromjd(2436116.31) => 19571004.81
</pre>

   <p><b>The return value is an encoding of a date into a Decimal.</b>
   In the above example, the returned date is 
   <pre>1957-01-04.81 (January 4, 1957 19h 26m)</pre> 
   where the decimal portion reflects a fraction of a day. 
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.numArgs(1, aArgs); 
    Decimal jd = aArgs[0].plus(Decimal.from("0.5"));
    
    Decimal z = Decimal.from(jd.intValue());
    Decimal f = jd.minus(z);
    Decimal a = z;
    if (! z.lt(2299161)){
      Decimal alpha = z.minus(1867216.25).div(36524.25);
      alpha = Decimal.from(alpha.intValue());
      Decimal beta = Decimal.from(alpha.div(4).intValue());
      a = z.plus(1).plus(alpha).minus(beta);
    }
    
    Decimal b = a.plus(1524);
    Decimal c = Decimal.from( b.minus(122.1).div(365.25).intValue()  );
    Decimal d = Decimal.from( c.times(365.25).intValue() );
    Decimal e = Decimal.from( b.minus(d).div(30.6001).intValue() );
    Decimal eprime = Decimal.from( e.times(30.6001).intValue() );
    
    Decimal day = b.minus(d).minus(eprime).plus(f);
    Decimal month = null;
    if ( e.lt(14) ){
      month = e.minus(1);
    }
    else {
      month = e.minus(13);
    }
    Decimal year = null;
    if (month.gt(2)){
      year = c.minus(4716);
    }
    else {
      year = c.minus(4715);
    }
    
    String encodedResult = year.toString() + pad(month) + pad(day);
    return Decimal.from(encodedResult);
  }
  
  private String pad(Decimal aNumber){
    return aNumber.lt(10) ? "0" + aNumber.toString() : aNumber.toString();
  }

}
