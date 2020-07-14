package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class FloorTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(FloorTEST.class);
  }

  public void testCases(){
    Function func = new Floor();
    
    testWith(func, "0", "0");
    testWith(func, "0", "-0");
    testWith(func, "0", "+0");
    
    testWith(func, "0", "0.5");
    testWith(func, "0", "0.000000000000000000001");
    testWith(func, "0", "0.999999999999999999999");
    testWith(func, "-1", "-0.000000000000000000001");
    testWith(func, "-1", "-0.999999999999999999999");
    testWith(func, "-1", "-0.5");
    testWith(func, "-2", "-1.0000000000000000000001");
    testWith(func, "-2", "-1.9999999999999999999999");
    testWith(func, "0", "0.00000000000000000000000000000000000000000000001");
    testWith(func, "0", "0.99999999999999999999999999999999999999999999999");

    testWith(func, "1", "1");
    testWith(func, "1", "1.000000000000000000000000001");
    testWith(func, "1", "1.999999999999999999999999999");

  }

  

}
