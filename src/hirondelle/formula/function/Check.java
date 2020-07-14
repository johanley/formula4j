package hirondelle.formula.function;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.builtin.WrongNumberOfArgs;

/**
 Utility class for validating the parameters passed to a {@link Function}.
  If a problem is found, all methods in this class will throw a RuntimeException.
 
 <P>Some methods in this class take a String <i>name</i> argument. This is simply used 
 to provide a more informative message, in case of an error. 
 
 <P>The built-in functions use this class to validate their arguments. 
 You are encouraged to do the same, but you are not obliged to use this class. 
 You may validate the arguments passed to your own custom functions in any way that's best for you. 
*/
public final class Check {
  
  /** Verify that the number of arguments passed to the function matches an expected value. */
  public static void numArgs(int aExpected, Decimal... aAllArgs){
    if (aAllArgs.length != aExpected){
      throw new WrongNumberOfArgs(aExpected, aAllArgs.length);
    }
  }
  
  /** Verify that the function has been passed exactly 1 argument. */
  public static void oneArg(Decimal... aAllArgs){
    numArgs(1, aAllArgs);
  }

  /** Verify that the function has been passed exactly 2 arguments. */
  public static void twoArgs(Decimal... aAllArgs){
    numArgs(2, aAllArgs);
  }

  /** 
   Verify that the given value has no fractional value (has no decimals).
   For this method, both 2 and 2.00 (for example) are taken to be integers. 
  */
  public static void integer(Decimal aArg, String aName){
    if (! aArg.isInteger() ){
      throw new IllegalArgumentException(aName + " is not an integer: " + aArg);
    }
  }

  /** Verify that the given value is greater than 0. */
  public static void positive(Decimal aArg, String aName){
    if (aArg.lteq(Decimal.ZERO)){
      throw new IllegalArgumentException(aName + " is not a positive number: " + aArg);
    }
  }
  
  /** Verify that the given value is less than 0. */
  public static void negative(Decimal aArg, String aName){
    if (aArg.gteq(Decimal.ZERO)){
      throw new IllegalArgumentException(aName + " is not a negative number: " + aArg);
    }
  }

  /** Verify that the given value is in a given range (inclusive). */
  public static void range(Decimal aArg, String aName, Decimal aLow, Decimal aHigh){
    if (aArg.lt(aLow) || aArg.gt(aHigh) ){
      throw new IllegalArgumentException(aName + " is not in the range " + aLow + ".." + aHigh + ": " + aArg);
    }
  }
  
  /** Verify that the given value is greater than or equal to 0. */
  public static void nonNegative(Decimal aArg, String aName){
    if (aArg.lt(Decimal.ZERO)){
      throw new IllegalArgumentException(aName + " cannot be less than 0: " + aArg);
    }
  }
  
  /** Verify that the given value is less than or equal to 0. */
  public static void nonPositive(Decimal aArg, String aName){
    if (aArg.gt(Decimal.ZERO)){
      throw new IllegalArgumentException(aName + " cannot be greater than 0: " + aArg);
    }
  }

  /** Verify that the given value is both greater than 0, and has no fractional value (has no decimals). */
  public static void positiveInteger(Decimal aArg, String aName){
    if (aArg.lteq(Decimal.ZERO) || aArg.getNumDecimals() != 0){
      throw new IllegalArgumentException(aName + " is not a positive integer: " + aArg);
    }
  }

  /**
   Verify that the given year-month-day can form a date.
   No checking on the number of days in the month is performed - only basic range checking.
   
   @param aYear a positive integer
   @param aMonth integer in the range 1..12 (inclusive)
   @param aDay in the range 1..31 (inclusive), with possible fractional day.
   */
  public static void date(Decimal aYear, Decimal aMonth, Decimal aDay){
    positiveInteger(aYear, "Year");
    positiveInteger(aMonth, "Month");
    positive(aDay, "Day");
    range(aMonth, "Month", Decimal.from("1"), Decimal.from("12"));
    range(aDay, "Day", Decimal.from("1"), Decimal.from("31.9999999999999999999999999999"));
  }
  
  private Check(){}

}
