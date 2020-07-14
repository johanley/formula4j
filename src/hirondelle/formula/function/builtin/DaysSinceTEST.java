package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;


public final class DaysSinceTEST extends BaseTEST {

  public static void main(String args[]) {
    junit.textui.TestRunner.run(DaysSinceTEST.class);
  }

  public void testCases(){
    Function func = new DaysSince();

    //these depend on hard-coded current dates and times
    /*
    testWith(func, "0.0", "2013", "1", "14");
    testWith(func, "2.0", "2013", "1", "12");
    testWith(func, "2.5", "2013", "1", "11.5");
    */
  }

}
