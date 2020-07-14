package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class LogBase10TEST extends BaseTEST {

  public static void main(String args[]) {
    junit.textui.TestRunner.run(LogBase10TEST.class);
  }

  public void testCases(){
    Function func = new LogBase10();
    
    testWith(func, "0.0", "1");
    testWith(func, "1.0", "10");
    testWith(func, "-1.0", "0.1");
    testWith(func, "-2.0", "0.01");
    testWith(func, "-3.0", "0.001");
    testWith(func, "2.0", "100");
    testWith(func, "3.0", "1000");

    testWith(func, "3.1770494419581974", "1503.3131");
    
    testFailWith(func, "0.0", "0");

  }

}
