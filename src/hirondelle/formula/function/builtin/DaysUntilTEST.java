package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class DaysUntilTEST extends BaseTEST {

  public static void main(String args[]) {
    junit.textui.TestRunner.run(DaysUntilTEST.class);
  }

  public void testCases(){
    Function func = new DaysUntil();

    //these depend on hard-coded current dates and times
    /*
    testWith(func, "0", "2012", "12", "10");
    testWith(func, "-2", "2012", "12", "8");
    testWith(func, "2", "2012", "12", "12");
    */
  }
  
}
