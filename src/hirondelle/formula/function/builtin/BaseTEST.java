package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Function;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/** Simple tests for functions. Tests success, but not failure. */
class BaseTEST extends TestCase {

  static final String PI = "3.141592653589793";
  static final String HALF_PI = "1.5707963267948966";
  static final String QUARTER_PI = "0.7853981633974483";
  static final String TWO_PI = "6.283185307179586";
  static final String E = "2.7182818284590455";
  
  void testWith(Function aFunction, String aExpected, String... aValues){
    List<Decimal> args = new ArrayList<Decimal>();
    for(String value : aValues){
      args.add(Decimal.from(value));
    }
    Decimal actual = aFunction.calculate(args.toArray(new Decimal[0]));
    Decimal exp = Decimal.from(aExpected);
    if (! actual.equals(exp) ){
      fail("Expected: " + exp + " actual: " + actual);
    }
  }
  
  
  void testBetween(Function aFunction, String aLow, String aHigh, String... aValues){
    List<Decimal> args = new ArrayList<Decimal>();
    for(String value : aValues){
      args.add(Decimal.from(value));
    }
    Decimal actual = aFunction.calculate(args.toArray(new Decimal[0]));
    Decimal low = Decimal.from(aLow);
    Decimal high = Decimal.from(aHigh);
    if ( actual.lt(low) || actual.gt(high) ){
      fail("Expected to be in range (inclusive): " + low + ".." + high + ", but actual value is: " + actual);
    }
  }
  
  
  void testFailWith(Function aFunction, String... aValues){
    List<Decimal> args = new ArrayList<Decimal>();
    for(String value : aValues){
      args.add(Decimal.from(value));
    }
    boolean completedNormally = false;
    try {
      @SuppressWarnings("unused")
      Decimal actual = aFunction.calculate(args.toArray(new Decimal[0]));
      completedNormally = true;
    }
    catch(Throwable ex){
      //do nothing - this is expected 
    }
    if(completedNormally){
      fail("Expected to fail, with args: " + args);
    }
  }

  
}
