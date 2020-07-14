package hirondelle.formula.function.builtin;

import static java.lang.Math.*;
import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/**
 distance(&phi;1, &lambda;1, &phi;2, &lambda;2): the distance in kilometres from one place to another. 
*/
public final class Distance implements Function {

  /**
   Returns the distance between two points on the Earth's surface, in kilometers.
   
  <P>This method takes 4 parameters, in the following order:
  <ul>
   <li>&phi;1 - the latitude of point 1, in degrees
   <li>&lambda;1 - the longitude of point 1, in degrees
   <li>&phi;2 - the latitude of point 2, in degrees
   <li>&lambda;2 - the longitude point 2, in degrees
  </ul>
  
  <P>Latitude is positive north of the equator, and negative south of the equator.
  Longitude is positive to the east of Greenwich meridian (Asia, for example), and negative to the west of the 
  Greenwich meridian (America, for example).
  
   <P>The return value usually has a large number of decimal places. That precision is spurious  
   and without physical significance, since the underlying implementation makes the approximation that the 
   Earth is spherical.
   
   <P>The caller should round the result to a reasonable number of decimal places.
   
   <P>This implementation uses two underlying formulas - one appropriate for large distances, and one  
   appropriate for small distances.
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.numArgs(4, aArgs);
    double lat1 = radsFor(aArgs[0]);
    double long1 = radsFor(aArgs[1]);
    double lat2 = radsFor(aArgs[2]);
    double long2 = radsFor(aArgs[3]);
    
    double deltaLong = abs(long2 - long1);
    double deltaLat = abs(lat2 - lat1);
    
    Double result = 0.0;
    double value = sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(deltaLong);
    if (abs(1 - value) > 0.01) {
      //the long-distance formula can be used; the cosine value is not too close to 1
      result = acos(value);
    }
    else {
      //the distance is small, and needs a different formula, more accurate at small distances
      //careful with integer division!!
      value = sqrt(  pow(sin(deltaLat/2.0), 2) + cos(lat1)* cos(lat2)* pow(sin(deltaLong/2.0), 2) );
      result = 2 * asin(value);
    }
    result = result * 6371.001; // kilometres
    return Decimal.from(result);  
  }
  
  private double radsFor(Decimal aAngle){
    return toRadians(aAngle.doubleValue());
  }
}
