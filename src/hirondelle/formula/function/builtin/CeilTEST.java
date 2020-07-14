package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class CeilTEST extends BaseTEST {
  public static void main(String args[]) {
    junit.textui.TestRunner.run(CeilTEST.class);
  }

  public void testCases(){
    Function func = new Ceil();
    
    testWith(func, "0", "0");
    testWith(func, "0", "-0");
    testWith(func, "0", "+0");
    
    testWith(func, "1", "0.5");
    testWith(func, "1", "0.000000000000000000001");
    testWith(func, "1", "0.999999999999999999999");
    testWith(func, "0", "-0.000000000000000000001");
    testWith(func, "0", "-0.999999999999999999999");
    testWith(func, "0", "-0.5");
    testWith(func, "-1", "-1.0000000000000000000001");
    testWith(func, "-1", "-1.9999999999999999999999");
    testWith(func, "1", "0.00000000000000000000000000000000000000000000001");
    testWith(func, "1", "0.99999999999999999999999999999999999999999999999");
    
  }

}
