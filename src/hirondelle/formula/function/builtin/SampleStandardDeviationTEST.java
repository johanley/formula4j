package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class SampleStandardDeviationTEST extends BaseTEST {

  public static void main(String args[]) {
    junit.textui.TestRunner.run(SampleStandardDeviationTEST.class);
  }

  public void testCases(){
    Function func = new SampleStandardDeviation();
    
    testWith(func, "0.0", "0", "0");
    testWith(func, "0.0", "1", "1");
    testWith(func, "0.0", "1.0", "1.0");
    testWith(func, "0.0", "-1.0", "-1.0");
    testWith(func, "0.0", "5123", "5123");
    testWith(func, "0.0", "5123.3213", "5123.3213");
    testWith(func, "2.138089935299395", "2", "4", "4", "4", "5", "5", "7", "9");
    testWith(func, "1.4142135623730951", "100", "102");
    
    testFailWith(func, "1");
    testFailWith(func);
  }
}
