package hirondelle.formula.function.builtin;

import static java.lang.Math.*;
import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** bearing(&phi;1, &lambda;1, &phi;2, &lambda;2): the compass bearing from one place to another. */
public final class Bearing implements Function {
  
  /**
   The bearing is the direction one must travel from a specific starting point (point 1)
    to get to a specific destination (point 2).
   
   <P>Returns degrees from true north, positive towards the east, in the range 0..360.
   
   <P>This method takes 4 parameters, in the following order:
   <ul>
    <li>&phi;1 - the latitude of the starting point, in degrees
    <li>&lambda;1 - the longitude of the starting point, in degrees
    <li>&phi;2 - the latitude of the destination point, in degrees
    <li>&lambda;2 - the longitude of the destination point, in degrees
   </ul>
   
   <P>Latitude is positive north of the equator, and negative south of the equator.
   Longitude is positive to the east of Greenwich meridian (Asia, for example), and negative to the west of the 
   Greenwich meridian (America, for example).
   
    <P>The return value usually has a large number of decimal places. That precision is spurious  
    and without physical significance, since the underlying implementation makes the approximation that the 
    Earth is spherical.
    
    <P>The caller should round the result to a reasonable number of decimal places. 
    (Some applications may even decide to round the result to an integer.)
    
    <P>For reference, the crux of the formula is:
<pre>atan2(sin(&Delta;&lambda;) * cos(&phi;2), cos(&phi;1) * sin(&phi;2) - sin(&phi;1) * cos(&phi;2) * cos(&Delta;&lambda;)</pre>
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.numArgs(4, aArgs);
    double φ1 = radsFor(aArgs[0]);
    double λ1 = radsFor(aArgs[1]);
    double φ2 = radsFor(aArgs[2]);
    double λ2 = radsFor(aArgs[3]);
    double Δλ = λ2 - λ1;
  
    Double result = atan2(sin(Δλ) * cos(φ2), cos(φ1) * sin(φ2) - sin(φ1) * cos(φ2) * cos(Δλ) ); // -pi..+pi
    result = toDegrees(result); // -180..+180
    result = (result + 360.0) % 360.0; // 0..360
    return Decimal.from(result); //degrees from north  
  }
  
  private double radsFor(Decimal aAngle){
    return toRadians(aAngle.doubleValue());
  }

}