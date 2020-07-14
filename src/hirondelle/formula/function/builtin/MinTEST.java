package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class MinTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(MinTEST.class);
  }

  public void testCases(){
    Function func = new Min();
    
    testWith(func, "0", "0");
    testWith(func, "0", "0", "0", "0");
    testWith(func, "0", "1", "0", "0");
    testWith(func, "0", "0", "1", "0");
    testWith(func, "0", "0", "0", "1");
    testWith(func, "-1", "-1", "0", "0");
    testWith(func, "-1", "0", "-1", "0");
    testWith(func, "-1", "0", "0", "-1");
    
    testWith(func, "-1", "-1", "0", "1");
    testWith(func, "-1", "0", "-1", "1");
    testWith(func, "-1", "1", "0", "-1");
    
    testWith(func, "0", "0", "1", "100");
    testWith(func, "-32131.3213", "-3213", "100", "-32131.3213");
    testWith(func, "-3213", "-3213", "100", "32131.3213");
    
    testWith(func, "12.321", "56", "17", "29", "12.321", "32131");
  }

}
